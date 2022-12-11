package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon weapon} that's a Sword.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */

public class Sword extends AbstractWeapon {

  /**
   * Creates a sword with a name, a base damage and its weight.
   *
   * @param name
   *     the weapon's name.
   * @param damage
   *     the weapon's damage.
   * @param weight
   *     the weapon's weight.
   * @throws InvalidStatValueException the weapon's weight can't be less than 1.
   */
  public Sword(final String name, final int damage, final int weight)
      throws InvalidStatValueException {
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

  @Override
  public Weapon equipToEngineer(Engineer engineer) throws InvalidEquipableWeaponException {
    throw new InvalidEquipableWeaponException("Sword cannot be equiped to engineer");
  }

  @Override
  public Weapon equipToBlackMage(BlackMage blackmage) throws InvalidEquipableWeaponException {
    throw new InvalidEquipableWeaponException("Sword cannot be equiped to black mage");
  }

  @Override
  public Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidEquipableWeaponException {
    throw new InvalidEquipableWeaponException("Sword cannot be equiped to white mage");
  }
}
