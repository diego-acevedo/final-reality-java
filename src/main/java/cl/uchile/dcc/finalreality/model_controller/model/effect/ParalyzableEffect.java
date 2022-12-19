package cl.uchile.dcc.finalreality.model_controller.model.effect;

import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;

/**
 * An {@link Effect effect} that represents the
 * {@link Enemy enemy} paralysis state.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface ParalyzableEffect extends Effect {

  /**
   * Check if the current state is paralysed. If it's safe returns false.
   *
   * @return True if the {@link Enemy enemy} is
   *     paralysed.
   */
  boolean isParalyzed();
}
