package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

public interface AbstractWeaponFactory {

  Weapon create() throws InvalidStatValueException;
}
