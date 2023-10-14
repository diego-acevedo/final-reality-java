package cl.uchile.dcc.finalreality.modelcontroller.model.effect;

import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;

/**
 * An {@link Effect effect} that represents the
 * {@link Enemy enemy} fire state.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface FireEffect extends Effect {

  /**
   * Check if the current state is burnt. If it's safe returns false.
   *
   * @return True if the {@link Enemy enemy} is burnt.
   */
  boolean isBurnt();
}
