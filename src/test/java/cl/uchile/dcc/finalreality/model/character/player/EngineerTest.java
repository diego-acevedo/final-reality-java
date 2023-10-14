package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.*;
import org.junit.jupiter.api.BeforeAll;
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
  static Sword sword;
  static Axe axe;
  static Knife knife;
  static Staff staff;
  static Bow bow;

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

  @BeforeAll
  static void setUpWeapons() throws InvalidStatValueException {
    sword = new Sword("Sword", 10, 10);
    axe = new Axe("Axe", 10, 10);
    knife = new Knife("Axe", 10, 10);
    staff = new Staff("Staff", 10, 10 ,10);
    bow = new Bow("Bow", 10, 10);
  }

  @Test
  void testWaitTurn() throws InterruptedException, InvalidStatValueException, InvalidEquipableWeaponException {
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
  void testEquip() throws InvalidStatValueException, InvalidEquipableWeaponException {
    assertEquals(new NullWeapon(), engineer1.getEquippedWeapon());
    assertThrows(InvalidEquipableWeaponException.class, () -> engineer1.equip(sword));
    assertEquals(new NullWeapon(), engineer1.getEquippedWeapon());
    engineer1.equip(axe);
    assertEquals(axe, engineer1.getEquippedWeapon());
    assertThrows(InvalidEquipableWeaponException.class, () -> engineer1.equip(knife));
    assertEquals(axe, engineer1.getEquippedWeapon());
    assertThrows(InvalidEquipableWeaponException.class, () -> engineer1.equip(staff));
    assertEquals(axe, engineer1.getEquippedWeapon());
    engineer1.equip(bow);
    assertEquals(bow, engineer1.getEquippedWeapon());
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

  @Test
  void spriteRowTest() {
    assertEquals(8, engineer1.getSpriteRow());
    assertEquals(8, engineer2.getSpriteRow());
    assertEquals(8, engineer3.getSpriteRow());
  }

  @Test
  void infoTest() throws InvalidStatValueException {
    assertEquals("HP: " + engineer1.getCurrentHp() + "/" + engineer1.getMaxHp(), engineer1.getInfo());
    engineer1.setCurrentHp((new Random()).nextInt(9, engineer1.getMaxHp()));
    assertEquals("HP: " + engineer1.getCurrentHp() + "/" + engineer1.getMaxHp(), engineer1.getInfo());
    engineer1.setCurrentHp((new Random()).nextInt(9, engineer1.getMaxHp()));
    assertEquals("HP: " + engineer1.getCurrentHp() + "/" + engineer1.getMaxHp(), engineer1.getInfo());
    engineer1.setCurrentHp((new Random()).nextInt(9, engineer1.getMaxHp()));
    assertEquals("HP: " + engineer1.getCurrentHp() + "/" + engineer1.getMaxHp(), engineer1.getInfo());
  }

  @Test
  void statsTest() {
    assertEquals("""
        Name: %s
        HP: %d
        Defense: %d"""
        .formatted(engineer1.getName(), engineer1.getCurrentHp(), engineer1.getDefense()), engineer1.getStats());
    assertEquals("""
        Name: %s
        HP: %d
        Defense: %d"""
        .formatted(engineer2.getName(), engineer2.getCurrentHp(), engineer2.getDefense()), engineer2.getStats());
    assertEquals("""
        Name: %s
        HP: %d
        Defense: %d"""
        .formatted(engineer3.getName(), engineer3.getCurrentHp(), engineer3.getDefense()), engineer3.getStats());
  }
}