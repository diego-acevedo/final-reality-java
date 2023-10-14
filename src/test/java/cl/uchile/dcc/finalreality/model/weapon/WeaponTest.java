package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
  Weapon axe;
  Weapon bow;
  Weapon knife;
  Weapon staff;
  Weapon sword;
  Weapon nullWeapon;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    axe = new Axe("weapon", 10, 10);
    bow = new Bow("weapon", 10, 10);
    knife = new Knife("weapon", 10, 10);
    staff = new Staff("weapon", 10, 10, 10);
    sword = new Sword("weapon", 10, 10);
    nullWeapon = new NullWeapon();
  }

  @Test
  void testEqualsBetweenWeapons() {
    assertNotEquals(axe, bow);
    assertNotEquals(axe, knife);
    assertNotEquals(axe, staff);
    assertNotEquals(axe, sword);

    assertNotEquals(bow, axe);
    assertNotEquals(bow, knife);
    assertNotEquals(bow, staff);
    assertNotEquals(bow, sword);

    assertNotEquals(knife, axe);
    assertNotEquals(knife, bow);
    assertNotEquals(knife, staff);
    assertNotEquals(knife, sword);

    assertNotEquals(staff, axe);
    assertNotEquals(staff, bow);
    assertNotEquals(staff, knife);
    assertNotEquals(staff, sword);

    assertNotEquals(sword, axe);
    assertNotEquals(sword, bow);
    assertNotEquals(sword, knife);
    assertNotEquals(sword, staff);

    assertNotEquals(nullWeapon, axe);
    assertNotEquals(nullWeapon, bow);
    assertNotEquals(nullWeapon, knife);
    assertNotEquals(nullWeapon, staff);
    assertNotEquals(nullWeapon, sword);
  }

}