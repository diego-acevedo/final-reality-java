package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
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
  public void executes() {
    List<PlayerCharacter> characters = gameDriver.getAliveCharacters();
    int n = RANDOM_GENERATOR.nextInt(0, characters.size());
    gameDriver.attack(gameDriver.getCurrentCharacter(), characters.get(n));
    nextState();
  }

  @Override
  public List<String> options() {
    return new ArrayList<>();
  }
}
