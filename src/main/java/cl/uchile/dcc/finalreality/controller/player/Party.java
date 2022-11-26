package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a party for a {@link Player}.
 */
public class Party {

  private final List<PlayerCharacter> characters;
  private final int maxCharacters;

  /**
   * Creates a new party that can holds a number of n {@link PlayerCharacter}.
   *
   * @param n Number of {@link PlayerCharacter} that the party can hold.
   */
  public Party(int n) {
    this.maxCharacters = n;
    this.characters = new ArrayList<>();
  }

  /**
   * Adds a new {@link PlayerCharacter} to the party.
   *
   * @param character {@link PlayerCharacter} being added.
   */
  public void addCharacter(PlayerCharacter character) {
    if (this.characters.size() < this.maxCharacters) {
      characters.add(character);
    }
  }

  /**
   * Returns a {@link PlayerCharacter} with an index n.
   *
   * @param n Index of the {@link PlayerCharacter}.
   * @return A {@link PlayerCharacter} with an index n.
   */
  public PlayerCharacter getCharacter(int n) {
    return characters.get(n);
  }

  /**
   * Checks if at least one {@link PlayerCharacter} is still alive.
   *
   * @return True if at least one character is alive.
   */
  public boolean alive() {
    boolean alive = false;
    for (PlayerCharacter character : characters) {
      alive = alive | character.isAlive();
    }
    return alive;
  }
}
