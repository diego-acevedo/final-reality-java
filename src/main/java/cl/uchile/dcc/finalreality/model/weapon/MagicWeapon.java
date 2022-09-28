package cl.uchile.dcc.finalreality.model.weapon;

/**
 * This represents a weapon that can cast magic.
 */
public interface MagicWeapon extends Weapon {

  /**
   * Returns this magic weapon's magic damage.
   */
  int getMagicDamage();
}
