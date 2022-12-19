package cl.uchile.dcc.finalreality.model_controller.model.weapon;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;

import java.util.Objects;

/**
 * A class that represents a null weapon. Does nothing.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */

public class NullWeapon extends AbstractWeapon {
  /**
   * Creates a null weapon.
   *
   * @throws InvalidStatValueException the weapon's weight can't be less than 1.
   */
  public NullWeapon() throws InvalidStatValueException {
    super("NullWeapon", 0, 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final NullWeapon weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
        && this.getDamage() == weapon.getDamage()
        && this.getWeight() == weapon.getWeight()
        && this.getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(NullWeapon.class, this.getName(), this.getDamage(), this.getWeight());
  }

  @Override
  public boolean isNull() {
    return true;
  }

  @Override
  public String getStats() {
    return "";
  }
}
