package cl.uchile.dcc.finalreality.model_controller.controller.states;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;

import java.util.List;

/**
 * Class that represents the state the game is in.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public interface GameState {

  /**
   * Sets a {@link GameDriver driver} to this {@link GameState state}.
   *
   * @param gameDriver The {@link GameDriver driver} being set.
   */
  void setGameDriver(GameDriver gameDriver);

  /**
   * Returns this {@link GameState state}'s {@link GameDriver driver}.
   *
   * @return This {@link GameState state}'s {@link GameDriver driver}.
   */
  GameDriver getGameDriver();

  /**
   * Moves to the next {@link GameState state}.
   */
  void nextState();

  /**
   * Goes back to the last checkpoint in case of a failed action.
   */
  void goBack();

  /**
   * Executes an action a tries to transition to the next {@link GameState state}.
   */
  void execute();

  /**
   * Returns a list of all the selectable options, and it displays an arrow (->) to the
   * option being pointed by the cursor.
   *
   * @return A list of options as strings.
   */
  List<String> options();

  /**
   * Checks if the state must be executed automatically by the {@link GameDriver driver} or if
   * it should wait for a player's input.
   *
   * @return True if the state must be executed automatically, false if not.
   */
  boolean executeAutomatically();

  String stateInstruction();

  String getStats();
}
