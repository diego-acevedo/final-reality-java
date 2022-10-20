package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class KnifeTest {

  Knife knife1;
  Knife knife2;
  Knife knife3;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    knife1 = new Knife("Knife1", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    knife2 = new Knife("Knife2", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    knife3 = new Knife("Knife3", rnd.nextInt(10,100), rnd.nextInt(10, 100));
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertNotEquals(knife1, knife2);
    assertNotEquals(knife2, knife3);
    assertNotEquals(knife1, knife3);
    assertEquals(knife1, new Knife(knife1.getName(), knife1.getDamage(), knife1.getWeight()));
    assertEquals(knife2, new Knife(knife2.getName(), knife2.getDamage(), knife2.getWeight()));
    assertEquals(knife3, new Knife(knife3.getName(), knife3.getDamage(), knife3.getWeight()));
    assertEquals(knife1, knife1);
    assertEquals(knife2, knife2);
    assertEquals(knife3, knife3);
  }

  @Test
  void testToString() {
    assertEquals(knife1.toString(),"Knife{name='%s', damage=%d, weight=%d}"
        .formatted(knife1.getName(), knife1.getDamage(), knife1.getWeight()));
    assertEquals(knife2.toString(),"Knife{name='%s', damage=%d, weight=%d}"
        .formatted(knife2.getName(), knife2.getDamage(), knife2.getWeight()));
    assertEquals(knife3.toString(),"Knife{name='%s', damage=%d, weight=%d}"
        .formatted(knife3.getName(), knife3.getDamage(), knife3.getWeight()));
    assertNotEquals(knife2.toString(),knife3.toString());
    assertNotEquals(knife3.toString(),knife1.toString());
    assertNotEquals(knife1.toString(),knife2.toString());
  }
}