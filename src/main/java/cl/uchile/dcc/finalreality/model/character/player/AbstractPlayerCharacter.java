/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.NonMagicalCharacterException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.AbstractCharacter;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.model.weapon.NullWeapon;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a player-controlled character in the game.
 *
 * <p>All player characters have a {@code name}, a maximum amount of <i>hit points</i>
 * ({@code maxHp}), a {@code defense} value, a queue of {@link GameCharacter}s that are
 * waiting for their turn ({@code turnsQueue}), and can equip a {@link Weapon}.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements
    PlayerCharacter {

  private Weapon equippedWeapon;

  /**
   * Creates a new character.
   * This constructor is <b>protected</b>, because it'll only be used by subclasses.
   *
   * @param name
   *     the character's name.
   * @param maxHp
   *     the character's max hp.
   * @param defense
   *     the character's defense.
   * @param turnsQueue
   *     the queue with the characters waiting for their turn.
   */
  protected AbstractPlayerCharacter(@NotNull final String name, final int maxHp,
      final int defense, @NotNull final BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    this.equippedWeapon = new NullWeapon();
  }

  /**
   * Starts a cooldown for a {@link GameCharacter player character} to be able to
   * attack again. Cooldown depends on the PlayerCharacter's equipped weapon's weight.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(
            /* command = */ this::addToQueue,
            /* delay = */ this.getEquippedWeapon().getWeight() / 10,
            /* unit = */ TimeUnit.SECONDS);
  }

  @Override
  public Weapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Sets a new {@link Weapon weapon} to the character.
   *
   * @param weapon {@link Weapon Weapon} being equipped.
   */
  protected void setEquippedWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
  }

  @Override
  public void attack(GameCharacter character)
      throws InvalidTargetCharacterException, InvalidStatValueException, NullWeaponException {
    if (this.equippedWeapon.isNull()) {
      throw new NullWeaponException(this + "has no weapon equipped.");
    }
    character.getAttackFromPlayerCharacter(this);
  }

  @Override
  public void getAttackFromEnemy(Enemy enemy) throws InvalidStatValueException {
    int newHp = Math.max(0, this.getCurrentHp() - (enemy.getAttack() - this.getDefense()));
    this.setCurrentHp(newHp);
  }

  @Override
  public void useMagic(Spell spell, GameCharacter character)
      throws InvalidMagicWeaponException, NonMagicalCharacterException,
      InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new NonMagicalCharacterException(this + " is not a magical character.");
  }

  @Override
  public void receiveSpell(Spell spell, Mage mage, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    spell.induceEffectOnPlayerCharacter(this, weapon);
  }

  @Override
  public boolean isPlayable() {
    return true;
  }
}
