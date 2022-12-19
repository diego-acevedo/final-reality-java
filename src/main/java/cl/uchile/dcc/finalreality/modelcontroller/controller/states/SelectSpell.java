package cl.uchile.dcc.finalreality.modelcontroller.controller.states;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Cure;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Fire;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Paralysis;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Poison;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Thunder;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the player is selecting a {@link Spell spell}
 * to use it on a {@link GameCharacter character}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class SelectSpell extends AbstractState {

  private final List<Spell> spells;

  /**
   * Creates a {@link SelectSpell "select spell" state}. Its failed state is
   * {@link PlayerSelectAction "player select action" state} Its next state is
   * {@link SelectSpellTarget "select spell target" state}.
   *
   * @param driver The {@link GameDriver driver} this {@link SelectSpell state} belongs to.
   * @param failed The {@link GameState state} the game should go back to when something fails.
   */
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
        options.add("> " + spells.get(i));
      } else {
        options.add("  " + spells.get(i).toString());
      }
    }
    return options;
  }

  @Override
  public String stateInstruction() {
    return "Select a spell";
  }

  @Override
  public String getStats() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % spells.size())
          + spells.size();
    } else {
      selectPos = gameDriver.getCursor() % spells.size();
    }
    Spell spellSelected = spells.get(selectPos);
    return "Stats:\n" + spellSelected.description();
  }
}
