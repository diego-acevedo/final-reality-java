package cl.uchile.dcc.finalreality.model_controller.controller.factories.weapon;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Staff;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory factory} that creates an {@link Staff staff}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class StaffFactory extends TemplateWeaponFactory {

  private int magicDamage;

  /**
   * Returns the random generated value for magic damage.
   *
   * @return The random generated value for magic damage.
   */
  public int getMp() {
    return this.magicDamage;
  }

  /**
   * Sets a new random generated value for magic damage.
   */
  private void setMp() {
    this.magicDamage = GameDriver.RANDOM_GENERATOR.nextInt(20, 50);
  }

  @Override
  protected void setValues() {
    this.setName("Staff");
    this.setDamage();
    this.setWeight();
    this.setMp();
  }

  @Override
  protected Weapon newWeapon() throws InvalidStatValueException {
    return new Staff(this.getName(), this.getDamage(), this.getWeight(), this.getMp());
  }
}
