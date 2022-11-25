package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

public class Player {

  private Inventory inventory;
  private Party party;

  public Player() throws InvalidStatValueException {
    this.inventory = new Inventory();
    this.party = new Party(5);
  }

  public boolean alive() {
    return party.alive();
  }

  public void addCharacter(PlayerCharacter character) {
    this.party.addCharacter(character);
  }
}
