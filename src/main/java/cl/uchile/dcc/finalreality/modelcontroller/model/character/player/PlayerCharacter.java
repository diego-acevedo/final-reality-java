package cl.uchile.dcc.finalreality.modelcontroller.model.character.player;

/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.NonMagicalCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * A {@link GameCharacter} that can equip a weapon.
 */
public interface PlayerCharacter extends GameCharacter {

  /**
   * Equips a weapon to the character.
   */
  void equip(Weapon weapon) throws InvalidEquipableWeaponException;

  /**
   * Return this character's equipped weapon.
   */
  Weapon getEquippedWeapon();

  /**
   * Makes the character cast a {@link Spell spell} to a {@link GameCharacter character}.
   *
   * @param spell {@link Spell Spell} being cast.
   * @param character {@link Character Character} receiving the {@link Spell spell}.
   * @throws InvalidMagicWeaponException The spell must be cast by a
   *     {@link MagicWeapon magic weapon}.
   * @throws NonMagicalCharacterException The spell must be cast by a {@link Mage mage}.
   * @throws InvalidMageException The spell must be cast by the right {@link Mage mage}.
   * @throws InvalidStatValueException The values must be valid.
   * @throws InvalidTargetCharacterException The targeted {@link GameCharacter character}
   *                                         must be valid.
   * @throws InvalidManaValueException The {@link Mage mage} must have enough mana points.
   */
  void useMagic(Spell spell, GameCharacter character)
      throws InvalidMagicWeaponException, NonMagicalCharacterException, InvalidMageException,
      InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Returns the row where the character's texture is stored.
   *
   * @return Row where the sprite is.
   */
  int getSpriteRow();
}
