package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

  private final List<Weapon> items;

  public Inventory() {
    this.items = new ArrayList<>();
  }

  public void addItem(Weapon weapon) {
    items.add(weapon);
  }

  public Weapon getWeapon(int n) {
    return items.get(n);
  }
}
