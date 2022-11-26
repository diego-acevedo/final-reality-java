package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Staff;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

/**
 * A {@link AbstractWeaponFactory} that creates an {@link Staff}.
 */
public class StaffFactory extends TemplateWeaponFactory {

  private int mp;

  public int getMp() {
    return this.mp;
  }

  private void setMp() {
    this.mp = this.rnd.nextInt(20, 50);
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
