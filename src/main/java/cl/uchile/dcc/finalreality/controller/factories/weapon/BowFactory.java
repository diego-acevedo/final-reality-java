package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Bow;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory} that creates an {@link Bow}.
 */
public class BowFactory extends TemplateWeaponFactory {

  @Override
  protected void setValues() {
    this.setName("Bow");
    this.setDamage();
    this.setWeight();
  }

  @Override
  protected Weapon newWeapon() throws InvalidStatValueException {
    return new Bow(this.getName(), this.getDamage(), this.getWeight());
  }
}
