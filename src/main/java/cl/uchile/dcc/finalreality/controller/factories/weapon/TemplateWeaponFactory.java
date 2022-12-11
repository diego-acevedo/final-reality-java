package cl.uchile.dcc.finalreality.controller.factories.weapon;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

/**
 * A class that holds the basic {@link AbstractWeaponFactory factory} logic.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class TemplateWeaponFactory implements AbstractWeaponFactory {

  private int weaponsCreated = 1;
  private String name;
  private int damage;
  private int weight;

  /**
   * Sets name to a random value.
   *
   * @param weaponClass The {@link Weapon weapon}'s class name.
   */
  protected void setName(String weaponClass) {
    this.name = "%s %d".formatted(weaponClass, this.weaponsCreated);
    this.weaponsCreated++;
  }

  /**
   * Sets damage to a random value.
   */
  protected void setDamage() {
    this.damage = RANDOM_GENERATOR.nextInt(50, 100);
  }

  /**
   * Sets weight to a random value.
   */
  protected void setWeight() {
    this.weight = RANDOM_GENERATOR.nextInt(10, 100);
  }

  /**
   * Returns the random generated value for name.
   *
   * @return The random generated value for name.
   */
  protected String getName() {
    return this.name;
  }

  /**
   * Returns the random generated value for damage.
   *
   * @return The random generated value for damage.
   */
  protected int getDamage() {
    return this.damage;
  }

  /**
   * Returns the random generated value for weight.
   *
   * @return The random generated value for weight.
   */
  protected int getWeight() {
    return this.weight;
  }

  /**
   * Assigns a new random generated value to all parameters.
   */
  protected abstract void setValues();

  /**
   * Creates a {@link Weapon weapon} with the random generated parameters.
   *
   * @return A new {@link Weapon weapon} with random parameters.
   * @throws InvalidStatValueException Values must be valid.
   */
  protected abstract Weapon newWeapon() throws InvalidStatValueException;

  @Override
  public Weapon create() throws InvalidStatValueException {
    this.setValues();
    return this.newWeapon();
  }
}
