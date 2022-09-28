package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon} that's a Staff.
 */

public class Staff extends AbstractWeapon {
  private final int magicDamage;

  /**
   * Creates a staff with a name, a base damage, weight and its magic damage.
   */
  public Staff(String name, int damage, int weight, int magicDamage)
      throws InvalidStatValueException {
    super(name, damage, weight);
    Require.statValueAtLeast(0, magicDamage, "Magic Damage");
    this.magicDamage = magicDamage;
  }

  /**
   * Returns the weapon's magic damage.
   */
  public int getMagicDamage() {
    return magicDamage;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Staff weapon)) {
      return false;
    }
    return hashCode() == weapon.hashCode()
            && this.getDamage() == weapon.getDamage()
            && this.getWeight() == weapon.getWeight()
            && this.getName().equals(weapon.getName())
            && this.magicDamage == weapon.magicDamage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Staff.class, this.getName(), this.getDamage(),
            this.getWeight(), magicDamage);
  }

  @Override
  public String toString() {
    return "Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
            .formatted(this.getName(), this.getDamage(), this.getWeight(), magicDamage);
  }
}
