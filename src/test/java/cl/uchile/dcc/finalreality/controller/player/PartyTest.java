package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class PartyTest {

  private Party party;
  private PlayerCharacter knight;
  private PlayerCharacter blackMage;
  private PlayerCharacter engineer;
  private PlayerCharacter thief;
  private PlayerCharacter whiteMage;
  private final BlockingQueue<GameCharacter> turnsQueue = new LinkedBlockingQueue<>();

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    this.party = new Party(4);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.thief = new Thief("Thief", 100, 100, turnsQueue);
    this.engineer = new Engineer("Engineer", 100, 100, turnsQueue);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
  }

  @Test
  void addCharacter() {
    assertEquals(0, this.party.getCharacters().size());
    this.party.addCharacter(knight);
    assertEquals(1, this.party.getCharacters().size());
    this.party.addCharacter(engineer);
    assertEquals(2, this.party.getCharacters().size());
    this.party.addCharacter(thief);
    assertEquals(3, this.party.getCharacters().size());
    this.party.addCharacter(blackMage);
    assertEquals(4, this.party.getCharacters().size());
    this.party.addCharacter(whiteMage);
    assertEquals(4, this.party.getCharacters().size());
    Set<PlayerCharacter> finalParty = new HashSet<>(this.party.getCharacters());
    Set<PlayerCharacter> expectedParty = new HashSet<PlayerCharacter>();
    expectedParty.add(knight);
    expectedParty.add(engineer);
    expectedParty.add(thief);
    expectedParty.add(blackMage);
    assertEquals(expectedParty, finalParty);
  }
}