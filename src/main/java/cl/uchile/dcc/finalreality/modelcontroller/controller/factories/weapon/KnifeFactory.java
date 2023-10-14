package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Knife;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory factory} that creates a {@link Knife knife}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
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
