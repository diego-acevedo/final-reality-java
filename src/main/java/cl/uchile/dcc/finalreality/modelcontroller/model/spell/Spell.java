package cl.uchile.dcc.finalreality.modelcontroller.model.spell;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * This represents a spell that can be cast by a {@link Mage}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface Spell {

  /**
   * Induce the {@link Spell spell}'s effect to an {@link Enemy enemy}.
   *
   * @param character {@link Enemy Spell} being affected by the {@link Spell spell}.
   * @param weapon {@link MagicWeapon Weapon} being used to cast the spell.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Spell spell} needs to work on an
   *                                         {@link Enemy enemy}.
   */
  void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  /**
   * Induce the {@link Spell spell}'s effect to an {@link PlayerCharacter player's character}.
   *
   * @param character {@link PlayerCharacter character} being affected by the {@link Spell spell}.
   * @param weapon {@link MagicWeapon weapon} being used to cast the spell.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Spell spell} needs to work on an
   *                                         {@link PlayerCharacter character}.
   */
  void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  /**
   * Check if the {@link Spell spell} can be cast by a
   * {@link BlackMage black mage}.
   * Throws exception if not.
   *
   * @param mage The {@link Mage mage} who cast the {@link Spell spell}.
   * @param character The {@link GameCharacter character} being affeted by the {@link Spell spell}.
   * @param weapon The {@link Weapon weapon} being used to
   *               cast the {@link Spell spell}
   * @throws InvalidMageException The {@link Mage mage} needs to know the {@link Spell spell}.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link GameCharacter character} being targeted
   *                                         cannot receive this {@link Spell spell}.
   * @throws InvalidManaValueException The {@link Mage mage} does not have enough mana.
   */
  void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Check if the {@link Spell spell} can be cast by a
   * {@link WhiteMage white mage}.
   * Throws exception if not.
   *
   * @param mage The {@link Mage mage} who cast the {@link Spell spell}.
   * @param character The {@link GameCharacter character} being affeted by the {@link Spell spell}.
   * @param weapon The {@link Weapon weapon}
   *               being used to cast the {@link Spell spell}
   * @throws InvalidMageException The {@link Mage mage} needs to know the {@link Spell spell}.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link GameCharacter character} being targeted
   *                                         cannot receive this {@link Spell spell}.
   * @throws InvalidManaValueException The {@link Mage mage} does not have enough mana.
   */
  void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Checks if the {@link Mage mage} casting the {@link Spell spell} has enough Mp to summon it.
   *
   * @param mageMana The {@link Mage mage}'s Mp.
   * @throws InvalidManaValueException The {@link Mage mage} does not have enough Mp.
   */
  void checkMana(int mageMana) throws InvalidManaValueException;

  /**
   * Returns the spell's description.
   *
   * @return Spell's description.
   */
  String description();
}
