package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

import java.util.Random;

public abstract class TemplateWeaponFactory implements AbstractWeaponFactory {

  private int n = 1;
  private String name;
  private int damage;
  private int weight;
  protected final Random rnd = new Random();

  protected void setName(String weaponClass) {
    this.name = "%s %d".formatted(weaponClass, this.n);
    this.n++;
  }

  protected void setDamage() {
    this.damage = rnd.nextInt(50, 100);
  }

  protected void setWeight() {
    this.weight = rnd.nextInt(10, 100);
  }

  protected String getName() {
    return this.name;
  }

  protected int getDamage() {
    return this.damage;
  }

  protected int getWeight() {
    return this.weight;
  }

  protected abstract void setValues();

  protected abstract Weapon newWeapon() throws InvalidStatValueException;

  @Override
  public Weapon create() throws InvalidStatValueException {
    this.setValues();
    return this.newWeapon();
  }
}
