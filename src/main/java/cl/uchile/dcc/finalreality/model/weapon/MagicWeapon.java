package cl.uchile.dcc.finalreality.model.weapon;

/**
 * This represents a {@link Weapon Weapon} that can cast magic.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface MagicWeapon extends Weapon {

  /**
   * Returns this magic weapon's magic damage.
   */
  int getMagicDamage();
}
