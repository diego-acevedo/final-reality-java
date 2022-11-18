package cl.uchile.dcc.finalreality.exceptions;

public class InvalidManaValueException extends Exception {

  /**
   * Creates a new {@code InvalidMagicWeaponException} with a {@code description} of the
   * error.
   */
  public InvalidManaValueException(String description) {
    super("Mage doesn't have enough mana. " + description);
  }
}
