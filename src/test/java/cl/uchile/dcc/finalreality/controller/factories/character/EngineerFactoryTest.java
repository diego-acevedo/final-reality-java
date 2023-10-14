package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character.AbstractCharacterFactory;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character.EngineerFactory;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EngineerFactoryTest {

  private GameDriver driver;
  private AbstractCharacterFactory factory;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new EngineerFactory();
  }

  @Test
  void createEngineerTest() throws InvalidStatValueException {
    PlayerCharacter character = factory.create(turnsQueue);
    assertEquals(new Engineer("Engineer 1", 1860, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Engineer("Engineer 2", 1981, 38, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Engineer("Engineer 3", 1942, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Engineer("Engineer 4", 1852, 9, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Engineer("Engineer 5", 1006, 23, turnsQueue), character);
  }

}