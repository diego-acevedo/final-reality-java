package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.AbstractCharacterFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.KnightFactory;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class KnightFactoryTest {

  private GameDriver driver;
  private AbstractCharacterFactory factory;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new KnightFactory();
  }

  @Test
  void createKnightTest() throws InvalidStatValueException {
    PlayerCharacter character = factory.create(turnsQueue);
    assertEquals(new Knight("Knight 1", 1860, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Knight("Knight 2", 1981, 38, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Knight("Knight 3", 1942, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Knight("Knight 4", 1852, 9, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Knight("Knight 5", 1006, 23, turnsQueue), character);
  }

}