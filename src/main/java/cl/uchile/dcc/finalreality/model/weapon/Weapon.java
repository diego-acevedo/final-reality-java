package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
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
  Weapon equipToKnight(Knight knight) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link Engineer}.
   *
   * @param engineer Character {@link Engineer} who will be equipped this weapon.
   */
  Weapon equipToEngineer(Engineer engineer) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link Thief}.
   *
   * @param thief Character {@link Thief} who will be equipped this weapon.
   */
  Weapon equipToThief(Thief thief) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link BlackMage}.
   *
   * @param blackmage Character {@link BlackMage} who will be equipped this weapon.
   */
  Weapon equipToBlackMage(BlackMage blackmage) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link WhiteMage}.
   *
   * @param whitemage Character {@link WhiteMage} who will be equipped this weapon.
   */
  Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidEquipableWeaponException;

  /**
   * Tries to cast a {@link Spell} by a {@link Mage} to some {@link Character}.
   *
   * @param character Character who will receive the effect of the spell.
   * @param spell Spell being cast.
   * @param mage Mage who is casting the spell.
   * @throws InvalidMagicWeaponException Weapon used must be a {@link MagicWeapon}.
   * @throws InvalidMageException The {@link Mage} used does not know the {@link Spell} used.
   * @throws InvalidStatValueException The MP and HP changed needs to be valid.
   * @throws InvalidTargetCharacterException The {@link Character} being target cannot receive
   *     this {@link Spell}.
   * @throws InvalidManaValueException The {@link Mage} does not have enough MP.
   */
  void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException,
      InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException;

  boolean isNull();
}
