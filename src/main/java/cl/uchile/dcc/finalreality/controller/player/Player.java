package cl.uchile.dcc.finalreality.controller.player;

import static cl.uchile.dcc.finalreality.controller.GameDriver.MAX_CHARACTERS;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;


/**
 * A class that represents a player. It has an {@link Inventory inventory}
 * and a {@link Party party}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Player {

  private final Inventory inventory;
  private final Party party;

  /**
   * Creates a new player with an initialized {@link Inventory inventory} and
   * an empty {@link Party party}.
   *
   * @throws InvalidStatValueException Values must be valid.
   */
  public Player() throws InvalidStatValueException {
    this.inventory = new Inventory();
    this.party = new Party(MAX_CHARACTERS);
  }

  /**
   * Checks if the {@link PlayerCharacter characters} in the {@link Party party} are alive.
   *
   * @return True if at least one character is alive.
   */
  public boolean alive() {
    return party.alive();
  }

  /**
   * Adds a new {@link PlayerCharacter character} to the {@link Party party}.
   *
   * @param character {@link PlayerCharacter Character} being added to the {@link Party party}.
   */
  public void addCharacter(PlayerCharacter character) {
    this.party.addCharacter(character);
  }

  /**
   * Returns the {@link Player player}'s {@link Party party}.
   *
   * @return {@link Player Player}'s {@link Party party}.
   */
  public Party getParty() {
    return party;
  }

  /**
   * Returns the {@link Player player}'s {@link Inventory inventory}.
   *
   * @return {@link Player Player}'s {@link Inventory inventory}.
   */
  public Inventory getInventory() {
    return inventory;
  }
}
