package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class End extends AbstractState {

  public End(GameDriver driver) {
    super(driver);
  }

  @Override
  public void execute() {
    // finish state
  }

  @Override
  public List<String> options() {
    String winner;
    if (gameDriver.enemiesAlive()) {
      winner = "Enemies won this fight...";
    } else if (gameDriver.playerAlive()) {
      winner = "Player has won the fight!";
    } else {
      winner = "Nobody won";
    }
    return new ArrayList<String>(Collections.singleton(winner));
  }
}
