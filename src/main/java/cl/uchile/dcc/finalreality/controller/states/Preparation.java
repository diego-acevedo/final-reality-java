package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the game is in preparation.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Preparation extends AbstractState {

  /**
   * Creates a {@link Preparation preparation state}. Doesn't have failed state
   * because is the beginning state. The next state is always a {@link NewTurn new turn state}.
   *
   * @param driver The {@link GameDriver driver} this {@link Preparation state} belongs to.
   */
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
