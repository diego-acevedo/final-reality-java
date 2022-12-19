package cl.uchile.dcc.finalreality.exceptions;

import static cl.uchile.dcc.finalreality.Utils.randomInt;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.Require;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class RequireTest {

  private long seed;
  private Random generator;

  @BeforeEach
  void setUp() {
    seed = -7588893100942082354L;//new Random().nextLong();
    generator = new Random(seed);
  }

  @DisplayName(
      "Require.statValueAtLeast() throws InvalidStatValueException if the actualStat is "
          + "less than least")
  @RepeatedTest(20)
  public void testStatValueAtLeast() {
    int least = randomInt(generator);
    int actualStat = generator.nextInt(Integer.MIN_VALUE, least);
    assertThrows(InvalidStatValueException.class,
        () -> Require.statValueAtLeast(least, actualStat, "test"),
        "Tests failed with seed: " + seed);
  }

  @DisplayName(
      "Require.statValueAtLeast() does not throw InvalidStatValueException if the "
          + "actualStat is greater than least")
  @RepeatedTest(20)
  public void testStatValueAtLeast2() {
    int least = randomInt(generator);
    int actualStat = randomInt(least + 1, Integer.MAX_VALUE, generator);
    assertDoesNotThrow(() -> Require.statValueAtLeast(least, actualStat, "test"),
        "Tests failed with seed: " + seed);
  }

  @DisplayName(
      "Require.statValueAtLeast() does not throw InvalidStatValueException if the "
          + "actualStat is equal to least")
  @RepeatedTest(20)
  public void testStatValueAtLeast3() {
    int least = randomInt(generator);
    assertDoesNotThrow(() -> Require.statValueAtLeast(least, least, "test"),
        "Tests failed with seed: " + seed);
  }
}