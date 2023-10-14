package cl.uchile.dcc.finalreality.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import cl.uchile.dcc.finalreality.Utils;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class InvalidStatValueExceptionTest {

  // Note: The name of the variable is written in UPPER_CASE because it is a `final`
  // variable.
  private final String PREFIX = "The provided value is not a valid stat value. ";
  private long seed;
  private Random generator;

  @BeforeEach
  void setUp() {
    seed = new Random().nextLong();
    generator = new Random(seed);
  }

  @DisplayName("An invalid stat value exception can be thrown with a message.")
  @RepeatedTest(20)
  void constructorTest() {
    int length = generator.nextInt(100);
    String expectedDescription = Utils.randomString(length, generator);
    InvalidStatValueException exception = assertThrows(InvalidStatValueException.class, () -> {
      throw new InvalidStatValueException(expectedDescription);
    }, "Test failed for seed: " + seed);
    assertEquals(PREFIX + expectedDescription, exception.getMessage(),
        "Test failed with seed: " + seed);
  }
}