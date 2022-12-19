package cl.uchile.dcc.finalreality.modelcontroller.exceptions;

/**
 * This error is used to represent that a spell tried to be used on the wrong type of character.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class InvalidTargetCharacterException extends Exception {
  /**
   * Creates a new {@code InvalidTargetCharacterException} with a {@code description} of the
   * error.
   */
  public InvalidTargetCharacterException(String description) {
    super("A character is receiving a spell they shouldn't. " + description);
  }
}
