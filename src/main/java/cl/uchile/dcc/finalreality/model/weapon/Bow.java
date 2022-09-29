package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon} that's a Bow.
 */
public class Bow extends AbstractWeapon {

  /**
   * Creates a bow with a name, a base damage and its weight.
   */
  public Bow(String name, int damage, int weight) throws InvalidStatValueException {
    super(name, damage, weight);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Bow weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
            && this.damage == weapon.damage
            && this.weight == weapon.weight
            && this.name.equals(weapon.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Bow.class, this.name, this.damage, this.weight);
  }

  @Override
  public String toString() {
    return "Bow" + super.toString();
  }
}
