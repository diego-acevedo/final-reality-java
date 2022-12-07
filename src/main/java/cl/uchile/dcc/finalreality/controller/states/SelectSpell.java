package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public class SelectSpell extends AbstractState {

  public SelectSpell(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.nextState = new SelectSpellTarget(driver, failed);
  }
}
