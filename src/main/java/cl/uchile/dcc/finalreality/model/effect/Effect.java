package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

public interface Effect {
  void receiveEffect(Enemy enemy);
}
