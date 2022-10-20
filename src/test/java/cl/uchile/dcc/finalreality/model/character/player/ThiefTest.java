package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Bow;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ThiefTest {

  Thief thief1;
  Thief thief2;
  Thief thief3;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    thief1 = new Thief("Thief1", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    thief2 = new Thief("Thief2", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    thief3 = new Thief("Thief3", rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException {
    thief1.equip(new Bow("Bow", 10, 10));
    thief2.equip(new Bow("Bow", 10, 20));
    thief3.equip(new Bow("Bow", 10, 30));
    assertEquals(10, thief1.getEquippedWeapon().getWeight());
    assertEquals(20, thief2.getEquippedWeapon().getWeight());
    assertEquals(30, thief3.getEquippedWeapon().getWeight());
    thief1.waitTurn();
    thief2.waitTurn();
    thief3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),thief1);
    assertEquals(queue.poll(),thief2);
    assertEquals(queue.poll(),thief3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(thief1.getMaxHp(), thief1.getCurrentHp());
    thief1.setCurrentHp(5);
    assertEquals(5, thief1.getCurrentHp());
    try {
      thief1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testToString() {
    assertEquals(thief1.toString(), "Thief{maxHp=%d, defense=%d, name='%s'}"
        .formatted(thief1.getMaxHp(), thief1.getDefense(), thief1.getName()));
    assertEquals(thief2.toString(), "Thief{maxHp=%d, defense=%d, name='%s'}"
        .formatted(thief2.getMaxHp(), thief2.getDefense(), thief2.getName()));
    assertEquals(thief3.toString(), "Thief{maxHp=%d, defense=%d, name='%s'}"
        .formatted(thief3.getMaxHp(), thief3.getDefense(), thief3.getName()));
    assertNotEquals(thief1.toString(), thief2.toString());
    assertNotEquals(thief1.toString(), thief3.toString());
    assertNotEquals(thief2.toString(), thief3.toString());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(thief1, thief1);
    assertEquals(thief2, thief2);
    assertEquals(thief3, thief3);
    assertEquals(thief1, new Thief(thief1.getName(), thief1.getMaxHp(), thief1.getDefense(), queue));
    assertEquals(thief2, new Thief(thief2.getName(), thief2.getMaxHp(), thief2.getDefense(), queue));
    assertEquals(thief3, new Thief(thief3.getName(), thief3.getMaxHp(), thief3.getDefense(), queue));
    assertNotEquals(thief1, thief2);
    assertNotEquals(thief3, thief2);
    assertNotEquals(thief1, thief3);
  }
}