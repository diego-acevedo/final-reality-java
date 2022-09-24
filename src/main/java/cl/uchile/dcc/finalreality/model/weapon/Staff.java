package cl.uchile.dcc.finalreality.model.weapon;

import java.util.Objects;

public class Staff extends AbstractWeapon {
  private final int magicDamage;
  /**
   * Creates a staff with a name, a base damage, weight and its magic damage.
   */
  public Staff(String name, int damage, int weight, int magicDamage) {
    super(name, damage, weight);
    this.magicDamage = magicDamage;
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
    return Objects.hash(Staff.class, this.getName(), this.getDamage(), this.getWeight(), magicDamage);
  }

  @Override
  public String toString() {
    return "Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
            .formatted(this.getName(), this.getDamage(), this.getWeight(), magicDamage);
  }
}
