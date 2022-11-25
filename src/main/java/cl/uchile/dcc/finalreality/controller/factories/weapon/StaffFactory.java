package cl.uchile.dcc.finalreality.controller.factories.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.weapon.Staff;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

public class StaffFactory extends TemplateWeaponFactory {

  private int Mp;

  public int getMp() {
    return this.Mp;
  }

  private void setMp() {
    this.Mp = this.rnd.nextInt(20, 50);
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
