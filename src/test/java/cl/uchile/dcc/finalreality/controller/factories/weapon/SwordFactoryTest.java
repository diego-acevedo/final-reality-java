package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon.AbstractWeaponFactory;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon.SwordFactory;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Sword;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordFactoryTest {

  private GameDriver driver;
  private AbstractWeaponFactory factory;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new SwordFactory();
  }

  @Test
  void createSwordTest() throws InvalidStatValueException {
    Weapon weapon = factory.create();
    assertEquals(new Sword("Sword 1", 60, 30), weapon);
    weapon = factory.create();
    assertEquals(new Sword("Sword 2", 81, 78), weapon);
    weapon = factory.create();
    assertEquals(new Sword("Sword 3", 92, 90), weapon);
    weapon = factory.create();
    assertEquals(new Sword("Sword 4", 52, 39), weapon);
    weapon = factory.create();
    assertEquals(new Sword("Sword 5", 56, 43), weapon);
  }
}