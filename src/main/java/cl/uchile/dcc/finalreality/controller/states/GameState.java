package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

import java.util.List;

/**
 * Class that represents the state the game is in.
 */
public interface GameState {

  void setGameDriver(GameDriver gameDriver);

  GameDriver getGameDriver();

  void nextState();

  void goBack();

  void allowContinue();

  boolean isFinished();

  void executes();

  List<String> options();
}
