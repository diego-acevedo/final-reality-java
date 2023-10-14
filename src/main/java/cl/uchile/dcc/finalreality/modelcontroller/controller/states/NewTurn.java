package cl.uchile.dcc.finalreality.modelcontroller.controller.states;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A {@link GameState} that represents the game is managing a new turn.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class NewTurn extends AbstractState {

  private ScheduledExecutorService waitToStart;

  /**
   * Creates a {@link NewTurn "new turn" state}. It doesn't have a failed state.
   * Its next state depends on the {@link GameCharacter character} playing.
   *
   * @param driver The {@link GameDriver driver} this {@link NewTurn state} belongs to.
   */
  public NewTurn(GameDriver driver) {
    super(driver);
  }

  @Override
  public void execute() {
    if (gameDriver.isGameOver()) {
      this.nextState = new End(gameDriver);
      nextState();
    } else {
      while (gameDriver.getTurnsQueue().isEmpty()) {
        // wait turn
      }
      if (!gameDriver.getTurnsQueue().isEmpty()) {
        GameCharacter character = gameDriver.getTurnsQueue().poll();
        gameDriver.setCurrentCharacter(character);
        if (gameDriver.getCurrentCharacter().isAlive()) {
          if (gameDriver.getCurrentCharacter().isPlayable()) {
            this.nextState = new PlayerSelectAction(gameDriver);
            nextState();
          } else {
            this.nextState = new EnemyPlay(gameDriver);
            waitToStart = Executors.newSingleThreadScheduledExecutor();
            waitToStart.schedule(this::enemyNextState, 2, TimeUnit.SECONDS);
          }
        } else {
          this.nextState = new NewTurn(gameDriver);
          nextState();
        }
      }
    }
  }

  private void enemyNextState() {
    nextState();
    waitToStart.shutdown();
  }

  @Override
  public List<String> options() {
    return new ArrayList<>();
  }

  @Override
  public boolean executeAutomatically() {
    return true;
  }
}
