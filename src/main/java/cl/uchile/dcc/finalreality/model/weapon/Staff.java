package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon} that's a Staff.
 */

public class Staff extends AbstractWeapon implements MagicWeapon {

  private final int magicDamage;

  /**
   * Creates a staff with a name, a base damage, weight and its magic damage.
   *
   * @param name
   *     the weapon's name.
   * @param damage
   *     the weapon's damage.
   * @param weight
   *     the weapon's weight.
   * @param magicDamage
   *     the weapon's magic damage.
   * @throws InvalidStatValueException the weapon's weight can't be less than 1.
   */
  public Staff(String name, int damage, int weight, final int magicDamage)
      throws InvalidStatValueException {
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
            && this.getMagicDamage() == weapon.getMagicDamage();
  }

  @Override
  public int hashCode() {
    return Objects.hash(Staff.class, this.getName(), this.getDamage(),
            this.getWeight(), this.getMagicDamage());
  }

  @Override
  public String toString() {
    return "Staff{name='%s', damage=%d, weight=%d, magicDamage=%d}"
            .formatted(this.getName(), this.getDamage(), this.getWeight(), this.getMagicDamage());
  }

  /**
   * Returns this magic weapon's magic damage.
   */
  @Override
  public int getMagicDamage() {
    return magicDamage;
  }

  @Override
  public Weapon equipToKnight(Knight knight) {
    return knight.getEquippedWeapon();
  }

  @Override
  public Weapon equipToEngineer(Engineer engineer) {
    return engineer.getEquippedWeapon();
  }

  @Override
  public Weapon equipToThief(Thief thief) {
    return thief.getEquippedWeapon();
  }

  @Override
  public Weapon equipToBlackMage(BlackMage blackmage) {
    return this;
  }

  @Override
  public Weapon equipToWhiteMage(WhiteMage whitemage) {
    return this;
  }
}
