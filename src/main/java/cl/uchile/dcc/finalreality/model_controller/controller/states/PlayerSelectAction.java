package cl.uchile.dcc.finalreality.model_controller.controller.states;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the player is selecting an action.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class PlayerSelectAction extends AbstractState {

  private final List<GameState> possibleNextTransitions;
  private final List<String> phasesName;

  /**
   * Creates a {@link PlayerSelectAction "player select action" state}. It doesn't have a failed
   * state. Its next state depends on what was selected.
   *
   * @param driver The {@link GameDriver driver} this {@link PlayerSelectAction state} belongs to.
   */
  public PlayerSelectAction(GameDriver driver) {
    super(driver);
    this.phasesName = new ArrayList<>();
    phasesName.add("Equip");
    phasesName.add("Attack");
    phasesName.add("Cast spell");
    this.possibleNextTransitions = new ArrayList<>();
    possibleNextTransitions.add(new SelectWeapon(gameDriver, this));
    possibleNextTransitions.add(new SelectAttackTarget(gameDriver, this));
    possibleNextTransitions.add(new SelectSpell(gameDriver, this));
  }

  @Override
  public void execute() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % possibleNextTransitions.size())
          + possibleNextTransitions.size();
    } else {
      selectPos = gameDriver.getCursor() % possibleNextTransitions.size();
    }
    this.nextState = possibleNextTransitions.get(selectPos);
    nextState();
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % possibleNextTransitions.size())
          + possibleNextTransitions.size();
    } else {
      selectPos = gameDriver.getCursor() % possibleNextTransitions.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < phasesName.size(); i++) {
      if (i == selectPos) {
        options.add("> " + phasesName.get(i));
      } else {
        options.add("  " + phasesName.get(i));
      }
    }
    return options;
  }

  @Override
  public String stateInstruction() {
    return "Select an action for this " + gameDriver.getCurrentCharacter().getClass().getSimpleName();
  }
}
