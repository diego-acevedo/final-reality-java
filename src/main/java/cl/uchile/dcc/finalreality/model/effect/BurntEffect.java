package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

public class BurntEffect implements Effect {

  private int magicDamage;

  public BurntEffect(int magicDamage) {
    this.magicDamage = magicDamage;
  }

  @Override
  public void receiveEffect(Enemy enemy) {

  }
}
