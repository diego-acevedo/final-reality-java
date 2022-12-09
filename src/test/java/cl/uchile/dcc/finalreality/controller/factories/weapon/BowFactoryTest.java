package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Bow;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowFactoryTest {

  private GameDriver driver;
  private AbstractWeaponFactory factory;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    this.factory = new BowFactory();
  }

  @Test
  void createBowTest() throws InvalidStatValueException {
    Weapon weapon = factory.create();
    assertEquals(new Bow("Bow 1", 60, 30), weapon);
    weapon = factory.create();
    assertEquals(new Bow("Bow 2", 81, 78), weapon);
    weapon = factory.create();
    assertEquals(new Bow("Bow 3", 92, 90), weapon);
    weapon = factory.create();
    assertEquals(new Bow("Bow 4", 52, 39), weapon);
    weapon = factory.create();
    assertEquals(new Bow("Bow 5", 56, 43), weapon);
  }
}