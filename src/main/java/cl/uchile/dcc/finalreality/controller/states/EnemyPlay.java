package cl.uchile.dcc.finalreality.controller.states;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents an {@link Enemy enemy} is playing their turn.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class EnemyPlay extends AbstractState {

  /**
   * Creates a {@link EnemyPlay "enemy play" state}. It doesn't have a failed state.
   * Its next state is a {@link NewTurn "new turn" state}.
   *
   * @param driver The {@link GameDriver driver} this {@link EnemyPlay state} belongs to.
   */
  public EnemyPlay(GameDriver driver) {
    super(driver);
    this.nextState = new NewTurn(driver);
  }

  @Override
  public void execute() {
    Enemy enemy = (Enemy) gameDriver.getCurrentCharacter();
    try {
      enemy.receiveEffect();
    } catch (InvalidStatValueException e) {
      nextState();
    }
    if (enemy.isAlive() && !enemy.isParalysed()) {
      List<PlayerCharacter> characters = gameDriver.getAliveCharacters();
      int n = RANDOM_GENERATOR.nextInt(0, characters.size());
      String status = gameDriver.attack(enemy, characters.get(n));
      System.out.println(status);
      gameDriver.setActionOutput(status);
      if (gameDriver.isTransitionSucceeded()) {
        nextState();
      } else {
        this.execute();
      }
    } else {
      nextState();
    }
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
