package cl.uchile.dcc.finalreality.modelcontroller.model.effect;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;

/**
 * A state an {@link Enemy enemy} can be in. Each one has different effects.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface Effect {

  /**
   * Makes the {@link Enemy enemy} receive the expected effect.
   *
   * @throws InvalidStatValueException HP value must be valid.
   */
  void receiveEffect() throws InvalidStatValueException;

  /**
   * Sets the {@link Enemy enemy} that this {@link Effect effect} belongs to.
   *
   * @param enemy The {@link Enemy enemy} this {@link Effect effect} belongs to.
   */
  void setEnemy(Enemy enemy);

  /**
   * Returns the column where the sprite is lacated.
   *
   * @return Sprite's column.
   */
  int spriteColumn();
}
