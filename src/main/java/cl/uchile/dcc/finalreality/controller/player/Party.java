package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;

import java.util.ArrayList;
import java.util.List;

public class Party {

  private final List<PlayerCharacter> characters;
  private final int maxCharacters;

  public Party(int n) {
    this.maxCharacters = n;
    this.characters = new ArrayList<>();
  }

  public void addCharacter(PlayerCharacter character) {
    if (this.characters.size() < this.maxCharacters) {
      characters.add(character);
    }
  }

  public PlayerCharacter getCharacter(int n) {
    return characters.get(n);
  }

  public boolean alive() {
    boolean alive = false;
    for (PlayerCharacter character : characters) {
      alive = alive | character.isAlive();
    }
    return alive;
  }
}
