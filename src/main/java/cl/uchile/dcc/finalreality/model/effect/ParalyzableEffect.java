package cl.uchile.dcc.finalreality.model.effect;

/**
 * An {@link Effect effect} that represents the
 * {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} paralysis state.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface ParalyzableEffect extends Effect {

  /**
   * Check if the current state is paralysed. If it's safe returns false.
   *
   * @return True if the {@link cl.uchile.dcc.finalreality.model.character.Enemy enemy} is
   *     paralysed.
   */
  boolean isParalyzed();
}
