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
    GameCharacter target = characters.get(gameDriver.getCursor() % characters.size());
    String status = gameDriver.useMagic((PlayerCharacter) gameDriver.getCurrentCharacter(), selectedSpell, target);
    System.out.println(status);
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
  }

  @Override
  public List<String> options() {
    List<String> options = new ArrayList<>();
    for (int i = 0; i < characters.size(); i++) {
      if (i == gameDriver.getCursor() % characters.size()) {
        options.add("-> " + characters.get(i).getName());
      } else {
        options.add(characters.get(i).getName());
      }
    }
    return options;
  }
}
