package cl.uchile.dcc.finalreality.exceptions;

public class InvalidMageException extends Exception {

  /**
   * Creates a new {@code InvalidMageException} with a {@code description} of the
   * error.
   */
  public InvalidMageException(String description) {
    super("An invalid mage is trying to conjure a spell they're not supposed to. " + description);
  }
}
