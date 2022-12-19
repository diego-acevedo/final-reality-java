package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BowTest {
  Bow bow1;
  Bow bow2;
  Bow bow3;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    bow1 = new Bow("Bow1", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    bow2 = new Bow("Bow2", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    bow3 = new Bow("Bow3", rnd.nextInt(10,100), rnd.nextInt(10, 100));
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertNotEquals(bow1, bow2);
    assertNotEquals(bow2, bow3);
    assertNotEquals(bow1, bow3);
    assertEquals(bow1, new Bow(bow1.getName(), bow1.getDamage(), bow1.getWeight()));
    assertEquals(bow2, new Bow(bow2.getName(), bow2.getDamage(), bow2.getWeight()));
    assertEquals(bow3, new Bow(bow3.getName(), bow3.getDamage(), bow3.getWeight()));
    assertEquals(bow1, bow1);
    assertEquals(bow2, bow2);
    assertEquals(bow3, bow3);
  }

  @Test
  void testToString() {
    assertEquals(bow1.toString(),"Bow{name='%s', damage=%d, weight=%d}"
        .formatted(bow1.getName(), bow1.getDamage(), bow1.getWeight()));
    assertEquals(bow2.toString(),"Bow{name='%s', damage=%d, weight=%d}"
        .formatted(bow2.getName(), bow2.getDamage(), bow2.getWeight()));
    assertEquals(bow3.toString(),"Bow{name='%s', damage=%d, weight=%d}"
        .formatted(bow3.getName(), bow3.getDamage(), bow3.getWeight()));
    assertNotEquals(bow2.toString(),bow3.toString());
    assertNotEquals(bow3.toString(),bow1.toString());
    assertNotEquals(bow1.toString(),bow2.toString());
  }

  @Test
  void testStats() {
    String stats1 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(bow1.getClass().getSimpleName(), bow1.getDamage(), bow1.getWeight());
    assertEquals(stats1, bow1.getStats());
    String stats2 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(bow2.getClass().getSimpleName(), bow2.getDamage(), bow2.getWeight());
    assertEquals(stats2, bow2.getStats());
    String stats3 = """
        Type: %s
        Damage: %d
        Weight: %d"""
        .formatted(bow3.getClass().getSimpleName(), bow3.getDamage(), bow3.getWeight());
    assertEquals(stats3, bow3.getStats());
  }
}