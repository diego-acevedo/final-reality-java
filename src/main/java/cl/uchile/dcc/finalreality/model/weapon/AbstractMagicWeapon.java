package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.Require;

abstract class AbstractMagicWeapon extends AbstractWeapon implements MagicWeapon {

  private final int magicDamage;

  protected AbstractMagicWeapon(final String name, final int damage, final int weight,
      int magicDamage) throws InvalidStatValueException {
    super(name, damage, weight);
    Require.statValueAtLeast(0, magicDamage, "Magic Damage");
    this.magicDamage = magicDamage;
  }

  /**
   * Returns the weapon's magic damage.
   */
  public int getMagicDamage() {
    return magicDamage;
  }
}
