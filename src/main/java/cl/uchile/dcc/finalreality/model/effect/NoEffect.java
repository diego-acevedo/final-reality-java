package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

public class NoEffect implements ParalyzableEffect, FireEffect, PoisonousEffect {
  @Override
  public void receiveEffect(Enemy enemy) {

  }

  @Override
  public boolean isBurnt() {
    return false;
  }

  @Override
  public boolean isParalyzed() {
    return false;
  }

  @Override
  public boolean isPoisoned() {
    return false;
  }
}
