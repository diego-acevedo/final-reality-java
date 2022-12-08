package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a factory to create {@link PlayerCharacter}.
 */
public interface AbstractCharacterFactory {

  /**
   * Creates a new {@link PlayerCharacter}.
   *
   * @param turnsQueue Turns queue of the game driver.
   * @return New {@link PlayerCharacter} with random values.
   * @throws InvalidStatValueException Values must be valid.
   */
  PlayerCharacter create(BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException;
}
