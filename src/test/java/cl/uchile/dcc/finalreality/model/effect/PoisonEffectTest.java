package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.PoisonEffect;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.PoisonousEffect;
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
    assertEquals(3, enemy.getPoisonStatus().spriteColumn());
    enemy.changePoisonousStatus(effect);
    assertEquals(1, enemy.getPoisonStatus().spriteColumn());
    assertEquals(100, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(50, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
  }

}