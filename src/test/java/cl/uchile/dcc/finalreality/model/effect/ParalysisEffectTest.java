package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ParalysisEffectTest {

  private ParalyzableEffect effect;
  private Enemy enemy;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    GameDriver.getGameDriver((new Random()).nextLong());
    turnsQueue = new LinkedBlockingQueue<>();
    this.enemy = new Enemy("Enemy", 100, 100, 100, 100, turnsQueue);
    this.effect = new ParalysisEffect();
  }

  @Test
  void paralysisEffectTest() throws InvalidStatValueException {
    assertFalse(enemy.isParalysed());
    enemy.changeParalysisStatus(effect);
    enemy.receiveEffect();
    assertTrue(enemy.isParalysed());
    enemy.receiveEffect();
    assertFalse(enemy.isParalysed());
  }

}