package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.ParalysisEffect;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.ParalyzableEffect;
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
    assertEquals(3, enemy.getParalysisStatus().spriteColumn());
    enemy.changeParalysisStatus(effect);
    assertEquals(2, enemy.getParalysisStatus().spriteColumn());
    enemy.receiveEffect();
    assertTrue(enemy.isParalysed());
    enemy.receiveEffect();
    assertFalse(enemy.isParalysed());
  }

}