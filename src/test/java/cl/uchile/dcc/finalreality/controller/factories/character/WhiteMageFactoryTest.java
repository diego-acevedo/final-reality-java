package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class WhiteMageFactoryTest {

  private GameDriver driver;
  private AbstractCharacterFactory factory;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new WhiteMageFactory();
  }

  @Test
  void createWhiteMageTest() throws InvalidStatValueException {
    PlayerCharacter character = factory.create(turnsQueue);
    assertEquals(new WhiteMage("WhiteMage 1", 1860, 20, 131, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new WhiteMage("WhiteMage 2", 1718, 22, 50, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new WhiteMage("WhiteMage 3", 1852, 9, 56, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new WhiteMage("WhiteMage 4", 1023, 14, 136, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new WhiteMage("WhiteMage 5", 1855, 23, 111, turnsQueue), character);
  }
}