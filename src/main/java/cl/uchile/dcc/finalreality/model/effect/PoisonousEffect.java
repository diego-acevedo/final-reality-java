package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect effect} that represents the
 * {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} poison state.
 */
public interface PoisonousEffect extends Effect {

  /**
   * Check if the current state is poisoned. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} is poisoned.
   */
  boolean isPoisoned();
}
