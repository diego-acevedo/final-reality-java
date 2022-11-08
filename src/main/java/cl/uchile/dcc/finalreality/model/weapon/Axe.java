package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.Objects;

/**
 * A class that holds the information of a {@link Weapon} that's an Axe.
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates an axe with a name, a base damage and its weight.
   *
   * @param name
   *     the weapon's name.
   * @param damage
   *     the weapon's damage.
   * @param weight
   *     the weapon's weight.
   * @throws InvalidStatValueException the weapon's weight can't be less than 1.
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
            && this.getDamage() == weapon.getDamage()
            && this.getWeight() == weapon.getWeight()
            && this.getName().equals(weapon.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(Axe.class, this.getName(), this.getDamage(), this.getWeight());
  }

  @Override
  public String toString() {
    return "Axe" + super.toString();
  }

  @Override
  public Weapon equipToThief(Thief thief) {
    return thief.getEquippedWeapon();
  }

  @Override
  public Weapon equipToBlackMage(BlackMage blackmage) {
    return blackmage.getEquippedWeapon();
  }

  @Override
  public Weapon equipToWhiteMage(WhiteMage whitemage) {
    return whitemage.getEquippedWeapon();
  }
}
