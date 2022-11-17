package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

public class ParalysisEffect implements ParalyzableEffect {

  @Override
  public void receiveEffect(Enemy enemy) {
    enemy.paralysisStatus = new NoEffect();
  }

  @Override
  public boolean isParalyzed() {
    return true;
  }
}
