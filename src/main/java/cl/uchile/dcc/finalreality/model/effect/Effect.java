package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

/**
 * A state an {@link Enemy} can be in. Each one has different effects.
 */
public interface Effect {

  /**
   * Makes the {@link Enemy} receive the expected effect.
   *
   * @throws InvalidStatValueException HP value must be valid.
   */
  void receiveEffect() throws InvalidStatValueException;

  /**
   * Sets the {@link Enemy} that this {@link Effect} belongs to.
   *
   * @param enemy The {@link Enemy} this {@link Effect} belongs to.
   */
  void setEnemy(Enemy enemy);
}
