package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public class SelectAttackTarget extends AbstractState {

  public SelectAttackTarget(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.nextState = new NewTurn(driver);
  }
}
