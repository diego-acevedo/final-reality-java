package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int weight, int maxHp, int defense,
      @NotNull final BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    Require.statValueAtLeast(1, weight, "Weight");
    this.weight = weight;
  }

  /**
   * Starts a cooldown for a {@link GameCharacter} to be able to attack again.
   * Cooldown depends on the enemy's weight.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(
            /* command = */ this::addToQueue,
            /* delay = */ this.getWeight() / 10,
            /* unit = */ TimeUnit.SECONDS);
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Enemy enemy)) {
      return false;
    }
    return hashCode() == enemy.hashCode()
        && name.equals(enemy.name)
        && weight == enemy.weight
        && maxHp == enemy.maxHp
        && defense == enemy.defense;
  }

  @Override
  public int hashCode() {
    return Objects.hash(Enemy.class, name, weight, maxHp, defense);
  }

  @Override
  public String toString() {
    return "Enemy{maxHp=%d, defense=%d, name='%s', weight=%d}"
            .formatted(maxHp, defense, name, weight);
  }
}
