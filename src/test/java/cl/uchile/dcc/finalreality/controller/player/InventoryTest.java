package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.controller.factories.weapon.*;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static cl.uchile.dcc.finalreality.controller.GameDriver.getGameDriver;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

  private Inventory inventory;
  private final long seed = (new Random()).nextLong();

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    getGameDriver(seed);
    getGameDriver(seed);
    inventory = new Inventory();
  }

  @Test
  void getWeapon() throws InvalidStatValueException {
    List<Weapon> expectedInventory = new ArrayList<>();
    getGameDriver(seed);
    AbstractWeaponFactory factory = new AxeFactory();
    for (int i = 0; i < 4; i++) {
      expectedInventory.add(factory.create());
    }
    factory = new BowFactory();
    for (int i = 0; i < 4; i++) {
      expectedInventory.add(factory.create());
    }
    factory = new KnifeFactory();
    for (int i = 0; i < 4; i++) {
      expectedInventory.add(factory.create());
    }
    factory = new StaffFactory();
    for (int i = 0; i < 4; i++) {
      expectedInventory.add(factory.create());
    }
    factory = new SwordFactory();
    for (int i = 0; i < 4; i++) {
      expectedInventory.add(factory.create());
    }
    for (int i = 0; i < expectedInventory.size(); i++) {
      assertEquals(expectedInventory.get(i), this.inventory.getWeapon(i));
    }
  }
}