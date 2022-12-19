package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.effect.BurntEffect;
import cl.uchile.dcc.finalreality.model_controller.model.effect.FireEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class BurntEffectTest {

  private FireEffect effect;
  private Enemy enemy;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    GameDriver.getGameDriver((new Random()).nextLong());
    turnsQueue = new LinkedBlockingQueue<>();
    this.enemy = new Enemy("Enemy", 100, 100, 100, 100, turnsQueue);
    this.effect = new BurntEffect(100);
  }

  @Test
  void burntEffectTest() throws InvalidStatValueException {
    assertFalse(enemy.getBurntStatus().isBurnt());
    assertEquals(3, enemy.getBurntStatus().spriteColumn());
    enemy.changeBurntStatus(effect);
    assertEquals(0, enemy.getBurntStatus().spriteColumn());
    assertEquals(100, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(50, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
    enemy.receiveEffect();
    assertEquals(0, enemy.getCurrentHp());
  }

}