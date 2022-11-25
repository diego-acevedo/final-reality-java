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

public class Inventory {

  private final List<Weapon> items;

  public Inventory() throws InvalidStatValueException {
    this.items = new ArrayList<>();
    AbstractWeaponFactory axeFactory = new AxeFactory();
    AbstractWeaponFactory bowFactory = new BowFactory();
    AbstractWeaponFactory knifeFactory = new KnifeFactory();
    AbstractWeaponFactory staffFactory = new StaffFactory();
    AbstractWeaponFactory swordFactory = new SwordFactory();
    this.initialize(axeFactory);
    this.initialize(bowFactory);
    this.initialize(knifeFactory);
    this.initialize(staffFactory);
    this.initialize(swordFactory);
  }

  public void addItem(Weapon weapon) {
    items.add(weapon);
  }

  public Weapon getWeapon(int n) {
    return items.get(n);
  }

  public void initialize(AbstractWeaponFactory factory)
      throws InvalidStatValueException {
    for (int i = 0; i < 4; i++) {
      this.addItem(factory.create());
    }
  }
}
