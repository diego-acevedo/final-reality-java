package cl.uchile.dcc.finalreality.modelcontroller.controller.states;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;

/**
 * A class that holds the basic logic of a {@link GameState state}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class AbstractState implements GameState {

  protected GameDriver gameDriver;
  protected GameState nextState;
  protected GameState failedState;

  /**
   * Creates a new {@link AbstractState abstract state}.
   *
   * @param driver The {@link GameDriver driver} this {@link GameState state} belongs to.
   * @param failed The {@link GameState state} to go back in case of a failed action.
   */
  public AbstractState(GameDriver driver, GameState failed) {
    this.gameDriver = driver;
    this.failedState = failed;
  }

  /**
   * Creates a new {@link AbstractState abstract state}.
   *
   * @param driver The {@link GameDriver driver} this {@link GameState state} belongs to.
   */
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
  public boolean executeAutomatically() {
    return false;
  }

  @Override
  public String stateInstruction() {
    return "";
  }

  @Override
  public String getStats() {
    return "";
  }
}
