package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.player.Player;
import cl.uchile.dcc.finalreality.controller.states.GameState;

/**
 * A class that controls the logic of the game.
 */
public class GameDriver {

  private GameState gameState;
  private Player player;

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
    gameState.setGameDriver(this);
  }
}
