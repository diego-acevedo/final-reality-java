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
 * A class that holds all the information of the {@link Player player}'s
 * {@link Inventory inventory}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Inventory {

  private final List<Weapon> items;

  /**
   * Initialize all {@link Weapon weapons} in the {@link Inventory inventory} randomly.
   *
   * @throws InvalidStatValueException {@link Weapon Weapons} values must be valid.
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
   * Adds a new {@link Weapon item} to the {@link Inventory inventory}.
   *
   * @param weapon {@link Weapon Item} being added to the {@link Inventory inventory}.
   */
  public void addItem(Weapon weapon) {
    items.add(weapon);
  }

  /**
   * Returns a {@link Weapon weapon} with the right index given.
   *
   * @param n Index of the {@link Weapon weapon}.
   * @return A {@link Weapon weapon} from the {@link Inventory inventory} with an index n.
   */
  public Weapon getWeapon(int n) {
    return items.get(n);
  }

  /**
   * Creates a series of {@link Weapon weapons} to be added to the {@link Inventory inventory}.
   *
   * @param factory Factory that creates the {@link Weapon weapons}.
   * @throws InvalidStatValueException Values must be valid.
   */
  public void initialize(AbstractWeaponFactory factory)
      throws InvalidStatValueException {
    for (int i = 0; i < 4; i++) {
      this.addItem(factory.create());
    }
  }

  /**
   * Returns the {@link Weapon items} being hold by the {@link Inventory inventory}.
   *
   * @return The {@link Weapon items} being hold by the {@link Inventory inventory}.
   */
  public List<Weapon> getItems() {
    return items;
  }
}
