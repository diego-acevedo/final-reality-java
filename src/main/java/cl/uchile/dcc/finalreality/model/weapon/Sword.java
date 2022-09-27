package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;

import java.util.Objects;

public class Sword extends AbstractWeapon {
  /**
   * Creates a sword with a name, a base damage and its weight.
   */
  public Sword(final String name, final int damage, final int weight) throws InvalidStatValueException {
    super(name, damage, weight);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Sword weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
            && this.getDamage() == weapon.getDamage()
            && this.getWeight() == weapon.getWeight()
            && this.getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(Sword.class, this.getName(), this.getDamage(), this.getWeight());
  }

  @Override
  public String toString() {
    return "Sword" + super.toString();
  }
}
