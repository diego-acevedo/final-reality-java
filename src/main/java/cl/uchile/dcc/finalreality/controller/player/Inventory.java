package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.controller.factories.weapon.AbstractWeaponFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.AxeFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.BowFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.KnifeFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.StaffFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.SwordFactory;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds all the information of the {@link Player}'s inventory.
 */
public class Inventory {

  private final List<Weapon> items;

  /**
   * Initialize all weapons in the inventory randomly.
   *
   * @throws InvalidStatValueException Weapons values must be valid.
   */
  public Inventory() throws InvalidStatValueException {
    this.items = new ArrayList<>();
    this.initialize(new AxeFactory());
    this.initialize(new BowFactory());
    this.initialize(new KnifeFactory());
    this.initialize(new StaffFactory());
    this.initialize(new SwordFactory());
  }

  /**
   * Adds a new item to the inventory.
   *
   * @param weapon Weapon being added to the inventory.
   */
  public void addItem(Weapon weapon) {
    items.add(weapon);
  }

  /**
   * Returns a {@link Weapon} with the right index given.
   *
   * @param n Index of the {@link Weapon}.
   * @return A {@link Weapon} from the {@link Inventory} with an index n.
   */
  public Weapon getWeapon(int n) {
    return items.get(n);
  }

  /**
   * Creates a series of {@link Weapon} to be added to the inventory.
   *
   * @param factory Factory that creates the {@link Weapon}
   * @throws InvalidStatValueException Values must be valid.
   */
  public void initialize(AbstractWeaponFactory factory)
      throws InvalidStatValueException {
    for (int i = 0; i < 4; i++) {
      this.addItem(factory.create());
    }
  }
}
