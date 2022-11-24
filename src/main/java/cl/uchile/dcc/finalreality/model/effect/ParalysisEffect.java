package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

/**
 * An {@link Effect} that represents the {@link Enemy} is paralyzed.
 */
public class ParalysisEffect implements ParalyzableEffect {

  private Enemy enemy;
  private int turns;

  @Override
  public void receiveEffect() {
    turns++;
    if (turns > 1) {
      ParalyzableEffect s = new NoEffect();
      this.enemy.changeParalysisStatus(s);
      this.turns = 0;
    }

  }

  @Override
  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public boolean isParalyzed() {
    return true;
  }
}
