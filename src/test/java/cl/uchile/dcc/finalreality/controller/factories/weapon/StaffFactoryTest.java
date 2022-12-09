package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Staff;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaffFactoryTest {

  private GameDriver driver;
  private AbstractWeaponFactory factory;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    this.factory = new StaffFactory();
  }

  @Test
  void createStaffTest() throws InvalidStatValueException {
    Weapon weapon = factory.create();
    assertEquals(new Staff("Staff 1", 60, 30, 31), weapon);
    weapon = factory.create();
    assertEquals(new Staff("Staff 2", 68, 42, 40), weapon);
    weapon = factory.create();
    assertEquals(new Staff("Staff 3", 52, 39, 26), weapon);
    weapon = factory.create();
    assertEquals(new Staff("Staff 4", 73, 54, 36), weapon);
    weapon = factory.create();
    assertEquals(new Staff("Staff 5", 55, 13, 41), weapon);
  }
}