package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {
  Staff staff1;
  Staff staff2;
  Staff staff3;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    staff1 = new Staff("Knife1", rnd.nextInt(10,100),
        rnd.nextInt(10, 100), rnd.nextInt(10,100));
    staff2 = new Staff("Knife2", rnd.nextInt(10,100),
        rnd.nextInt(10, 100), rnd.nextInt(10,100));
    staff3 = new Staff("Knife3", rnd.nextInt(10,100),
        rnd.nextInt(10, 100), rnd.nextInt(10,100));
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertNotEquals(staff1, staff2);
    assertNotEquals(staff2, staff3);
    assertNotEquals(staff1, staff3);
    assertEquals(staff1, new Staff(staff1.getName(), staff1.getDamage(),
        staff1.getWeight(), staff1.getMagicDamage()));
    assertEquals(staff2, new Staff(staff2.getName(), staff2.getDamage(),
        staff2.getWeight(), staff2.getMagicDamage()));
    assertEquals(staff3, new Staff(staff3.getName(), staff3.getDamage(),
        staff3.getWeight(), staff3.getMagicDamage()));
    assertEquals(staff1, staff1);
    assertEquals(staff2, staff2);
    assertEquals(staff3, staff3);
  }

  @Test
  void testToString() {
    assertEquals(staff1.toString(),"Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
        .formatted(staff1.getName(), staff1.getDamage(), staff1.getWeight(), staff1.getMagicDamage()));
    assertEquals(staff2.toString(),"Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
        .formatted(staff2.getName(), staff2.getDamage(), staff2.getWeight(), staff2.getMagicDamage()));
    assertEquals(staff3.toString(),"Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
        .formatted(staff3.getName(), staff3.getDamage(), staff3.getWeight(), staff3.getMagicDamage()));
    assertNotEquals(staff2.toString(),staff3.toString());
    assertNotEquals(staff3.toString(),staff1.toString());
    assertNotEquals(staff1.toString(),staff2.toString());
  }
}