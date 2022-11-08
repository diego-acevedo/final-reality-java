package cl.uchile.dcc.finalreality.exceptions;

/**
 * This error is used to represent that an invalid weapon is being equiped to a character.
 *
 * @author <a href="https://github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */

public class InvalidEquipableWeaponException extends Exception {

  /**
   * Creates a new {@code InvalidEquipableWeaponException} with a {@code description} of the
   * error.
   */
  public InvalidEquipableWeaponException(String description) {
    super("An invalid weapon is trying to be equiped. " + description);
  }
}
