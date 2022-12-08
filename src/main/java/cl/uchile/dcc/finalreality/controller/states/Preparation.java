package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the game is in preparation.
 */
public class Preparation extends AbstractState {

  public Preparation(GameDriver driver) {
    super(driver);
    this.nextState = new NewTurn(driver);
  }

  @Override
  public void execute() {
    this.nextState();
  }

  @Override
  public List<String> options() {
    return new ArrayList<>();
  }
}
