package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

/**
 * Class that represents the state the game is in.
 */
public interface GameState {

  void setGameDriver(GameDriver gameDriver);

  GameDriver getGameDriver();
}
