package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NullWeaponTest {

  NullWeapon weapon1;
  NullWeapon weapon2;
  NullWeapon weapon3;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    Random rnd = new Random();

    weapon1 = new NullWeapon();
    weapon2 = new NullWeapon();
    weapon3 = new NullWeapon();
  }

  @Test
  void equalsTest() {
    assertEquals(weapon1, weapon1);
    assertEquals(weapon2, weapon2);
    assertEquals(weapon3, weapon3);

    assertEquals(weapon1, weapon2);
    assertEquals(weapon1, weapon3);

    assertEquals(weapon2, weapon1);
    assertEquals(weapon2, weapon3);

    assertEquals(weapon3, weapon1);
    assertEquals(weapon3, weapon2);
  }

  @Test
  void testStats() {
    String stats1 = "";
    assertEquals(stats1, weapon1.getStats());
    String stats2 = "";
    assertEquals(stats2, weapon2.getStats());
    String stats3 = "";
    assertEquals(stats3, weapon3.getStats());
  }

}