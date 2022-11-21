package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

import static java.lang.Math.max;

public class PoisonEffect implements PoisonousEffect {

  private Enemy enemy;
  private final int magicDamage;

  public PoisonEffect(int magicDamage) {
    this.magicDamage = magicDamage;
  }

  @Override
  public void receiveEffect() throws InvalidStatValueException {
    int newHp = max(0, this.enemy.getCurrentHp() - (magicDamage/3));
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
