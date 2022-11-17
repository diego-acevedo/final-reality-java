package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;

import static java.lang.Math.max;

public class BurntEffect implements FireEffect {

  private int magicDamage;

  public BurntEffect(int magicDamage) {
    this.magicDamage = magicDamage;
  }

  @Override
  public void receiveEffect(Enemy enemy) throws InvalidStatValueException {
    int newHp = max(0, enemy.getCurrentHp() - (magicDamage/2));
    enemy.setCurrentHp(newHp);
  }

  @Override
  public boolean isBurnt() {
    return true;
  }
}
