package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.spell.*;

import java.util.ArrayList;
import java.util.List;

public class SelectSpell extends AbstractState {

  private final List<Spell> spells;

  public SelectSpell(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.spells = new ArrayList<>();
    this.spells.add(new Cure());
    this.spells.add(new Fire());
    this.spells.add(new Paralysis());
    this.spells.add(new Poison());
    this.spells.add(new Thunder());
  }

  @Override
  public void execute() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % spells.size())
          + spells.size();
    } else {
      selectPos = gameDriver.getCursor() % spells.size();
    }
    Spell spellSelected = spells.get(selectPos);
    this.nextState = new SelectSpellTarget(this.gameDriver, this.failedState, spellSelected);
    nextState();
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % spells.size())
          + spells.size();
    } else {
      selectPos = gameDriver.getCursor() % spells.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < spells.size(); i++) {
      if (i == selectPos) {
        options.add("-> " + spells.get(i));
      } else {
        options.add(spells.get(i).toString());
      }
    }
    return options;
  }
}
