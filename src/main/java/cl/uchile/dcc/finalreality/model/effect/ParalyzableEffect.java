package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect} that represents the {@link cl.uchile.dcc.finalreality.model.character.Enemy}
 * paralysis state.
 */
public interface ParalyzableEffect extends Effect {

  /**
   * Check if the current state is paralysed. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy} is paralysed.
   */
  boolean isParalyzed();
}
