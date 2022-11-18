package cl.uchile.dcc.finalreality.exceptions;

public class InvalidTargetCharacterException extends Exception {
  /**
   * Creates a new {@code InvalidTargetCharacterException} with a {@code description} of the
   * error.
   */
  public InvalidTargetCharacterException(String description) {
    super("A character is receiving a spell they shouldn't. " + description);
  }
}
