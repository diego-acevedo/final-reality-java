package cl.uchile.dcc.finalreality.model_controller.exceptions;

/**
 * This error is used to represent that a mage doesn't have enough mana to cast certain spell.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class InvalidManaValueException extends Exception {

  /**
   * Creates a new {@code InvalidMagicWeaponException} with a {@code description} of the
   * error.
   */
  public InvalidManaValueException(String description) {
    super("Mage doesn't have enough mana. " + description);
  }
}
