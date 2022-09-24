package cl.uchile.dcc.finalreality.model.weapon;

public class Staff extends AbstractWeapon {
  private final int magicDamage;
  /**
   * Creates a staff with a name, a base damage, weight and its magic damage.
   */
  public Staff(String name, int damage, int weight, int magicDamage) {
    super(name, damage, weight);
    this.magicDamage = magicDamage;
  }
}
