package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.ArrayList;
import java.util.List;

public class NewTurn extends AbstractState {

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
        if (gameDriver.getCurrentCharacter().isPlayable()) {
          this.nextState = new PlayerSelectAction(gameDriver);
        } else {
          this.nextState = new EnemyPlay(gameDriver);
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
