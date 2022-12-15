package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link GameState} that represents the game is over..
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class End extends AbstractState {

  /**
   * Creates a {@link End "end" state}. It doesn't have a failed nor next state.
   *
   * @param driver The {@link GameDriver driver} this {@link End state} belongs to.
   */
  public End(GameDriver driver) {
    super(driver);
  }

  @Override
  public void execute() {
    // finish state
  }

  @Override
  public List<String> options() {
    return new ArrayList<>();
  }

  @Override
  public String stateInstruction() {
    String winner;
    if (gameDriver.enemiesAlive()) {
      winner = "Enemies won this fight...";
    } else if (gameDriver.playerAlive()) {
      winner = "Player has won the fight!";
    } else {
      winner = "Nobody won";
    }
    return winner;
  }

  @Override
  public boolean executeAutomatically() {
    return true;
  }
}
