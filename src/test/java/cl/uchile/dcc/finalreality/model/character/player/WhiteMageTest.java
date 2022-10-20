package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class WhiteMageTest {

  WhiteMage whitemage1;
  WhiteMage whitemage2;
  WhiteMage whitemage3;
  BlockingQueue<GameCharacter> queue;
  static Sword sword;
  static Axe axe;
  static Knife knife;
  static Staff staff;
  static Bow bow;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    whitemage1 = new WhiteMage("WhiteMage1", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    whitemage2 = new WhiteMage("WhiteMage2", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    whitemage3 = new WhiteMage("WhiteMage3", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
  }

  @BeforeAll
  static void setUpWeapons() throws InvalidStatValueException {
    sword = new Sword("Sword", 10, 10);
    axe = new Axe("Axe", 10, 10);
    knife = new Knife("Axe", 10, 10);
    staff = new Staff("Staff", 10, 10 ,10);
    bow = new Bow("Bow", 10, 10);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException {
    whitemage1.equip(new Staff("Staff", 10, 10, 20));
    whitemage2.equip(new Staff("Staff", 10, 20, 20));
    whitemage3.equip(new Staff("Staff", 10, 30, 20));
    assertEquals(10, whitemage1.getEquippedWeapon().getWeight());
    assertEquals(20, whitemage2.getEquippedWeapon().getWeight());
    assertEquals(30, whitemage3.getEquippedWeapon().getWeight());
    whitemage1.waitTurn();
    whitemage2.waitTurn();
    whitemage3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),whitemage1);
    assertEquals(queue.poll(),whitemage2);
    assertEquals(queue.poll(),whitemage3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(whitemage1.getMaxHp(), whitemage1.getCurrentHp());
    whitemage1.setCurrentHp(5);
    assertEquals(5, whitemage1.getCurrentHp());
    try {
      whitemage1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testEquip() {
    assertNull(whitemage1.getEquippedWeapon());
    whitemage1.equip(sword);
    assertNull(whitemage1.getEquippedWeapon());
    whitemage1.equip(axe);
    assertNull(whitemage1.getEquippedWeapon());
    whitemage1.equip(knife);
    assertNull(whitemage1.getEquippedWeapon());
    whitemage1.equip(staff);
    assertEquals(staff, whitemage1.getEquippedWeapon());
    whitemage1.equip(bow);
    assertEquals(staff, whitemage1.getEquippedWeapon());
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(whitemage1, whitemage1);
    assertEquals(whitemage2, whitemage2);
    assertEquals(whitemage3, whitemage3);
    assertEquals(whitemage1, new WhiteMage(whitemage1.getName(), whitemage1.getMaxHp(),
        whitemage1.getDefense(), whitemage1.getMaxMp(), queue));
    assertEquals(whitemage2, new WhiteMage(whitemage2.getName(), whitemage2.getMaxHp(),
        whitemage2.getDefense(), whitemage2.getMaxMp(), queue));
    assertEquals(whitemage3, new WhiteMage(whitemage3.getName(), whitemage3.getMaxHp(),
        whitemage3.getDefense(), whitemage3.getMaxMp(), queue));
    assertNotEquals(whitemage1, whitemage2);
    assertNotEquals(whitemage3, whitemage2);
    assertNotEquals(whitemage1, whitemage3);
  }

  @Test
  void testToString() {
    assertEquals(whitemage1.toString(), "WhiteMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(whitemage1.getMaxMp(), whitemage1.getMaxHp(), whitemage1.getDefense(), whitemage1.getName()));
    assertEquals(whitemage2.toString(), "WhiteMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(whitemage2.getMaxMp(), whitemage2.getMaxHp(), whitemage2.getDefense(), whitemage2.getName()));
    assertEquals(whitemage3.toString(), "WhiteMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(whitemage3.getMaxMp(), whitemage3.getMaxHp(), whitemage3.getDefense(), whitemage3.getName()));
    assertNotEquals(whitemage1.toString(), whitemage2.toString());
    assertNotEquals(whitemage1.toString(), whitemage3.toString());
    assertNotEquals(whitemage2.toString(), whitemage3.toString());
  }
}