package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {

  Knight knight1;
  Knight knight2;
  Knight knight3;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    knight1 = new Knight("Knight1", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    knight2 = new Knight("Knight2", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    knight3 = new Knight("Knight3", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException {
    knight1.equip(new Sword("Sword", 10, 10));
    knight2.equip(new Sword("Sword", 10, 20));
    knight3.equip(new Sword("Sword", 10, 30));
    assertEquals(10, knight1.getEquippedWeapon().getWeight());
    assertEquals(20, knight2.getEquippedWeapon().getWeight());
    assertEquals(30, knight3.getEquippedWeapon().getWeight());
    knight1.waitTurn();
    knight2.waitTurn();
    knight3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),knight1);
    assertEquals(queue.poll(),knight2);
    assertEquals(queue.poll(),knight3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(knight1.getMaxHp(), knight1.getCurrentHp());
    knight1.setCurrentHp(5);
    assertEquals(5, knight1.getCurrentHp());
    try {
      knight1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testToString() {
    assertEquals(knight1.toString(), "Knight{maxHp=%d, defense=%d, name='%s'}"
        .formatted(knight1.getMaxHp(), knight1.getDefense(), knight1.getName()));
    assertEquals(knight2.toString(), "Knight{maxHp=%d, defense=%d, name='%s'}"
        .formatted(knight2.getMaxHp(), knight2.getDefense(), knight2.getName()));
    assertEquals(knight3.toString(), "Knight{maxHp=%d, defense=%d, name='%s'}"
        .formatted(knight3.getMaxHp(), knight3.getDefense(), knight3.getName()));
    assertNotEquals(knight1.toString(), knight2.toString());
    assertNotEquals(knight1.toString(), knight3.toString());
    assertNotEquals(knight2.toString(), knight3.toString());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(knight1, knight1);
    assertEquals(knight2, knight2);
    assertEquals(knight3, knight3);
    assertEquals(knight1, new Knight(knight1.getName(), knight1.getMaxHp(), knight1.getDefense(), queue));
    assertEquals(knight2, new Knight(knight2.getName(), knight2.getMaxHp(), knight2.getDefense(), queue));
    assertEquals(knight3, new Knight(knight3.getName(), knight3.getMaxHp(), knight3.getDefense(), queue));
    assertNotEquals(knight1, knight2);
    assertNotEquals(knight3, knight2);
    assertNotEquals(knight1, knight3);
  }
}