package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect effect} that represents the
 * {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} fire state.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface FireEffect extends Effect {

  /**
   * Check if the current state is burnt. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} is burnt.
   */
  boolean isBurnt();
}
