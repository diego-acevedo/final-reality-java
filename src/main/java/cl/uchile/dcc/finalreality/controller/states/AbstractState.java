package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

public abstract class AbstractState implements GameState {

  protected GameDriver gameDriver;
  protected GameState nextState;
  protected GameState failedState;
  private boolean finished = false;

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

  @Override
  public void nextState() {
    gameDriver.setGameState(nextState);
  }

  @Override
  public void goBack() {
    gameDriver.setGameState(failedState);
  }

  @Override
  public void allowContinue() {
    this.finished = true;
  }

  @Override
  public boolean isFinished() {
    return this.finished;
  }

  @Override
  public boolean executeAutomatically() {
    return false;
  }
}
