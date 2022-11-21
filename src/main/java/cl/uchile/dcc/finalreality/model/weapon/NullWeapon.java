package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.spell.Spell;

import java.util.Objects;

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
  public void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new InvalidMagicWeaponException(this + " cannot cast spells.");
  }
}
