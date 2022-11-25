package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;

public class Player {

  private Inventory inventory;
  private Party party;

  public Player() throws InvalidStatValueException {
    this.inventory = new Inventory();
    this.party = new Party(5);
  }
}
