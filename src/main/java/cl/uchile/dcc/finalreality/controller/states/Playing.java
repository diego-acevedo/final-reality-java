package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public class Playing implements GameState {

  private GameDriver gameDriver;

  @Override
  public void setGameDriver(GameDriver gameDriver) {
    this.gameDriver = gameDriver;
  }
}