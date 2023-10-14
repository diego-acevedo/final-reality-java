package cl.uchile.dcc.finalreality.modelcontroller.exceptions;

/**
 * This error is used to represent that an invalid mage has cast a spell they don't know.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class InvalidMageException extends Exception {

  /**
   * Creates a new {@code InvalidMageException} with a {@code description} of the
   * error.
   */
  public InvalidMageException(String description) {
    super("An invalid mage is trying to conjure a spell they're not supposed to. " + description);
  }
}
