package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.Enemy;

import java.util.ArrayList;
import java.util.List;

public class SelectAttackTarget extends AbstractState {

  private final List<Enemy> aliveEnemies;

  public SelectAttackTarget(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.nextState = new NewTurn(driver);
    this.aliveEnemies = gameDriver.getAliveEnemies();
  }

  @Override
  public void execute() {
    int select_pos;
    if (gameDriver.getCursor() < 0) {
      select_pos = (gameDriver.getCursor() % aliveEnemies.size())
          + aliveEnemies.size();
    } else {
      select_pos = gameDriver.getCursor() % aliveEnemies.size();
    }
    Enemy target = aliveEnemies.get(select_pos);
    String status = gameDriver.attack(gameDriver.getCurrentCharacter(), target);
    System.out.println(status);
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
  }

  @Override
  public List<String> options() {
    List<String> options = new ArrayList<>();
    for (int i = 0; i < aliveEnemies.size(); i++) {
      if (i == gameDriver.getCursor() % aliveEnemies.size()) {
        options.add("-> " + aliveEnemies.get(i).getName());
      } else {
        options.add(aliveEnemies.get(i).getName());
      }
    }
    return options;
  }
}
