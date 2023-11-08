package cl.uchile.dcc.finalreality.modelcontroller.model.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Knight;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Thief;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;

/**
 * This represents a weapon equipable by a
 * {@link PlayerCharacter player character}.
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
   * Tries to equip this weapon to a {@link Knight knight}.
   *
   * @param knight Character {@link Knight knight} who will be equipped this weapon.
   */
  Weapon equipToKnight(Knight knight) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link Engineer engineer}.
   *
   * @param engineer Character {@link Engineer engineer} who will be equipped this weapon.
   */
  Weapon equipToEngineer(Engineer engineer) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link Thief thief}.
   *
   * @param thief Character {@link Thief thief} who will be equipped this weapon.
   */
  Weapon equipToThief(Thief thief) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link BlackMage black mage}.
   *
   * @param blackmage Character {@link BlackMage black mage} who will be equipped this weapon.
   */
  Weapon equipToBlackMage(BlackMage blackmage) throws InvalidEquipableWeaponException;

  /**
   * Tries to equip this weapon to a {@link WhiteMage white mage}.
   *
   * @param whitemage Character {@link WhiteMage white mage} who will be equipped this weapon.
   */
  Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidEquipableWeaponException;

  /**
   * Tries to cast a {@link Spell spell} by a {@link Mage mage} to some
   * {@link GameCharacter character}.
   *
   * @param character {@link GameCharacter character} who will receive the effect of the spell.
   * @param spell {@link Spell Spell} being cast.
   * @param mage {@link Mage Mage} who is casting the spell.
   * @throws InvalidMagicWeaponException Weapon used must be a {@link MagicWeapon magic weapon}.
   * @throws InvalidMageException The {@link Mage mage} used does not know the
   *                              {@link Spell spell} used.
   * @throws InvalidStatValueException The MP and HP changed needs to be valid.
   * @throws InvalidTargetCharacterException The {@link GameCharacter character} being target
   *                                         cannot receive this {@link Spell}.
   * @throws InvalidManaValueException The {@link Mage mage} does not have enough MP.
   */
  void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException,
      InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Returns true if the weapon is {@link NullWeapon null}.
   *
   * @return True if the weapon is {@link NullWeapon null}.
   */
  boolean isNull();

  /**
   * Returns the weapon's stats.
   *
   * @return Weapon's stats.
   */
  String getStats();
}