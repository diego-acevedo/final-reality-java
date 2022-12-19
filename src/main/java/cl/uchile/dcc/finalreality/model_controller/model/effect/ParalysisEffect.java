package cl.uchile.dcc.finalreality.model_controller.model.effect;

import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;

/**
 * An {@link Effect} that represents the {@link Enemy} is paralyzed.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class ParalysisEffect implements ParalyzableEffect {

  private Enemy enemy;
  private int turns = 0;

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
  public int spriteColumn() {
    return 2;
  }

  @Override
  public boolean isParalyzed() {
    return true;
  }
}
