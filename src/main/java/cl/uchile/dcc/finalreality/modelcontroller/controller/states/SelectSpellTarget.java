package cl.uchile.dcc.finalreality.modelcontroller.controller.states;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the player is selecting a {@link GameCharacter character}
 * to use the {@link Spell spell} on.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class SelectSpellTarget extends AbstractState {

  private final Spell selectedSpell;
  private final List<GameCharacter> characters;

  /**
   * Creates a {@link SelectSpellTarget "select spell target" state}. Its failed state is
   * {@link PlayerSelectAction "player select action" state} Its next state is
   * {@link NewTurn "new turn" state}.
   *
   * @param driver The {@link GameDriver driver} this {@link SelectSpellTarget state} belongs to.
   * @param failed The {@link GameState state} the game should go back to when something fails.
   * @param selectedSpell The {@link Spell spell} that's going to be used.
   */
  public SelectSpellTarget(GameDriver driver, GameState failed, Spell selectedSpell) {
    super(driver, failed);
    this.nextState = new NewTurn(driver);
    this.selectedSpell = selectedSpell;
    this.characters = new ArrayList<>();
    characters.addAll(gameDriver.getAliveCharacters());
    characters.addAll(gameDriver.getAliveEnemies());
  }

  @Override
  public void execute() {
    int selectPos;
    if (gameDriver.getCursor() % characters.size() < 0) {
      selectPos = (gameDriver.getCursor() % characters.size())
          + characters.size();
    } else {
      selectPos = gameDriver.getCursor() % characters.size();
    }
    GameCharacter target = characters.get(selectPos);
    PlayerCharacter attacker = (PlayerCharacter) gameDriver.getCurrentCharacter();
    String status = gameDriver.useMagic(attacker, selectedSpell, target);
    System.out.println(status);
    gameDriver.setActionOutput(status);
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() % characters.size() < 0) {
      selectPos = (gameDriver.getCursor() % characters.size())
          + characters.size();
    } else {
      selectPos = gameDriver.getCursor() % characters.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < characters.size(); i++) {
      if (i == selectPos) {
        options.add("> " + characters.get(i).getName());
      } else {
        options.add("  " + characters.get(i).getName());
      }
    }
    return options;
  }

  @Override
  public String stateInstruction() {
    return "Select a target";
  }

  @Override
  public String getStats() {
    int selectPos;
    if (gameDriver.getCursor() % characters.size() < 0) {
      selectPos = (gameDriver.getCursor() % characters.size())
          + characters.size();
    } else {
      selectPos = gameDriver.getCursor() % characters.size();
    }
    GameCharacter target = characters.get(selectPos);
    return "Stats:\n" + target.getStats();
  }
}
