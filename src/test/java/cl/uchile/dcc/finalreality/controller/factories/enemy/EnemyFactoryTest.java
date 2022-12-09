package cl.uchile.dcc.finalreality.controller.factories.enemy;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EnemyFactoryTest {

  private GameDriver driver;
  private AbstractEnemyFactory factory;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    long seed = 2411269062697659106L;
    driver = GameDriver.getGameDriver(seed);
    driver = GameDriver.getGameDriver(seed);
    this.factory = new EnemyFactory();
  }

  @Test
  void create() throws InvalidStatValueException {
    Enemy enemy;
    enemy = factory.create(turnsQueue);
    assertEquals(new Enemy("Enemy 1", 78, 1860, 20, 81, turnsQueue), enemy);
    enemy = factory.create(turnsQueue);
    assertEquals(new Enemy("Enemy 2", 39, 1942, 20, 52, turnsQueue), enemy);
    enemy = factory.create(turnsQueue);
    assertEquals(new Enemy("Enemy 3", 56, 1006, 23, 94, turnsQueue), enemy);
    enemy = factory.create(turnsQueue);
    assertEquals(new Enemy("Enemy 4", 64, 1855, 23, 61, turnsQueue), enemy);
    enemy = factory.create(turnsQueue);
    assertEquals(new Enemy("Enemy 5", 83, 1573, 39, 89, turnsQueue), enemy);
  }
}