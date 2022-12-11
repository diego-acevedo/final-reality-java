package cl.uchile.dcc.finalreality.controller.factories.enemy;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a factory to create {@link Enemy enemy}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface AbstractEnemyFactory {

  /**
   * Creates a new {@link Enemy enemy}.
   *
   * @param turnsQueue Turns queue of the game driver.
   * @return New {@link Enemy enemy} with random values.
   * @throws InvalidStatValueException Values must be valid.
   */
  Enemy create(BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException;
}
