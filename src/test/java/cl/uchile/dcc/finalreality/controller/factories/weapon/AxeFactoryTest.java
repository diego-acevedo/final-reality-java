package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Axe;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AxeFactoryTest {

  private GameDriver driver;
  private AbstractWeaponFactory factory;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new AxeFactory();
  }

  @Test
  void createAxeTest() throws InvalidStatValueException {
    Weapon weapon = factory.create();
    assertEquals(new Axe("Axe 1", 60, 30), weapon);
    weapon = factory.create();
    assertEquals(new Axe("Axe 2", 81, 78), weapon);
    weapon = factory.create();
    assertEquals(new Axe("Axe 3", 92, 90), weapon);
    weapon = factory.create();
    assertEquals(new Axe("Axe 4", 52, 39), weapon);
    weapon = factory.create();
    assertEquals(new Axe("Axe 5", 56, 43), weapon);
  }
}