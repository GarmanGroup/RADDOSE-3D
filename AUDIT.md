# Code audit: EM/XFEL changes leaking into the MX path

**Date:** 2026-09-17 · **Scope:** `src/se/raddo/raddose3D/`, all history

## Why

Two bugs were found incidentally while restoring the test suite, and both had
the same shape: a change made for the electron-diffraction or XFEL work that
silently altered the standard MX path and was never reverted.

- `WATER_CONCENTRATION` flipped from 55555 to 51666 in a commit titled
  "Tidied up MicroED" — every MX absorption coefficient shifted ~2.6%
- the crystal voxel rotation disabled in "Added surrounding to XFEL"

Two found by accident suggested more existed. This audit went looking.

## Conclusion

**The population is small and is now bounded.** Sweeping the whole codebase for
the signatures of both bugs — commented-out alternative definitions, dead
stores, parameters overwritten by literals, and the author's own "for now" /
"remember to" markers — turned up **no third instance of an EM change silently
altering live MX numerics**.

What it did find: one genuine MX-path defect (already documented as
TEST-TRIAGE.md #2), several pieces of dead code that would become bugs if
re-enabled, and a larger set of author-flagged approximations that are
deliberate and confined to the EM path.

The good news is specific: the MX numerical core — `Element`,
`ElementDatabase`, `CoefCalcCompute`'s absorption/elastic/attenuation routines,
`Crystal.expose`'s dose accumulation — is not riddled with stray EM toggles.
The two known cases were the exceptions, not the rule.

---

## Method

Four detectors over all 93 production classes, each validated against the two
known bugs before being trusted:

| detector | what it looks for | caught the known cases? |
|---|---|---|
| dead stores | value assigned, overwritten with no read between | yes — found the AngleP case |
| commented alternatives | same identifier, one live one commented, within 6 lines | yes — found WATER_CONCENTRATION |
| author markers | "for now", "remember to", "fudge", "not right", "hardcod" … | yes — found both |
| literal overrides | identifier assigned a literal with an explanatory comment | yes — found `surrounding = false` |

Plus a commit cross-reference: 44 commits with EM/XFEL/GOS/MicroED/Monte-Carlo
in the title touched MX-path files, ranked by lines *deleted* (i.e. existing
logic modified rather than new methods added).

Detector output was triaged by hand; EM-only code paths (GOS, Bethe cross
sections, stopping power, ELSEPA) were excluded, since changes there cannot
reach an MX run.

---

## Findings

### A. Live MX-path defect

**A1. `cryoCrystCoord` is double-rotated** — already documented as
TEST-TRIAGE.md #2, pinned by `CrystalRotationFrameTest`. This is the third
member of the family, found by the dead-store detector. Decision pending.

### B. Dead code that is a trap, not yet a bug

**B1. `CrystalPolyhedron.trackPhotoelectron` is entirely dead.**
Its only call site, `Crystal.java:1196`, is commented out. (The
`trackPhotoelectron` calls in `MC.java` and `XFEL.java` are different private
methods with different signatures.) Inside it, `CrystalPolyhedron.java:2523`
does:

```java
surrounding = false;   //I'm just going to focus on the crystal for now
```

overwriting its own `boolean surrounding` parameter, so the caller's value is
discarded and stopping power, elastic MFPL and elastic probabilities are all
taken for the crystal regardless. Harmless while the method is unreachable;
wrong the moment anyone uncomments line 1196.

**B2. Dead rotation store**, `CrystalPolyhedron.java:655-669` — TEST-TRIAGE.md
#2(a). The lines should be deleted; the note above them
(`//need to remember to add this back in for the main RADDOSE-3D`) is
misleading, since restoring them breaks occupancy.

**B3. `proteinDensity`**, `CoefCalcCompute.java:1315-1317`. On the MX path:
`calculateSolventFractionFromNums()` feeds solvent fraction, hence composition,
hence absorption. A constant 1.35 is live while the molecular-weight-dependent
Fischer et al. (2004) form sits commented directly beneath it — despite the
comment above saying "change protein density to that calculated by Fischer et
al 2004". For a 5.6 kDa monomer the two differ by ~11%.
Not a regression (it has always been the constant), but the stated intent and
the code disagree. Worth a deliberate decision.

### C. Deliberate and correctly labelled — no action

**C1. `Element.java:378`**, the light-element guard. `0413c30` commented out
`if (atomicNumber <= LIGHT_ATOM_MAX_NUM)` so the L-edge jump corrections now
apply to every element. This *looks* like the pattern but is not: the commit is
titled "Fixed absorption cross sections for energies below L1 edge but above
L3", the change matches the title, and bisecting showed the CoefCalc reference
tests still passed at that commit. A candid comment
("I don't think this is doing it correctly") records the reasoning.

**C2. Author-flagged approximations**, all deliberate and commented:
M2–M5 edges counted only for Z ≥ 73 (`CoefCalcCompute.java:881-908, 1052-1079`);
`numInnerShells` capped "as no N edge in MuCalc" for Z > 60 (`:3275, :3752`);
the cryo Gumbel parameters recomputed with a blended density while the maximum
photoelectron distance is not (`CrystalPolyhedron.java:1510-1521`, with the
author's own `//I'm not recalculating the max distance though :/`).

**C3. EM-only scaffolding.** Roughly 30 commented-out alternatives in the GOS,
Bethe, stopping-power and ELSEPA code, plus `numSim = 10000` hardcoded over
`NUM_MONTE_CARLO_ELECTRONS` at `MicroED.java:1041-1042`. Confined to the EM
path; they cannot affect an MX run. The MicroED one is worth a look by whoever
owns that code, since it silently overrides a configured value.

### D. Hygiene, unrelated to correctness

**All four items below were fixed on 2026-09-17.** See the commit
"fix: make runs reproducible, and stop expose() killing the JVM".

- **`System.exit(0)` inside `Crystal.expose`** (`Crystal.java:759, 772, 782`)
  for the XFEL/MC/GOS branches. Terminating the JVM from a library method
  breaks the `server/` package and any embedding use.
- **Version banner printed per wedge** — `Crystal.java:740` runs on every
  `expose()` call, not once per run.
- **`private static` mutable state**: `CRYO_GUMBEL_DISTN_CALC_LOC` / `_SCALE`
  (`CrystalPolyhedron.java:81-82`) are static but reassigned per beam, so two
  crystals in one input would clobber each other. On inspection there were 12
  such fields, not 2 -- the Gumbel and Johnson SU distribution parameters for
  both the crystal and the cryo-solution. All are now per-instance.

### E. Non-determinism (found while verifying the cryoCrystCoord fix)

The simulation could not reproduce its own results: the same binary on the same
input gave a different answer every run, with average diffraction weighted dose
spanning about 0.4%. The cause was 214 `Math.random()` calls and 4
`ThreadLocalRandom.current()` calls, none of which can be seeded.

All now draw from `RandomSource`, seeded from `--seed`, the `RADDOSE_SEED`
environment variable, or the clock, and the seed is printed at the start of
every run so any result can be repeated. With a fixed seed all six output files
are byte-identical across separate JVM runs.

**A wrong turn worth recording.** While investigating, the cumulative
probability selection in `getIonisedElement` looked unsafe: the cumulative sums
are built by iterating `presentElements` (a `HashSet`) and consumed by
iterating `elementAbsorptionProbs.keySet()` (a different `HashMap`), and
`Element` overrides neither `hashCode` nor `equals`. Converting the collections
to insertion-ordered ones was tried and then reverted, because measurement
showed the concern was unfounded on both counts: with the RNG seeded, hash
ordering produced identical results across runs, and the two collections
iterate in the same order anyway (a `HashSet` is backed by a `HashMap`, so the
derived map inherits its ordering). Every consumption loop checked either
iterates the same collection it was built from, or looks up by key. No change
was warranted.

---

## What this does not cover

- **Numerical correctness of the EM/XFEL physics itself.** The audit asked
  whether EM changes leaked into MX, not whether the EM models are right.
- **`MicroED.java`, `XFEL.java`, `MC.java` internals** (14,000 lines combined),
  beyond confirming they do not write to MX-path state.
- **Semantic leaks with no textual signature.** A change that altered MX
  behaviour without leaving a toggle, dead store or comment would not be caught
  by any of these detectors. `WATER_CONCENTRATION`-style edits are findable
  precisely because they leave the old value behind; a plain overwrite would
  not. The only defence against that class is the regression tests now in
  place, which is an argument for extending them to `Crystal.expose` via
  golden files.

---

## Coverage baseline (2026-09-17)

`ant coverage` runs the suite under JaCoCo and writes `reports/coverage/index.html`.

| | overall | excluding MC/XFEL/MicroED |
|---|---|---|
| line | 24.9% (3830 / 15406) | **42.8%** (3830 / 8955) |
| branch | 20.4% | |
| method | 31.3% | |
| class | 66.7% (66 / 99) | |

The headline number is dominated by three near-duplicate subprograms --
`MC`, `XFEL` and `MicroED`, 6,316 lines between them, all at 0%. They are
separate entry points reached only via the `RUNS`/subprogram directives, they
consume RNG heavily, and `MicroED` drives a real browser. Testing them needs
seams, not more tests.

The MX path itself is reasonably covered:

| class | line coverage |
|---|---|
| `CoefCalcFromParams` | 100% |
| `CrystalSphericalNew` | 100% |
| `DDMBfactor` | 100% |
| `Histogram` | 95% |
| `ExposureSummary` | 92% |
| `Element` | 87% |
| `Crystal` | 84% |
| `BeamGaussian` | 70% |
| `CrystalPolyhedron` | 60% |

### Where the remaining value is

- **`CoefCalcCompute` 27.5%** (1,779 lines missed) is the largest genuine gap,
  but most of the missed code is the GOS/Bethe/ELSEPA electron physics, which
  an MX run never reaches -- confirmed by the golden-file tests being unchanged
  when the integer-division constants were corrected.
- **`RD3D` 0%** (205 lines) is command-line parsing and output wiring. Testable
  if `parseOutputDestinations` were extracted.
- **`CoefCalcFromPDB` 0%**, **`ContainerMixture`/`ContainerElemental` 0%** are
  network-bound (RCSB, NIST). The attenuation arithmetic is already covered via
  a stub subclass; the download paths are not, and arguably should not be.
- **`CrystalCuboidOld` 0%**, **`CrystalSphericalOld` 0%**, **`CoefCalcRaddose`
  0%** are legacy. `CoefCalcRaddose` shells out to an external `raddose` binary
  that is not shipped. These are candidates for deletion rather than tests.
- **`OutputFinalDoseStateRPreview` 0%** (110 lines) is the largest untested
  output and would follow the pattern already established in
  `OutputWriterTest`.
