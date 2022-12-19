package cl.uchile.dcc.finalreality.modelcontroller.exceptions;

/**
 * This error is used to represent that a character wants to attack with no weapon.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class NullWeaponException extends Exception {
  /**
   * Creates a new {@code NullWeaponException} with a {@code description} of the
   * error.
   */
  public NullWeaponException(String description) {
    super("No weapon equipped. " + description);
  }
}
