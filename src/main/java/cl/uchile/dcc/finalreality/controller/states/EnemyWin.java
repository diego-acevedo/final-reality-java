package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

/**
 * A {@link GameState} that represents the game is over and the enemy has won.
 */
public class EnemyWin implements GameState {

  private GameDriver gameDriver;

  @Override
  public void setGameDriver(GameDriver gameDriver) {
    this.gameDriver = gameDriver;
  }
}
