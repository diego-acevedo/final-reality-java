package cl.uchile.dcc.finalreality.modelcontroller.model.character.player;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.Require;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * Cointains the common behavior between mages.
 * Its constructor is <b>protected</b>, because it'll only be used by subclasses.
 */
public abstract class AbstractMage extends AbstractPlayerCharacter implements Mage {

  private int currentMp;
  private final int maxMp;

  /**
   * Creates a new mage.
   *
   * @param name
   *     this character's name.
   * @param maxHp
   *     this character's max health points.
   * @param defense
   *     this character's defense.
   * @param maxMp
   *     this mage's max mana points.
   * @param turnsQueue
   *     the combat queue that manages the turns.
   * @throws InvalidStatValueException maxMp must be greater than 0.
   *
   * @author <a href="https://www.github.com/r8vnhill">R8V</a>
   * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
   */
  protected AbstractMage(final @NotNull String name, final int maxHp, final int defense,
      int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    Require.statValueAtLeast(0, maxMp, "Max MP");
    this.maxMp = maxMp;
    this.currentMp = maxMp;
  }

  /**
   * Returns the character's current MP.
   */
  public int getCurrentMp() {
    return currentMp;
  }

  /**
   * Sets the character's current MP.
   */
  public void setCurrentMp(int currentMp) throws InvalidStatValueException {
    Require.statValueAtLeast(0, currentMp, "Current MP");
    Require.statValueAtMost(maxMp, currentMp, "Current MP");
    this.currentMp = currentMp;
  }

  /**
   * Returns the character's max MP.
   */
  public int getMaxMp() {
    return maxMp;
  }

  @Override
  public void useMagic(Spell spell, GameCharacter character)
      throws InvalidMagicWeaponException, InvalidMageException,
      InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException {
    this.getEquippedWeapon().castSpell(character, spell, this);
  }
}
