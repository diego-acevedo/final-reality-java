package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class AbstractCharacter implements GameCharacter {

  private int currentHp;
  private final int maxHp;
  private final int defense;
  private final BlockingQueue<GameCharacter> turnsQueue;
  private final String name;
  private boolean alive;
  protected ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param maxHp
   *     the character's max hp
   * @param defense
   *     the character's defense
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   * @throws InvalidStatValueException maxHp must be greater than 1
   *     and defense must be greater than 0
   */
  protected AbstractCharacter(@NotNull String name, int maxHp, int defense,
      @NotNull BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException {
    Require.statValueAtLeast(1, maxHp, "Max HP");
    Require.statValueAtLeast(0, defense, "Defense");
    this.maxHp = maxHp;
    this.currentHp = maxHp;
    this.defense = defense;
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.alive = true;
  }

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    try {
      turnsQueue.put(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public int getMaxHp() {
    return maxHp;
  }

  @Override
  public int getDefense() {
    return defense;
  }

  @Override
  public void setCurrentHp(int hp) throws InvalidStatValueException {
    Require.statValueAtLeast(0, hp, "Current HP");
    Require.statValueAtMost(maxHp, hp, "Current HP");
    currentHp = hp;
    this.checkHealth();
  }

  @Override
  public void getAttackFromEnemy(Enemy enemy)
      throws InvalidTargetCharacterException, InvalidStatValueException {
    throw new InvalidTargetCharacterException("This character cannot be attack by an enemy");
  }

  @Override
  public void getAttackFromPlayerCharacter(PlayerCharacter character)
      throws InvalidTargetCharacterException, InvalidStatValueException {
    throw new InvalidTargetCharacterException("This character cannot be attacked by"
        + "a player character");
  }

  @Override
  public boolean isAlive() {
    return this.alive;
  }

  @Override
  public void checkHealth() {
    if (this.currentHp == 0) {
      this.alive = false;
    }
  }
}
