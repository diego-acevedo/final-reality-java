package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

/**
 * An {@link Effect} that represents the {@link Enemy} is burnt.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class BurntEffect implements FireEffect {

  private Enemy enemy;
  private final int magicDamage;

  /**
   * Creates a burnt effect.
   *
   * @param magicDamage MagicDamage of the
   *                    {@link cl.uchile.dcc.finalreality.model.weapon.MagicWeapon weapon}
   *                    that cast the {@link cl.uchile.dcc.finalreality.model.spell.Spell spell}.
   */
  public BurntEffect(int magicDamage) {
    this.magicDamage = magicDamage;
  }

  @Override
  public void receiveEffect() throws InvalidStatValueException {
    int newHp = Math.max(0, this.enemy.getCurrentHp() - (magicDamage / 2));
    this.enemy.setCurrentHp(newHp);
  }

  @Override
  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public int spriteColumn() {
    return 0;
  }

  @Override
  public boolean isBurnt() {
    return true;
  }
}
