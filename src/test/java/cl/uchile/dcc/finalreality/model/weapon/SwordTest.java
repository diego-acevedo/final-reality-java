package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

  Sword sword1;
  Sword sword2;
  Sword sword3;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    sword1 = new Sword("Sword1", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    sword2 = new Sword("Sword2", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    sword3 = new Sword("Sword3", rnd.nextInt(10,100), rnd.nextInt(10, 100));
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertNotEquals(sword1, sword2);
    assertNotEquals(sword2, sword3);
    assertNotEquals(sword1, sword3);
    assertEquals(sword1, new Sword(sword1.getName(), sword1.getDamage(), sword1.getWeight()));
    assertEquals(sword2, new Sword(sword2.getName(), sword2.getDamage(), sword2.getWeight()));
    assertEquals(sword3, new Sword(sword3.getName(), sword3.getDamage(), sword3.getWeight()));
    assertEquals(sword1, sword1);
    assertEquals(sword2, sword2);
    assertEquals(sword3, sword3);
  }

  @Test
  void testToString() {
    assertEquals(sword1.toString(),"Sword{name='%s', damage=%d, weight=%d}"
        .formatted(sword1.getName(), sword1.getDamage(), sword1.getWeight()));
    assertEquals(sword2.toString(),"Sword{name='%s', damage=%d, weight=%d}"
        .formatted(sword2.getName(), sword2.getDamage(), sword2.getWeight()));
    assertEquals(sword3.toString(),"Sword{name='%s', damage=%d, weight=%d}"
        .formatted(sword3.getName(), sword3.getDamage(), sword3.getWeight()));
    assertNotEquals(sword2.toString(),sword3.toString());
    assertNotEquals(sword3.toString(),sword1.toString());
    assertNotEquals(sword1.toString(),sword2.toString());
  }

  @Test
  void testStats() {
    String stats1 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(sword1.getClass().getSimpleName(), sword1.getDamage(), sword1.getWeight());
    assertEquals(stats1, sword1.getStats());
    String stats2 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(sword2.getClass().getSimpleName(), sword2.getDamage(), sword2.getWeight());
    assertEquals(stats2, sword2.getStats());
    String stats3 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(sword3.getClass().getSimpleName(), sword3.getDamage(), sword3.getWeight());
    assertEquals(stats3, sword3.getStats());
  }
}