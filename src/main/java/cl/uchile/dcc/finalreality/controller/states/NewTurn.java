package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the game is managing a new turn.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class NewTurn extends AbstractState {

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
          } else {
            this.nextState = new EnemyPlay(gameDriver);
          }
        } else {
          this.nextState = new NewTurn(gameDriver);
        }
      }
    }
    nextState();
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
