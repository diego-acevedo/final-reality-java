package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect} that represents the {@link cl.uchile.dcc.finalreality.model.character.Enemy}
 * fire state.
 */
public interface FireEffect extends Effect {

  /**
   * Check if the current state is burnt. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy} is burnt.
   */
  boolean isBurnt();
}
