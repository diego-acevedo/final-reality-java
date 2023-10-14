package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Sword;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory factory} that creates a {@link Sword sword}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class SwordFactory extends TemplateWeaponFactory {

  @Override
  protected void setValues() {
    this.setName("Sword");
    this.setDamage();
    this.setWeight();
  }

  @Override
  protected Weapon newWeapon() throws InvalidStatValueException {
    return new Sword(this.getName(), this.getDamage(), this.getWeight());
  }
}
