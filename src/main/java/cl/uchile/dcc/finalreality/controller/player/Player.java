package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;

/**
 * A class that represents a player. It has an {@link Inventory} and a {@link Party}.
 */
public class Player {

  private Inventory inventory;
  private Party party;

  /**
   * Creates a new player with an initialized {@link Inventory} and an empty {@link Party}.
   *
   * @throws InvalidStatValueException Values must be valid.
   */
  public Player() throws InvalidStatValueException {
    this.inventory = new Inventory();
    this.party = new Party(5);
  }

  /**
   * Checks if the {@link PlayerCharacter} in the {@link Party} are alive.
   *
   * @return True if at least one character is alive.
   */
  public boolean alive() {
    return party.alive();
  }

  /**
   * Adds a new {@link PlayerCharacter} to the {@link Party}.
   *
   * @param character {@link PlayerCharacter} being added to the {@link Party}.
   */
  public void addCharacter(PlayerCharacter character) {
    this.party.addCharacter(character);
  }
}
