package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Axe;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory factory} that creates an {@link Axe axe}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class AxeFactory extends TemplateWeaponFactory {

  @Override
  protected void setValues() {
    this.setName("Axe");
    this.setDamage();
    this.setWeight();
  }

  @Override
  protected Weapon newWeapon() throws InvalidStatValueException {
    return new Axe(this.getName(), this.getDamage(), this.getWeight());
  }
}
