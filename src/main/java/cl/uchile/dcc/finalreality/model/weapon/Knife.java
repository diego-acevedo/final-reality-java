package cl.uchile.dcc.finalreality.model.weapon;

public class Knife extends AbstractWeapon {
  /**
   * Creates a knife with a name, a base damage and its weight.
   */
  public Knife(String name, int damage, int weight) {
    super(name, damage, weight);
  }

  @Override
  public String toString() {
    return "Knife" + super.toString();
  }
}
