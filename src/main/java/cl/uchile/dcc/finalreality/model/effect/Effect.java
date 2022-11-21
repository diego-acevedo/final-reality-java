package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

public interface Effect {
  void receiveEffect() throws InvalidStatValueException;

  void setEnemy(Enemy enemy);
}
