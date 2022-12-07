package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public class EnemyPlay extends AbstractState {

  public EnemyPlay(GameDriver driver) {
    super(driver);
    this.nextState = new NewTurn(driver);
  }
}
