package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * A class that represents a factory to create {@link Weapon weapon}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface AbstractWeaponFactory {

  /**
   * Creates a new {@link Weapon weapon}.
   *
   * @return New {@link Weapon weapon} with random values.
   * @throws InvalidStatValueException Values must be valid.
   */
  Weapon create() throws InvalidStatValueException;
}
