package cl.uchile.dcc.finalreality.modelcontroller.exceptions;

/**
 * This error is used to represent that a non-magical weapon has tried to cast a spell.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class InvalidMagicWeaponException extends Exception {

  /**
   * Creates a new {@code InvalidMagicWeaponException} with a {@code description} of the
   * error.
   */
  public InvalidMagicWeaponException(String description) {
    super("A non magical weapon is trying to cast a spell. " + description);
  }
}
