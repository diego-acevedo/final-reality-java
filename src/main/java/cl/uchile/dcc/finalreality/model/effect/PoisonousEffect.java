package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect} that represents the {@link cl.uchile.dcc.finalreality.model.character.Enemy}
 * poison state.
 */
public interface PoisonousEffect extends Effect {

  /**
   * Check if the current state is poisoned. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy} is poisoned.
   */
  boolean isPoisoned();
}
