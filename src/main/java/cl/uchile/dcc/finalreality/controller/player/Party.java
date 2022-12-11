package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a party for a {@link Player player}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Party {

  private final List<PlayerCharacter> characters;
  private final int maxCharacters;

  /**
   * Creates a new party that can holds a number of n {@link PlayerCharacter characters}.
   *
   * @param n Number of {@link PlayerCharacter characters} that the party can hold.
   */
  public Party(int n) {
    this.maxCharacters = n;
    this.characters = new ArrayList<>();
  }

  /**
   * Adds a new {@link PlayerCharacter character} to the party.
   *
   * @param character {@link PlayerCharacter Character} being added.
   */
  public void addCharacter(PlayerCharacter character) {
    if (this.characters.size() < this.maxCharacters) {
      characters.add(character);
    }
  }

  /**
   * Checks if at least one {@link PlayerCharacter character} is still alive.
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

  /**
   * Returns the {@link PlayerCharacter characters} being hold by the {@link Party party}.
   *
   * @return The {@link PlayerCharacter characters} being hold by the {@link Party party}.
   */
  public List<PlayerCharacter> getCharacters() {
    return characters;
  }
}
