package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public abstract class AbstractState implements GameState {

  private GameDriver gameDriver;
  protected GameState nextState;
  protected GameState failedState;

  public AbstractState(GameDriver driver, GameState failed) {
    this.gameDriver = driver;
    this.failedState = failed;
  }

  public AbstractState(GameDriver driver) {
    this(driver, null);
  }

  @Override
  public void setGameDriver(GameDriver gameDriver) {
    this.gameDriver = gameDriver;
  }

  @Override
  public GameDriver getGameDriver() {
    return this.gameDriver;
  }
}
