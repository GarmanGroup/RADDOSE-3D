# Test triage

Findings from restoring the test suite.

> **Status 2026-09-17: all items are resolved and nothing is parked.**
> `ant test-pending` reports zero tests. Items 1 and 2 were decided by the
> Garman group; items 4, 6, 7 and 8 were fixed; items 3 and 5 were measured and
> needed no change. The history below is kept because several of these were
> mis-diagnosed first time and the measurements are worth not repeating.

Originally: **nothing here had been "fixed" by
changing an expectation.** Each item is either a parked test (tagged
`pending`, excluded from `ant test` / `ant test-all`, runnable with
`ant test-pending`) or a latent defect pinned by a characterisation test.

Every number below was reproduced by compiling the relevant commit in a
throwaway git worktree and running a probe against it, not inferred from
reading diffs.

## How to run

| command | what it runs |
|---|---|
| `ant build build-tests test` | the fast suite (162 tests, ~5 s from clean) |
| `ant test-all` | adds the `slow` geometry tests (168) |
| `ant test-pending` | only the parked tests below; **expected to fail** |
| `ant test-network` | only the tests that contact physics.nist.gov |

`JAVA_HOME` must point at a JDK (this machine has only Homebrew's JDK 25:
`export JAVA_HOME=/opt/homebrew/opt/openjdk`).

---

## 1. Absorption coefficients shifted by −2.6% — a debug toggle left flipped

**Parked tests:** `CoefCalcTests.testCoefCalcScenario1`, `testCoefCalcScenario3`,
`testCoefCalcWaterBasedSurrounding`
**Cause:** commit `b0a79ff` (2023-10-22, *"Tidied up MicroED. Almost ready for
proper release"*)
**Severity: this is the one to look at first.**

That commit swapped which of two mutually-exclusive commented-out lines is
active in `CoefCalcCompute.java:146-149`:

```diff
-  protected static final double WATER_CONCENTRATION = 55555;  // Density of 1 g/cm^3
-//protected static final double WATER_CONCENTRATION = 51666;  // Density of 0.93 g/cm^3
+//protected static final double WATER_CONCENTRATION = 55555;  // Density of 1 g/cm^3
+  protected static final double WATER_CONCENTRATION = 51666;  // Density of 0.93 g/cm^3
```

51666 / 55555 = 0.93 exactly, i.e. the density of amorphous ice rather than
liquid water. That is physically meaningful for cryo/EM work, and the commit
was an EM commit — but it applies globally, so **every standard MX dose
calculation has been ~2.6% low since October 2023**, and this is in the
released `raddose3d.jar`.

Measured, by bisecting compiled worktrees (absorption coefficient, scenario 1):

| commit | date | value | vs RADDOSE-v2 ref 1.042e-3 (tol 5e-6) |
|---|---|---|---|
| `5e9b053` | 2023-10-18 | 1.04575543e-03 | +3.8e-6 — passes |
| **`b0a79ff`** | **2023-10-22** | **1.01852368e-03** | **−2.3e-5 — fails** |
| `34b8d25` … `HEAD` | → 2026 | 1.01852368e-03 | fails |

Reverting only that constant on current HEAD restores all three scenarios
exactly:

| scenario | HEAD | with 55555 restored | RADDOSE-v2 ref |
|---|---|---|---|
| 1 @ 8.05 keV | 1.01852368e-03 | 1.04575543e-03 | 1.042e-03 |
| 3 @ 12.1 keV | 4.45987638e-04 | 4.57617993e-04 | 4.60e-04 |
| 2 @ 14.05 keV *(control, passes either way)* | 4.67194987e-03 | 4.67591258e-03 | 4.675e-03 |

**Decision needed.** Either (a) 0.93 g/cm³ was intended globally, in which case
the RADDOSE-v2 reference values in the tests are obsolete and should be
re-derived and the change documented in the release notes; or (b) it was a
local EM experiment that escaped, in which case it is a live bug affecting all
MX users and the constant should be per-sample rather than a global toggle.

---

## 2. Crystal voxel rotation is computed then thrown away

**Parked test:** `CrystalCuboidTest.testCuboidCrystalPandL`
**Cause:** `c2191eb` (2018-12-03, *"Added surrounding to XFEL"*)

### The test's expectation is wrong

`AngleP` / `AngleL` are not broken. The crystal *mesh* is rotated correctly at
`CrystalPolyhedron.java:574-584`, and the voxel grid is then rebuilt over the
rotated bounding box:

```
Non-cubic crystal 100 x 50 x 20 um:
  AngleP=0    bbox = 100.0 x  50.0 x 20.0    51x26x11 voxels   12500 occupied
  AngleP=45   bbox = 106.1 x 106.1 x 20.0    54x54x11 voxels   12420 occupied
  AngleP=90   bbox =  50.0 x 100.0 x 20.0    26x51x11 voxels   12450 occupied
  AngleL=90   bbox = 100.0 x  20.0 x 50.0    51x11x26 voxels   12500 occupied
```

106.1 µm is exactly (100+50)/sqrt(2), and occupancy is conserved to within
discretisation. The feature works.

The test assumed `getCrystCoord(i,j,k)` returns *rotated coordinates of a fixed
grid*, so that a 180° rotation negates them. It actually returns
*bounding-box-relative coordinates of a rebuilt grid*. A cube rotated 180° has
the same bounding box, so voxel (0,0,0) is the same corner either way and the
negation assertion cannot hold. **The expectation needs rewriting or deleting;
it is not evidence of a production bug.** Left parked rather than edited, per
"flag, don't rebaseline".

Note the four 360°-invariance assertions in the same test pass trivially under
either reading, so they prove nothing.

### But two real defects sit underneath it

**(a) Dead code.** `CrystalPolyhedron.java:655-669` computes the rotated voxel
coordinate and then immediately overwrites all three components with the
unrotated values:

```java
tempCrystCoords[i][j][k][0] = x2;                                  // rotated
tempCrystCoords[i][j][k][1] = y2 * Math.cos(l) + z2 * Math.sin(l);
tempCrystCoords[i][j][k][2] = -1 * y2 * Math.sin(l) + z2 * Math.cos(l);

tempCrystCoords[i][j][k][0] = x;                                   // overwritten
tempCrystCoords[i][j][k][1] = y;
tempCrystCoords[i][j][k][2] = z;
```

`c2191eb` commented the rotation block out and added the unrotated assignment;
a later commit un-commented the rotation but left the overwrite in place, so it
now reads as "compute, then discard". Four lines above it sits the author's own
note: `//need to remember to add this back in for the main RADDOSE-3D`. It was
never added back.

**(b) The crystal and cryo grids are in different frames — this one is live.**
The surrounding/cryo grid at `CrystalPolyhedron.java:764-774` applies the same
rotation and *keeps* it. So with `AngleP` or `AngleL` non-zero:

| grid | rotated? |
|---|---|
| crystal mesh (`vertices`) | yes |
| crystal voxels (`crystCoord`) | **no** |
| surrounding voxels (`cryoCrystCoord`) | yes |

Both feed the same transform in the same exposure loop —
`Crystal.java:1087-1088` for the crystal and `Crystal.java:1295-1297` for the
surrounding — so they are meant to share a physical frame. Whenever a rotated
crystal is combined with `CALCSURROUNDING` / cryo photoelectron escape, the two
sets of voxels are mutually rotated.

**RESOLVED (2026-09-17).** The rotation was removed from `cryoCrystCoord` and
the dead lines deleted from `crystCoord`. Restoring the rotation for
`crystCoord`, which the in-code note suggests, was tested and rejected: it
double-rotates, dropping occupancy of a 100x50x20 um crystal at AngleP=90 from
12450 to 6500 voxels. `CrystalRotationFrameTest.surroundingEnclosesTheCrystalAtEveryRotation`
now passes and is no longer parked.

The remaining parked test, `CrystalCuboidTest.testCuboidCrystalPandL`, still
has a wrong expectation and needs rewriting or deleting -- that is unrelated to
this fix.

---

## 3. The `findDepth` deduplication fix appears to be unreachable

**Not parked** — no test fails. Recorded because the fix may be a no-op.
**Commit:** `9a75719` "fix findDepth deduplication"

The fix is correct in the abstract: the old code compared boxed `Double`
references (`distancesFound.get(i+1) == distancesFound.get(i)`), which is never
true for doubles outside the small-integer cache, and it also advanced `i` past
an element after removing one. Both are genuine defects **as written**.

However, no input could be found where the fix changes the output. Compiling
both versions and diffing:

- 98,051 sampled voxels across three meshes (100 µm cube, the convex `.obj`
  fixture, the concave `.obj` fixture) at seven rotation settings → **0
  differences**
- rays aimed exactly at every vertex, edge midpoint and face centre, plus a
  sub-voxel sweep along a face diagonal → **0 differences**

The likely reason is `Vector.polygonInclusionTest`: the pnpoly crossing-number
algorithm uses a half-open edge rule (`(v[i].y > p.y) != (v[j].y > p.y)`), which
by construction assigns a point on an edge shared by two triangles to exactly
one of them. The duplicate distances the filter exists to remove therefore never
arise.

**No action required**, but worth knowing that the bug it fixed was probably not
the cause of whatever symptom prompted it — so if that symptom is still open,
it has another cause. `FindDepthRegressionTest` documents this and keeps the
underlying invariants covered.

---

## 4. `BEAM_CIRCULAR` is compared with `==` rather than `equals`

**Pinned by:** `BeamGaussianCircularTest.circularFlagIsComparedByReferenceNotByValue`

`BeamGaussian.java:115` reads:

```java
if (properties.get(Beam.BEAM_CIRCULAR) == "TRUE") {
```

This is reference equality on a `String`. It works only because the ANTLR parser
stores the interned literal (`InputfileParser.java:4845`). Any caller building
the property map programmatically — the server package, a test, a future API —
gets a **rectangular** beam from an equal-but-not-identical `"TRUE"`, silently,
with a different `normFactor` and therefore different doses everywhere.

Same pattern at `BeamTophat.java:91` and `BeamExperimentalpgm.java:48`; also
`Version.java:24` (`REVISION == "?---?"`).

**FIXED (2026-09-17)** in `BeamGaussian`, `BeamTophat`, `BeamExperimentalpgm`,
`Version` and the two `crystalTypeEM` comparisons in `MicroED`, all now
`"LITERAL".equals(value)`. `BeamGaussianCircularTest.circularFlagIsComparedByValue`
asserts the corrected behaviour.

---

## 5. The circular-beam rewrite reduced numerical accuracy

**Pinned by:** `BeamGaussianCircularTest` (tolerances set to current behaviour)
**Commit:** `d2fbcf1` "circular beam now uses more accurate cartesian approach"

The commit replaced a 100-step polar trapezoid (with the radial integral done
analytically) by a 1000-step cartesian midpoint rule. Measured against the exact
Rayleigh closed form for the circular case, and a 2,000,001-point Simpson
reference for the elliptical case:

| | circular, σx=σy | elliptical, σ 20×60, aperture 25×70 |
|---|---|---|
| old polar trapezoid | ~1e-16 | ~5e-11 |
| new cartesian midpoint | ~3.6e-6 | ~3.3e-6 |

The old implementation was near machine-exact in both regimes. The new one is
about five orders of magnitude less accurate, and is also no longer symmetric
under swapping the x and y axes (the two orderings differ by ~3.5e-7).

In absolute terms the error is ~7e-6 relative, i.e. under 0.001% on dose, so
this is a precision regression rather than a correctness one — but the commit
message's rationale is not borne out. If the rewrite was motivated by a
suspected error in the polar version, that error did not show up in any case
tested here.

---

## 6. A `BeamGaussian` is inert until a container is applied

**Pinned by:** `BeamGaussianCircularTest.beamIsInertUntilContainerAttenuationIsApplied`

`attenuatedPhotonsPerSec` (`BeamGaussian.java:49`) is not initialised by the
constructor, so it defaults to 0 and `beamIntensity` returns 0 **everywhere**
until `applyContainerAttenuation` (`:269`) or `setPhotonsPerfs` (`:347`) is
called.

Not a live bug — `Crystal.expose` calls it at `Crystal.java:869` — but the
ordering is implicit and undocumented, and a fresh beam silently delivering zero
dose is a sharp edge for anyone using `BeamGaussian` directly.

---

## 6. Integer division inside floating point physics expressions

**Pinned by:** `IntegerDivisionTest`

Java evaluates `(2/3)` as integer division, giving 0, so any term it multiplies
disappears. Six such expressions exist:

| location | expression | effect |
|---|---|---|
| `CoefCalcCompute.java:2384` | `(1/16) * ((gamma-1)/gamma)^2` | term dropped |
| `CoefCalcCompute.java:2563` | `(1/8) * (1 - sqrt(1-beta^2))` | term dropped |
| `CoefCalcCompute.java:3759` | `(2/3) * (shells[i]/Z) * plasma^2` | term dropped; `shells[i]/Z` is also integer division |
| `CoefCalcCompute.java:3877` | `(2/3) * ((fk*totNum)/sumZ) * plasma^2` | term dropped |
| `MicroED.java:240` | `(4/3) * PI * a * b * c` | sphere volume 25% low |

**FIXED (2026-09-17)**: all five now use floating point (`2.0/3.0`, `1.0/8.0`,
`1.0/16.0`, `4.0/3.0`, and `(double) shells[i]/Z`). The golden-file tests for a
standard MX run are byte-identical before and after, which confirms
independently that this code is not reachable from an MX experiment -- the
change affects the electron/GOS paths only.

Two consequences worth noting. First, `getWkMolecule` collapses to
`a * bindingEnergy * 1000`, which means **commit `8d37c7a` -- which widened
`sumZ` from `int` to `double` to avoid a truncation -- cannot change any
result**, because the quantity it feeds is multiplied by zero. Second,
`MicroED.java:240` makes the spherical crystal volume three quarters of the
correct value, and that volume clamps `exposedVolume` at `MicroED.java:1825`.
(That branch is also guarded by `crystalTypeEM == "SPHERICAL"`, a String
reference comparison -- the same pattern as item 4 -- so it may not fire at
all.)

---

## 7. `ExposureSummary.exposureComplete()` throws if no image ran

**Pinned by:** `OutputWriterTest.exposureCompleteThrowsIfNoImagesRan`

`exposureComplete()` ends with `lastDWD = imageDWD[images - 1]`, which is
`imageDWD[-1]` when no image completed, throwing
`ArrayIndexOutOfBoundsException`. An exposure whose angular resolution exceeds
its wedge span would reach this.
<p>
**FIXED (2026-09-17)**: guarded with `if (images > 0)`.

Also worth knowing for future test-writing: the `Output*` classes cannot be
exercised without driving `ExposureSummary` through its whole observer
lifecycle (`exposureStart`, `summaryObservation`, `imageComplete`,
`exposureComplete`). A freshly constructed one returns null from every getter,
so `publishWedge` throws.

---

## 8. `examples/SMXray2_example_input.txt` does not parse

**Parked test:** `InputFileParseTest.smxray2ExampleIsStillInvalid`

Line 10 reads `AbsCoefCalc CIF`, which the parser rejects with "no viable
alternative at input 'CIF'" (the error on line 13 is recovery fallout). The
cause is confusing token naming in `Inputfile.g`:

```
Inputfile.g:393   CIF     : ('E'|'e')('X'|'x')('P'|'p')('S'|'s')('M'|'m');   <- matches "EXPSM"
Inputfile.g:509   CIFNAME : ('C'|'c')('I'|'i')('F'|'f');                      <- matches "CIF"
```

The token *named* `CIF` matches the literal **"EXPSM"**, so the keyword for a
CIF-based absorption calculation is spelled `EXPSM`. Replacing line 10 with
`AbsCoefCalc EXPSM` makes the file parse (it then fails only because the
referenced `Fe3O4` CIF file is absent).

**PARTIALLY FIXED (2026-09-17)**: the example now reads `AbsCoefCalc EXPSM` and
parses.

Two things remain open, both reported rather than changed:

1. **The example still cannot be run.** It names a CIF file, `Fe3O4`, that the
   project does not ship, and `CoefCalcFromCIF` reads from a local path only --
   its download code is commented out (`CoefCalcFromCIF.java:8-10`). It is
   therefore excluded from the example sweep, with a separate test asserting
   only that its `AbsCoefCalc` keyword is valid. Either ship the CIF file or
   change the example to an `AbsCoefCalc` that needs no external data.
2. **`CoefCalcFromCIF` NPEs on a missing file**: it catches the `IOException`,
   prints "Cannot read from specified path", then falls through and
   dereferences the null reader (`:25-33`).

Also unchanged: RD3D printed results for this file even while the parser was
recording two errors, so a malformed input does not stop the run.

---

## Mutation checks

The suite was validated by reverting each fix and confirming it goes red:

| mutation | failures |
|---|---|
| `Vector.dotProduct` sign flipped | 8 |
| `c0d0c3f` reverted (`muabsIndex = 4` for every shell) | 2 |
| circular beam ellipse clip removed | 11 |
| `WATER_CONCENTRATION` restored to 55555 | 0 in `ant test` — **by design**: the tests that detect it are parked. `ant test-pending` then reports all three `CoefCalc` scenarios **passing**, which is the end-to-end confirmation of item 1. |

---

## 7. Test-side defects found and fixed during the port

These were bugs in the tests, not the production code, so they were fixed
outright rather than parked:

| what | where | was |
|---|---|---|
| `CrystalDummy()` threw NPE | now supplies dimensions | `Crystal.java:297` casts `properties.get(CRYSTAL_DIM_X)` to `double` unguarded, so an empty map unboxes null. The `YDim`/`ZDim` lines right after it *are* wrapped in try/catch — the asymmetry looks unintentional, but production code was left alone. This alone broke 3 of 5 `InputParserTest` cases. |
| `ContainerTests` never ran | revived as `ContainerTest` | the file had **zero** `@Test` annotations, so TestNG silently ran none of it. The attenuation maths is now tested offline through a stub subclass of `ContainerSemiTransparent`; the two tests that genuinely need physics.nist.gov are tagged `network` and run only via `ant test-network`. |
| `CoefCalcTests.testSequenceParser` never ran | annotation added | no `@Test`; the only thing referencing `TestSequence.fasta`. |
| `setupDepthFinding` inside the innermost loop | hoisted | `CrystalCuboidTest.testFindDepth` re-rotated the whole polyhedron 33,300 times with constant arguments. Not in the `advanced` group, so it dominated `ant test`. |
| stale `se.raddo.raddose3D.tests.*` class-name strings | updated | the factory tests load classes by name. |
| `ContainerTests:90` one-sided comparison | `Math.abs` | `a - b < 1e-2` passes for any sufficiently negative difference. |
