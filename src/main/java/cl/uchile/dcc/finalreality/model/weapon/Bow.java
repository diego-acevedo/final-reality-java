package cl.uchile.dcc.finalreality.model.weapon;

public class Bow extends AbstractWeapon {
  /**
   * Creates a bow with a name, a base damage and its weight.
   */
  public Bow(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public String toString() {
    return "Bow" + super.toString();
  }
}
