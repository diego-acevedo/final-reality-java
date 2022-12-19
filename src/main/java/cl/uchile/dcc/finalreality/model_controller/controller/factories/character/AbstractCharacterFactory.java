package cl.uchile.dcc.finalreality.model_controller.controller.factories.character;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a factory that creates a {@link PlayerCharacter player character}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface AbstractCharacterFactory {

  /**
   * Creates a new {@link PlayerCharacter player character}.
   *
   * @param turnsQueue Turns queue of the game driver.
   * @return New {@link PlayerCharacter player character} with random values.
   * @throws InvalidStatValueException Values must be valid.
   */
  PlayerCharacter create(BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException;
}
