package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;

import java.util.concurrent.BlockingQueue;

public interface AbstractCharacterFactory {

  PlayerCharacter create(BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException;
}
