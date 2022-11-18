package cl.uchile.dcc.finalreality.exceptions;

public class InvalidMagicWeaponException extends Exception {

  /**
   * Creates a new {@code InvalidMagicWeaponException} with a {@code description} of the
   * error.
   */
  public InvalidMagicWeaponException(String description) {
    super("A non magical weapon is trying to cast a spell. " + description);
  }
}
