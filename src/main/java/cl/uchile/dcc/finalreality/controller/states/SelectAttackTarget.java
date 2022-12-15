package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the player is selecting a {@link Enemy enemy}
 * to attack.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class SelectAttackTarget extends AbstractState {

  private final List<Enemy> aliveEnemies;

  /**
   * Creates a {@link SelectAttackTarget "select attack target" state}. Its failed state is
   * {@link PlayerSelectAction "player select action" state} Its next state is
   * {@link NewTurn "new turn" state}.
   *
   * @param driver The {@link GameDriver driver} this {@link SelectAttackTarget state} belongs to.
   * @param failed The {@link GameState state} the game should go back to when something fails.
   */
  public SelectAttackTarget(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.nextState = new NewTurn(driver);
    this.aliveEnemies = gameDriver.getAliveEnemies();
  }

  @Override
  public void execute() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % aliveEnemies.size())
          + aliveEnemies.size();
    } else {
      selectPos = gameDriver.getCursor() % aliveEnemies.size();
    }
    Enemy target = aliveEnemies.get(selectPos);
    GameCharacter attacker = gameDriver.getCurrentCharacter();
    String status = gameDriver.attack(attacker, target);
    System.out.println(status);
    gameDriver.setActionOutput(status);
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % aliveEnemies.size())
          + aliveEnemies.size();
    } else {
      selectPos = gameDriver.getCursor() % aliveEnemies.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < aliveEnemies.size(); i++) {
      if (i == selectPos) {
        options.add("> " + aliveEnemies.get(i).getName());
      } else {
        options.add("  " + aliveEnemies.get(i).getName());
      }
    }
    return options;
  }

  @Override
  public String stateInstruction() {
    return "Select a target";
  }

  @Override
  public String getStats() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % aliveEnemies.size())
          + aliveEnemies.size();
    } else {
      selectPos = gameDriver.getCursor() % aliveEnemies.size();
    }
    Enemy target = aliveEnemies.get(selectPos);
    return "Stats:\n" + target.getStats();
  }
}
