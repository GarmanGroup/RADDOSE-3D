package se.raddo.raddose3D;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link RandomSource}, which exists so that a run can be repeated.
 * <p>
 * Photoelectron and fluorescence escape are Monte Carlo calculations. They
 * previously drew from {@link Math#random()} and {@code ThreadLocalRandom},
 * neither seedable, so two runs of the same input differed -- on a 30x20x15 um
 * crystal with escape enabled, average diffraction weighted dose varied over a
 * range of about 0.4%. That is large enough to mask a real change of the size
 * these tests are meant to detect.
 */
public class RandomSourceTest {

  private static List<Double> draw(final int n) {
    List<Double> out = new ArrayList<Double>(n);
    for (int i = 0; i < n; i++) {
      out.add(RandomSource.nextDouble());
    }
    return out;
  }

  @Test
  public void theSameSeedGivesTheSameSequence() {
    RandomSource.setSeed(12345L);
    List<Double> first = draw(500);

    RandomSource.setSeed(12345L);
    List<Double> second = draw(500);

    assertEquals(first, second, "same seed must replay the same sequence");
  }

  @Test
  public void differentSeedsGiveDifferentSequences() {
    RandomSource.setSeed(1L);
    List<Double> a = draw(200);

    RandomSource.setSeed(2L);
    List<Double> b = draw(200);

    assertNotEquals(a, b, "different seeds should not produce the same stream");
  }

  @Test
  public void intsAreAlsoReproducibleAndInRange() {
    RandomSource.setSeed(99L);
    List<Integer> first = new ArrayList<Integer>();
    for (int i = 0; i < 200; i++) {
      int v = RandomSource.nextInt(7);
      assertTrue(v >= 0 && v < 7, "nextInt out of range: " + v);
      first.add(v);
    }

    RandomSource.setSeed(99L);
    for (int i = 0; i < 200; i++) {
      assertEquals(first.get(i).intValue(), RandomSource.nextInt(7),
          "nextInt diverged at draw " + i);
    }
  }

  @Test
  public void doublesLieInTheUnitInterval() {
    RandomSource.setSeed(5L);
    for (int i = 0; i < 1000; i++) {
      double v = RandomSource.nextDouble();
      assertTrue(v >= 0.0 && v < 1.0, "nextDouble out of range: " + v);
    }
  }

  @Test
  public void settingASeedIsRecorded() {
    RandomSource.setSeed(4242L);
    assertEquals(4242L, RandomSource.getSeed());
    assertTrue(RandomSource.isSeedChosen(), "an explicit seed should be flagged");
    assertTrue(RandomSource.describeSeed().contains("4242"),
        "the seed should appear in the run log line: "
            + RandomSource.describeSeed());
  }

  /**
   * A generated seed must still be reported, otherwise a run that turns out to
   * be interesting cannot be repeated.
   */
  @Test
  public void aGeneratedSeedIsStillReportedSoRunsCanBeRepeated() {
    String described = RandomSource.describeSeed();
    assertTrue(described.contains(String.valueOf(RandomSource.getSeed())),
        "seed value missing from: " + described);
  }
}
