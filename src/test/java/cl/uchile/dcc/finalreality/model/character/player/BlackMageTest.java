package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Knife;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class BlackMageTest {

  BlackMage blackmage1;
  BlackMage blackmage2;
  BlackMage blackmage3;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    blackmage1 = new BlackMage("WhiteMage1", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    blackmage2 = new BlackMage("WhiteMage2", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    blackmage3 = new BlackMage("WhiteMage3", rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException {
    blackmage1.equip(new Knife("Knife", 10, 10));
    blackmage2.equip(new Knife("Knife", 10, 20));
    blackmage3.equip(new Knife("Knife", 10, 30));
    assertEquals(10, blackmage1.getEquippedWeapon().getWeight());
    assertEquals(20, blackmage2.getEquippedWeapon().getWeight());
    assertEquals(30, blackmage3.getEquippedWeapon().getWeight());
    blackmage1.waitTurn();
    blackmage2.waitTurn();
    blackmage3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),blackmage1);
    assertEquals(queue.poll(),blackmage2);
    assertEquals(queue.poll(),blackmage3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(blackmage1.getMaxHp(), blackmage1.getCurrentHp());
    blackmage1.setCurrentHp(5);
    assertEquals(5, blackmage1.getCurrentHp());
    try {
      blackmage1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(blackmage1, blackmage1);
    assertEquals(blackmage2, blackmage2);
    assertEquals(blackmage3, blackmage3);
    assertEquals(blackmage1, new BlackMage(blackmage1.getName(), blackmage1.getMaxHp(),
        blackmage1.getDefense(), blackmage1.getMaxMp(), queue));
    assertEquals(blackmage2, new BlackMage(blackmage2.getName(), blackmage2.getMaxHp(),
        blackmage2.getDefense(), blackmage2.getMaxMp(), queue));
    assertEquals(blackmage3, new BlackMage(blackmage3.getName(), blackmage3.getMaxHp(),
        blackmage3.getDefense(), blackmage3.getMaxMp(), queue));
    assertNotEquals(blackmage1, blackmage2);
    assertNotEquals(blackmage3, blackmage2);
    assertNotEquals(blackmage1, blackmage3);
  }

  @Test
  void testToString() {
    assertEquals(blackmage1.toString(), "BlackMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(blackmage1.getMaxMp(), blackmage1.getMaxHp(), blackmage1.getDefense(), blackmage1.getName()));
    assertEquals(blackmage2.toString(), "BlackMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(blackmage2.getMaxMp(), blackmage2.getMaxHp(), blackmage2.getDefense(), blackmage2.getName()));
    assertEquals(blackmage3.toString(), "BlackMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(blackmage3.getMaxMp(), blackmage3.getMaxHp(), blackmage3.getDefense(), blackmage3.getName()));
    assertNotEquals(blackmage1.toString(), blackmage2.toString());
    assertNotEquals(blackmage1.toString(), blackmage3.toString());
    assertNotEquals(blackmage2.toString(), blackmage3.toString());
  }
}