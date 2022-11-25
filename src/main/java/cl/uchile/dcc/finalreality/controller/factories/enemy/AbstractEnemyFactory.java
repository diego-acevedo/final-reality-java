package cl.uchile.dcc.finalreality.controller.factories.enemy;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;

import java.util.concurrent.BlockingQueue;

public interface AbstractEnemyFactory {

  Enemy create(BlockingQueue<GameCharacter> turnsQueue) throws InvalidStatValueException;
}
