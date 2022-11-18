package cl.uchile.dcc.finalreality.exceptions;

public class NonMagicalCharacterException extends Exception {
  /**
   * Creates a new {@code NonMagicalCharacterException} with a {@code description} of the
   * error.
   */
  public NonMagicalCharacterException(String description) {
    super("The provided value is not a valid stat value. " + description);
  }
}
