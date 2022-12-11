package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

/**
 * An {@link Effect} that represents the {@link Enemy} is poisoned.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class PoisonEffect implements PoisonousEffect {

  private Enemy enemy;
  private final int magicDamage;

  /**
   * Creates a poison effect.
   *
   * @param magicDamage MagicDamage of the
   *                    {@link cl.uchile.dcc.finalreality.model.weapon.MagicWeapon weapon}
   *                    that cast the {@link cl.uchile.dcc.finalreality.model.spell.Spell spell}.
   */
  public PoisonEffect(int magicDamage) {
    this.magicDamage = magicDamage;
  }

  @Override
  public void receiveEffect() throws InvalidStatValueException {
    int newHp = Math.max(0, this.enemy.getCurrentHp() - (magicDamage / 3));
    this.enemy.setCurrentHp(newHp);
  }

  @Override
  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public boolean isPoisoned() {
    return true;
  }
}
