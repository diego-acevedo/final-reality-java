package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ThiefFactoryTest {

  private GameDriver driver;
  private AbstractCharacterFactory factory;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    this.factory = new ThiefFactory();
  }

  @Test
  void createThiefTest() throws InvalidStatValueException {
    PlayerCharacter character = factory.create(turnsQueue);
    assertEquals(new Thief("Thief 1", 1860, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Thief("Thief 2", 1981, 38, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Thief("Thief 3", 1942, 20, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Thief("Thief 4", 1852, 9, turnsQueue), character);
    character = factory.create(turnsQueue);
    assertEquals(new Thief("Thief 5", 1006, 23, turnsQueue), character);
  }

}