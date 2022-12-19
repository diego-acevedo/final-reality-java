package cl.uchile.dcc.finalreality.exceptions;

import cl.uchile.dcc.finalreality.Utils;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InvalidEquipableWeaponExceptionTest {

  private final String PREFIX = "An invalid weapon is trying to be equiped. ";
  private long seed;
  private Random generator;

  @BeforeEach
  void setUp() {
    seed = new Random().nextLong();
    generator = new Random(seed);
  }

  @RepeatedTest(20)
  void constructorTest() {
    int length = generator.nextInt(100);
    String expectedDescription = Utils.randomString(length, generator);
    InvalidEquipableWeaponException exception = assertThrows(InvalidEquipableWeaponException.class, () -> {
      throw new InvalidEquipableWeaponException(expectedDescription);
    }, "Test failed for seed: " + seed);
    assertEquals(PREFIX + expectedDescription, exception.getMessage(),
        "Test failed with seed: " + seed);
  }
}