package cl.uchile.dcc.finalreality.controller.player;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.controller.factories.weapon.*;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameDriver.getGameDriver;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  private Player player;
  private GameDriver driver;
  private PlayerCharacter knight;
  private PlayerCharacter blackMage;
  private PlayerCharacter engineer;
  private PlayerCharacter thief;
  private PlayerCharacter whiteMage;
  private final BlockingQueue<GameCharacter> turnsQueue = new LinkedBlockingQueue<>();
  private final long seed = (new Random()).nextLong();

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    this.driver = getGameDriver(seed);
    this.driver = getGameDriver(seed);
    this.player = new Player();

    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.thief = new Thief("Thief", 100, 100, turnsQueue);
    this.engineer = new Engineer("Engineer", 100, 100, turnsQueue);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);

    this.player.addCharacter(knight);
    this.player.addCharacter(engineer);
    this.player.addCharacter(thief);
    this.player.addCharacter(blackMage);
    this.player.addCharacter(whiteMage);
  }

  @Test
  void alive() throws InvalidStatValueException {
    assertTrue(this.player.alive());
    knight.setCurrentHp(0);
    assertTrue(this.player.alive());
    engineer.setCurrentHp(0);
    assertTrue(this.player.alive());
    thief.setCurrentHp(0);
    assertTrue(this.player.alive());
    blackMage.setCurrentHp(0);
    assertTrue(this.player.alive());
    whiteMage.setCurrentHp(0);
    assertFalse(this.player.alive());
  }

  @Test
  void getParty() {
    Set<PlayerCharacter> expectedParty = new HashSet<>();
    expectedParty.add(knight);
    expectedParty.add(engineer);
    expectedParty.add(thief);
    expectedParty.add(blackMage);
    expectedParty.add(whiteMage);
    assertEquals(expectedParty, new HashSet<>(this.player.getParty().getCharacters()));
  }

  @Test
  void getInventory() throws InvalidStatValueException {
    Set<Weapon> expextedInventory = new HashSet<>();
    getGameDriver(seed);
    AbstractWeaponFactory factory = new AxeFactory();
    for (int i = 0; i < 4; i++) {
      expextedInventory.add(factory.create());
    }
    factory = new BowFactory();
    for (int i = 0; i < 4; i++) {
      expextedInventory.add(factory.create());
    }
    factory = new KnifeFactory();
    for (int i = 0; i < 4; i++) {
      expextedInventory.add(factory.create());
    }
    factory = new StaffFactory();
    for (int i = 0; i < 4; i++) {
      expextedInventory.add(factory.create());
    }
    factory = new SwordFactory();
    for (int i = 0; i < 4; i++) {
      expextedInventory.add(factory.create());
    }
    assertEquals(expextedInventory, new HashSet<>(this.player.getInventory().getItems()));
  }
}