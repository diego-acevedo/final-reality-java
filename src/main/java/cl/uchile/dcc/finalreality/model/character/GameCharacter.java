package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface GameCharacter {

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns this character's current HP.
   */
  int getCurrentHp();

  /**
   * Returns this character's max HP.
   */
  int getMaxHp();

  /**
   * Returns this character's defense.
   */
  int getDefense();

  /**
   * Sets this character's current HP to {@code newHp}.
   */
  void setCurrentHp(int hp) throws InvalidStatValueException;

  /**
   * Starts a cooldown for a {@link GameCharacter} to be able to attack again.
   */
  void waitTurn();

  /**
   * Makes the {@link Character} recieve the effect of a {@link Spell}.
   *
   * @param spell The {@link Spell} being cast.
   * @param mage The {@link Mage} who cast the {@link Spell}.
   * @param weapon The {@link cl.uchile.dcc.finalreality.model.weapon.Weapon}
   *               used to cast the {@link Spell}.
   * @throws InvalidTargetCharacterException The targeted {@link Character}
   *                                         must be able to receive the {@link Spell}.
   * @throws InvalidStatValueException The HP and MP values changed must be valid.
   */
  void receiveSpell(Spell spell, Mage mage, MagicWeapon weapon)
      throws InvalidTargetCharacterException, InvalidStatValueException;

}
