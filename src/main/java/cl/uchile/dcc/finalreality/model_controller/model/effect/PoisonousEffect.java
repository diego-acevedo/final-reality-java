package cl.uchile.dcc.finalreality.model_controller.model.effect;

import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;

/**
 * An {@link Effect effect} that represents the
 * {@link Enemy enemy} poison state.
 */
public interface PoisonousEffect extends Effect {

  /**
   * Check if the current state is poisoned. If it's safe returns false.
   *
   * @return True if the {@link Enemy enemy} is poisoned.
   */
  boolean isPoisoned();
}
