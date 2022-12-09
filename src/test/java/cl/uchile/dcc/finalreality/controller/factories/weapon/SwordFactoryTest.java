package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.controller.factories.character.AbstractCharacterFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.BlackMageFactory;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class SwordFactoryTest {

  private GameDriver driver;
  private AbstractWeaponFactory factory;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    long seed = 2411269062697659106L;
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