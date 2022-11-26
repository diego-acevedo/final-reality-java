package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

/**
 * A class that represents a factory to create {@link Weapon}.
 */
public interface AbstractWeaponFactory {

  /**
   * Creates a new {@link Weapon}.
   *
   * @return New {@link Weapon} with random values.
   * @throws InvalidStatValueException Values must be valid.
   */
  Weapon create() throws InvalidStatValueException;
}
