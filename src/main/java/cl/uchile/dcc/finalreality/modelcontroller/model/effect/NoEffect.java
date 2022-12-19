package cl.uchile.dcc.finalreality.modelcontroller.model.effect;

import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;

/**
 * An {@link Effect} that represents the {@link Enemy} is fine.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class NoEffect implements ParalyzableEffect, FireEffect, PoisonousEffect {

  private Enemy enemy;

  @Override
  public void receiveEffect() {

  }

  @Override
  public void setEnemy(Enemy enemy) {
    this.enemy = enemy;
  }

  @Override
  public int spriteColumn() {
    return 3;
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
