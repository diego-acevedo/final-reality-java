package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.spell.Spell;

/**
 * This represents a weapon equipable by a
 * {@link cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter PlayerCharacter}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */

public interface Weapon {

  /**
   * Returns this weapon's name.
   */
  String getName();

  /**
   * Returns this weapon's damage.
   */
  int getDamage();

  /**
   * Returns this weapon's weight.
   */
  int getWeight();

  /**
   * Tries to equip this weapon to a {@link Knight}.
   *
   * @param knight Character {@link Knight} who will be equipped this weapon.
   */
  Weapon equipToKnight(Knight knight) throws InvalidStatValueException;

  /**
   * Tries to equip this weapon to a {@link Engineer}.
   *
   * @param engineer Character {@link Engineer} who will be equipped this weapon.
   */
  Weapon equipToEngineer(Engineer engineer) throws InvalidStatValueException;

  /**
   * Tries to equip this weapon to a {@link Thief}.
   *
   * @param thief Character {@link Thief} who will be equipped this weapon.
   */
  Weapon equipToThief(Thief thief) throws InvalidStatValueException;

  /**
   * Tries to equip this weapon to a {@link BlackMage}.
   *
   * @param blackmage Character {@link BlackMage} who will be equipped this weapon.
   */
  Weapon equipToBlackMage(BlackMage blackmage) throws InvalidStatValueException;

  /**
   * Tries to equip this weapon to a {@link WhiteMage}.
   *
   * @param whitemage Character {@link WhiteMage} who will be equipped this weapon.
   */
  Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidStatValueException;

  void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException,
      InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException;

}
