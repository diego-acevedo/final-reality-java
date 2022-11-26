package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AxeTest {
  Axe axe1;
  Axe axe2;
  Axe axe3;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    axe1 = new Axe("Axe1", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    axe2 = new Axe("Axe2", rnd.nextInt(10,100), rnd.nextInt(10, 100));
    axe3 = new Axe("Axe3", rnd.nextInt(10,100), rnd.nextInt(10, 100));
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertNotEquals(axe1, axe2);
    assertNotEquals(axe2, axe3);
    assertNotEquals(axe1, axe3);
    assertEquals(axe1, new Axe(axe1.getName(), axe1.getDamage(), axe1.getWeight()));
    assertEquals(axe2, new Axe(axe2.getName(), axe2.getDamage(), axe2.getWeight()));
    assertEquals(axe3, new Axe(axe3.getName(), axe3.getDamage(), axe3.getWeight()));
    assertEquals(axe1, axe1);
    assertEquals(axe2, axe2);
    assertEquals(axe3, axe3);
  }

  @Test
  void testToString() {
    assertEquals(axe1.toString(),"Axe{name='%s', damage=%d, weight=%d}"
        .formatted(axe1.getName(), axe1.getDamage(), axe1.getWeight()));
    assertEquals(axe2.toString(),"Axe{name='%s', damage=%d, weight=%d}"
        .formatted(axe2.getName(), axe2.getDamage(), axe2.getWeight()));
    assertEquals(axe3.toString(),"Axe{name='%s', damage=%d, weight=%d}"
        .formatted(axe3.getName(), axe3.getDamage(), axe3.getWeight()));
    assertNotEquals(axe2.toString(),axe3.toString());
    assertNotEquals(axe3.toString(),axe1.toString());
    assertNotEquals(axe1.toString(),axe2.toString());
  }
}