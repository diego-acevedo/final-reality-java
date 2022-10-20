package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Axe;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EngineerTest {

  Engineer engineer1;
  Engineer engineer2;
  Engineer engineer3;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    engineer1 = new Engineer("Engineer1", rnd.nextInt(10,100),
        rnd.nextInt(10,100), queue);
    engineer2 = new Engineer("Engineer2", rnd.nextInt(10,100),
        rnd.nextInt(10,100), queue);
    engineer3 = new Engineer("Engineer3", rnd.nextInt(10,100),
        rnd.nextInt(10,100), queue);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException {
    engineer1.equip(new Axe("Axe", 10, 10));
    engineer2.equip(new Axe("Axe", 10, 20));
    engineer3.equip(new Axe("Axe", 10, 30));
    assertEquals(10, engineer1.getEquippedWeapon().getWeight());
    assertEquals(20, engineer2.getEquippedWeapon().getWeight());
    assertEquals(30, engineer3.getEquippedWeapon().getWeight());
    engineer1.waitTurn();
    engineer2.waitTurn();
    engineer3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),engineer1);
    assertEquals(queue.poll(),engineer2);
    assertEquals(queue.poll(),engineer3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(engineer1.getMaxHp(), engineer1.getCurrentHp());
    engineer1.setCurrentHp(5);
    assertEquals(5, engineer1.getCurrentHp());
    try {
      engineer1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testToString() {
    assertEquals(engineer1.toString(), "Engineer{maxHp=%d, defense=%d, name='%s'}"
        .formatted(engineer1.getMaxHp(), engineer1.getDefense(), engineer1.getName()));
    assertEquals(engineer2.toString(), "Engineer{maxHp=%d, defense=%d, name='%s'}"
        .formatted(engineer2.getMaxHp(), engineer2.getDefense(), engineer2.getName()));
    assertEquals(engineer3.toString(), "Engineer{maxHp=%d, defense=%d, name='%s'}"
        .formatted(engineer3.getMaxHp(), engineer3.getDefense(), engineer3.getName()));
    assertNotEquals(engineer1.toString(), engineer2.toString());
    assertNotEquals(engineer1.toString(), engineer3.toString());
    assertNotEquals(engineer2.toString(), engineer3.toString());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(engineer1, engineer1);
    assertEquals(engineer2, engineer2);
    assertEquals(engineer3, engineer3);
    assertEquals(engineer1, new Engineer(engineer1.getName(), engineer1.getMaxHp(), engineer1.getDefense(), queue));
    assertEquals(engineer2, new Engineer(engineer2.getName(), engineer2.getMaxHp(), engineer2.getDefense(), queue));
    assertEquals(engineer3, new Engineer(engineer3.getName(), engineer3.getMaxHp(), engineer3.getDefense(), queue));
    assertNotEquals(engineer1, engineer2);
    assertNotEquals(engineer3, engineer2);
    assertNotEquals(engineer1, engineer3);
  }
}