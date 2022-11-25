package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Knife;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

public class KnifeFactory extends TemplateWeaponFactory {

  @Override
  protected void setValues() {
    this.setName("Knife");
    this.setDamage();
    this.setWeight();
  }

  @Override
  protected Weapon newWeapon() throws InvalidStatValueException {
    return new Knife(this.getName(), this.getDamage(), this.getWeight());
  }
}
