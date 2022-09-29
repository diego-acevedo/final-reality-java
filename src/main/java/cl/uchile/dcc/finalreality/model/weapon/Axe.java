package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon} that's an Axe.
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates an axe with a name, a base damage and its weight.
   */
  public Axe(String name, int damage, int weight)  throws InvalidStatValueException {
    super(name, damage, weight);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Axe weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
            && this.damage == weapon.damage
            && this.weight == weapon.weight
            && this.name.equals(weapon.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Axe.class, this.name, this.damage, this.weight);
  }

  @Override
  public String toString() {
    return "Axe" + super.toString();
  }
}
