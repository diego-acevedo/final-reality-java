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

class PoisonEffectTest {

  private PoisonousEffect effect;
  private Enemy enemy;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    GameDriver.getGameDriver((new Random()).nextLong());
    turnsQueue = new LinkedBlockingQueue<>();
    this.enemy = new Enemy("Enemy", 100, 100, 100, 100, turnsQueue);
    this.effect = new PoisonEffect(150);
  }

  @Test
  void burntEffectTest() throws InvalidStatValueException {
    assertFalse(enemy.getPoisonStatus().isPoisoned());
    enemy.changePoisonousStatus(effect);
    assertEquals(100, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(50, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
  }

}