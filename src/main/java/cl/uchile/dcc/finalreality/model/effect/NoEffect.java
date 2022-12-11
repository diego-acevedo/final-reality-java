package cl.uchile.dcc.finalreality.model.effect;

import cl.uchile.dcc.finalreality.model.character.Enemy;

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
