package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
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
   * Starts a cooldown for a {@link GameCharacter character} to be able to attack again.
   */
  void waitTurn();

  /**
   * Makes the {@link Character character} recieve the effect of a {@link Spell spell}.
   *
   * @param spell The {@link Spell spell} being cast.
   * @param mage The {@link Mage mage} who cast the {@link Spell spell}.
   * @param weapon The {@link cl.uchile.dcc.finalreality.model.weapon.Weapon weapon}
   *               used to cast the {@link Spell spell}.
   * @throws InvalidTargetCharacterException The targeted {@link Character character}
   *                                         must be able to receive the {@link Spell spell}.
   * @throws InvalidStatValueException The HP and MP values changed must be valid.
   */
  void receiveSpell(Spell spell, Mage mage, MagicWeapon weapon)
      throws InvalidTargetCharacterException, InvalidStatValueException;

  /**
   * Attacks the targeted {@link Character character}.
   *
   * @param character The {@link Character character} being targeted.
   * @throws InvalidTargetCharacterException Characters cannot attack their own kind.
   * @throws InvalidStatValueException New Hp value needs to be valid.
   */
  void attack(GameCharacter character)
      throws InvalidTargetCharacterException, InvalidStatValueException, NullWeaponException;

  /**
   * Handles the behaivor of recieving an attack from a {@link PlayerCharacter player character}.
   *
   * @param character {@link PlayerCharacter Player character} executing the attack.
   * @throws InvalidTargetCharacterException The {@link PlayerCharacter character}
   *                                         must attack a valid target.
   * @throws InvalidStatValueException The values must be valid.
   */
  void getAttackFromPlayerCharacter(PlayerCharacter character)
      throws InvalidTargetCharacterException, InvalidStatValueException;

  /**
   * Handles the behaivor of recieving an attack from an {@link Enemy enemy}.
   *
   * @param enemy {@link Enemy Enemy} executing the attack.
   * @throws InvalidTargetCharacterException The {@link Enemy enemy}
   *                                         must attack a valid target.
   * @throws InvalidStatValueException The values must be valid.
   */
  void getAttackFromEnemy(Enemy enemy)
      throws InvalidTargetCharacterException, InvalidStatValueException;

  /**
   * Checks if the {@link GameCharacter character} is alive.
   *
   * @return True if it's alive, false if it's not.
   */
  boolean isAlive();

  /**
   * Checks if the character is alive, if it's dead, it changes its status and notify the
   * {@link cl.uchile.dcc.finalreality.controller.GameDriver driver}.
   */
  void checkHealth();

  /**
   * Returns true if this character is playable, or false if it's not.
   *
   * @return True if this character is playable, or false if it's not.
   */
  boolean isPlayable();
}
