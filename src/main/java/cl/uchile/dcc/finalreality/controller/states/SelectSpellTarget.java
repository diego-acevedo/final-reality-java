package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class SelectSpellTarget extends AbstractState {

  private final Spell selectedSpell;
  private final List<GameCharacter> characters;

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
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % characters.size())
          + characters.size();
    } else {
      selectPos = gameDriver.getCursor() % characters.size();
    }
    GameCharacter target = characters.get(selectPos);
    PlayerCharacter attacker = (PlayerCharacter) gameDriver.getCurrentCharacter();
    String status = gameDriver.useMagic(attacker, selectedSpell, target);
    System.out.println(status);
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % characters.size())
          + characters.size();
    } else {
      selectPos = gameDriver.getCursor() % characters.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < characters.size(); i++) {
      if (i == selectPos) {
        options.add("-> " + characters.get(i).getName());
      } else {
        options.add(characters.get(i).getName());
      }
    }
    return options;
  }
}
