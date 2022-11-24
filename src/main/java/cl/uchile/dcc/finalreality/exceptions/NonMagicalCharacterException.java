package cl.uchile.dcc.finalreality.exceptions;

/**
 * This error is used to represent that a non-magical character tried to cast a spell.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class NonMagicalCharacterException extends Exception {
  /**
   * Creates a new {@code NonMagicalCharacterException} with a {@code description} of the
   * error.
   */
  public NonMagicalCharacterException(String description) {
    super("The provided value is not a valid stat value. " + description);
  }
}
