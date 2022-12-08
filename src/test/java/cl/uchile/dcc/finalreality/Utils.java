package cl.uchile.dcc.finalreality;

import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 *
 */
public class Utils {

  /**
   * Creates a random string with the given length and random number generator.
   *
   * <p>This function is pure because it does not modify any state of the program.
   */
  @Contract(pure = true)
  public static @NotNull String randomString(int length, Random generator) {
    return RandomStringUtils.random(length, 0, 0, false, false, null, generator);
  }

  /**
   * Returns a random integer between {@code min} (inclusive) and {@code max} (exclusive) using the
   * given random number generator.
   *
   * <p>This function is pure because it does not modify any state of the program.
   */
  @Contract(pure = true)
  public static int randomInt(int min, int max, @NotNull Random generator) {
    if (max - min <= 0) { // Prevents overflow
      return randomInt(generator);
    }
    return generator.nextInt(max - min) + min;
  }

  /**
   * Returns a random integer between {@code Integer.MIN_VALUE} (inclusive) and
   * {@code Integer.MAX:VALUE} (exclusive) using the given random number generator.
   *
   * <p>This function is pure because it does not modify any state of the program.
   *
   * @see Integer#MIN_VALUE
   * @see Integer#MAX_VALUE
   */
  @Contract(pure = true)
  public static int randomInt(@NotNull Random generator) {
    return (generator.nextBoolean() ? -1 : 1) * generator.nextInt(Integer.MAX_VALUE);
  }
}
