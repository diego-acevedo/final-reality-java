package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

public class EnemyPlay extends AbstractState {

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
