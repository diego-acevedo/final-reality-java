package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectAction extends AbstractState {

  private final List<GameState> possibleNextTransitions;
  private final List<String> phasesName;
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
        options.add("-> " + phasesName.get(i));
      } else {
        options.add(phasesName.get(i));
      }
    }
    return options;
  }
}
