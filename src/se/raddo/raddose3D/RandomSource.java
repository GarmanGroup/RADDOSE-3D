package se.raddo.raddose3D;

import java.util.Random;

/**
 * The single source of pseudo-random numbers for a RADDOSE-3D run.
 * <p>
 * Photoelectron and fluorescence escape are Monte Carlo calculations, so a run
 * consumes a large number of random values. Previously these came from
 * {@link Math#random()} and {@link java.util.concurrent.ThreadLocalRandom},
 * neither of which can be seeded, so two runs of the same input produced
 * different answers -- typically a few tenths of a percent apart on average
 * diffraction weighted dose. That made results irreproducible and made it
 * impossible to tell a small genuine change from run-to-run noise.
 * <p>
 * Routing every draw through here makes a run reproducible: give the same seed
 * and you get the same numbers. The seed is taken, in order of precedence,
 * from {@link #setSeed(long)} (the {@code --seed} command line option), then
 * the {@code RADDOSE_SEED} environment variable, and otherwise from the clock.
 * Whichever is used is printed at startup so that any run can be repeated.
 * <p>
 * This class holds static mutable state deliberately, which is not the same
 * thing as the accidental sharing that static simulation parameters would
 * cause: a Monte Carlo calculation wants one stream of random numbers for the
 * whole run, and drawing from a single stream is what makes the run
 * reproducible. It is not thread safe, and the simulation is single threaded.
 */
public final class RandomSource {

  /** The seed in use. */
  private static long    seed;

  /** The generator, replaced whenever the seed changes. */
  private static Random  generator;

  /** Whether the seed was chosen by the user rather than the clock. */
  private static boolean seedWasChosen;

  static {
    long initial = System.nanoTime();
    boolean chosen = false;

    String env = System.getenv("RADDOSE_SEED");
    if (env != null) {
      try {
        initial = Long.parseLong(env.trim());
        chosen = true;
      } catch (NumberFormatException ignored) {
        System.err.println("Ignoring unparseable RADDOSE_SEED value: " + env);
      }
    }

    seed = initial;
    seedWasChosen = chosen;
    generator = new Random(initial);
  }

  private RandomSource() {
    // Static utility class.
  }

  /**
   * Restarts the generator from the given seed. Call before the simulation
   * begins; calling mid-run restarts the stream.
   *
   * @param newSeed the seed to use
   */
  public static void setSeed(final long newSeed) {
    seed = newSeed;
    seedWasChosen = true;
    generator = new Random(newSeed);
  }

  /** @return the seed this run is using. */
  public static long getSeed() {
    return seed;
  }

  /** @return true if the seed came from the user rather than the clock. */
  public static boolean isSeedChosen() {
    return seedWasChosen;
  }

  /**
   * Describes the seed for the run log, so a run can be reproduced.
   *
   * @return a human readable description of the seed in use
   */
  public static String describeSeed() {
    return "Random seed: " + seed
        + (seedWasChosen ? " (specified)"
                         : " (generated; pass --seed " + seed
                           + " to repeat this run)");
  }

  /** @return the next double in [0, 1), replacing {@code Math.random()}. */
  public static double nextDouble() {
    return generator.nextDouble();
  }

  /**
   * @param bound upper bound, exclusive
   * @return the next int in [0, bound)
   */
  public static int nextInt(final int bound) {
    return generator.nextInt(bound);
  }

  /** @return the next value from a standard normal distribution. */
  public static double nextGaussian() {
    return generator.nextGaussian();
  }
}
