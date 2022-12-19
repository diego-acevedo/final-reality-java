package cl.uchile.dcc.finalreality.model_controller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Bow;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory factory} that creates a {@link Bow bow}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
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
