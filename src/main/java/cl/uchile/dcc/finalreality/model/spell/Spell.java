package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

/**
 * This represents a spell that can be cast by a {@link Mage}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface Spell {

  /**
   * Induce the {@link Spell}'s effect to an {@link Enemy}.
   *
   * @param character {@link Enemy} being affected by the {@link Spell}.
   * @param weapon {@link MagicWeapon} being used to cast the spell.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Spell} needs to work on an {@link Enemy}.
   */
  void induceEffectOnEnemy(Enemy character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  /**
   * Induce the {@link Spell}'s effect to an {@link PlayerCharacter}.
   *
   * @param character {@link PlayerCharacter} being affected by the {@link Spell}.
   * @param weapon {@link MagicWeapon} being used to cast the spell.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Spell} needs to work on an
   *     {@link PlayerCharacter}.
   */
  void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  /**
   * Check if the {@link Spell} can be cast by a
   * {@link cl.uchile.dcc.finalreality.model.character.player.BlackMage}. Throws exception if not.
   *
   * @param mage The {@link Mage} who cast the {@link Spell}.
   * @param character The {@link Character} being affeted by the {@link Spell}.
   * @param weapon The {@link cl.uchile.dcc.finalreality.model.weapon.Weapon} being used to cast
   *               the {@link Spell}
   * @throws InvalidMageException The {@link Mage} needs to know the {@link Spell}.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Character} being targeted cannot
   *                                         receive this {@link Spell}.
   * @throws InvalidManaValueException The {@link Mage} does not have enough mana.
   */
  void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Check if the {@link Spell} can be cast by a
   * {@link cl.uchile.dcc.finalreality.model.character.player.WhiteMage}. Throws exception if not.
   *
   * @param mage The {@link Mage} who cast the {@link Spell}.
   * @param character The {@link Character} being affeted by the {@link Spell}.
   * @param weapon The {@link cl.uchile.dcc.finalreality.model.weapon.Weapon} being used to cast
   *               the {@link Spell}
   * @throws InvalidMageException The {@link Mage} needs to know the {@link Spell}.
   * @throws InvalidStatValueException The HP and MP values changed need to be valid.
   * @throws InvalidTargetCharacterException The {@link Character} being targeted cannot
   *                                         receive this {@link Spell}.
   * @throws InvalidManaValueException The {@link Mage} does not have enough mana.
   */
  void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException;

  /**
   * Checks if the {@link Mage} casting the {@link Spell} has enough Mp to summon it.
   *
   * @param mageMana The {@link Mage}'s Mp.
   * @throws InvalidManaValueException The {@link Mage} does not have enough Mp.
   */
  void checkMana(int mageMana) throws InvalidManaValueException;
}
