package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.factories.character.AbstractCharacterFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.BlackMageFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.EngineerFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.KnightFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.ThiefFactory;
import cl.uchile.dcc.finalreality.controller.factories.character.WhiteMageFactory;
import cl.uchile.dcc.finalreality.controller.factories.enemy.AbstractEnemyFactory;
import cl.uchile.dcc.finalreality.controller.factories.enemy.EnemyFactory;
import cl.uchile.dcc.finalreality.controller.player.Player;
import cl.uchile.dcc.finalreality.controller.states.GameState;
import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.NonMagicalCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that controls the logic of the game.
 */
public class GameDriver {

  private GameState gameState;
  private final Player player;
  private final List<PlayerCharacter> playerCharacterList;
  private final List<Enemy> enemyList;
  private final BlockingQueue<GameCharacter> turnsQueue;

  /**
   * Creates a game controller with all characters and weapons initialized.
   *
   * @throws InvalidStatValueException Characters and weapons must be created with the right values.
   */
  public GameDriver() throws InvalidStatValueException {
    this.player = new Player();
    this.playerCharacterList = new ArrayList<>();
    this.enemyList = new ArrayList<>();
    this.turnsQueue = new LinkedBlockingQueue<>();

    this.initializePlayerCharacters(new BlackMageFactory());
    this.initializePlayerCharacters(new EngineerFactory());
    this.initializePlayerCharacters(new ThiefFactory());
    this.initializePlayerCharacters(new KnightFactory());
    this.initializePlayerCharacters(new WhiteMageFactory());
    this.initializeEnemies();
  }

  /**
   * Creates all characters the player has to choose for their party.
   *
   * @param factory A factory for a certain type of {@link PlayerCharacter}
   * @throws InvalidStatValueException Characters must be created with the right values.
   */
  private void initializePlayerCharacters(AbstractCharacterFactory factory)
      throws InvalidStatValueException {
    for (int i = 0; i < 3; i++) {
      playerCharacterList.add(factory.create(turnsQueue));
    }
  }

  /**
   * Creates all enemies the player has to fight against.
   *
   * @throws InvalidStatValueException Enemies must be created with the right values.
   */
  private void initializeEnemies() throws InvalidStatValueException {
    AbstractEnemyFactory factory = new EnemyFactory();
    for (int i = 0; i < 5; i++) {
      Enemy enemy = factory.create(turnsQueue);
      enemyList.add(enemy);
      turnsQueue.add(enemy);
    }
  }

  /**
   * Choose a character by thier index from the available
   * characters and puts it into the player's party.
   *
   * @param n Index of the character from the characters list.
   */
  public void addCharacterToParty(int n) {
    PlayerCharacter character = playerCharacterList.get(n);
    player.addCharacter(character);
    turnsQueue.add(character);
  }

  /**
   * Equips a {@code weapon} to the {@code character} if possible.
   *
   * @param weapon {@link Weapon} to be equipped.
   * @param character {@link PlayerCharacter} who will recieve the {@link Weapon}.
   */
  public void equip(Weapon weapon, PlayerCharacter character) {
    try {
      character.equip(weapon);
    } catch (InvalidEquipableWeaponException e) {
      System.out.println("You cannot equip this weapon to this character.");
    }
  }

  /**
   * Attacks a {@link GameCharacter} using a {@link GameCharacter} if possible.
   *
   * @param attacker {@link GameCharacter} who attacks.
   * @param target {@link GameCharacter} who gets attacked.
   */
  public void attack(GameCharacter attacker, GameCharacter target) {
    try {
      attacker.attack(target);
    } catch (InvalidTargetCharacterException e1) {
      System.out.println("You cannot attack this character.");
    } catch (InvalidStatValueException e2) {
      System.out.println("There's been a problem setting new Hp value.");
    }
  }

  /**
   * Cast a {@link Spell} on a {@link GameCharacter} by another {@link GameCharacter}.
   *
   * @param attacker {@link GameCharacter} who attacks.
   * @param spell {@link Spell} being cast.
   * @param target {@link GameCharacter} who gets attacked.
   */
  public void useMagic(PlayerCharacter attacker, Spell spell, GameCharacter target) {
    try {
      attacker.useMagic(spell, target);
    } catch (InvalidTargetCharacterException invalidTarget) {
      System.out.println("You cannot use this spell on the selected character.");
    } catch (InvalidMagicWeaponException invalidWeapon) {
      System.out.println("You have to have equipped a magic weapon to cast spells.");
    } catch (NonMagicalCharacterException nonMagical) {
      System.out.println("This is a non-magical character. They can't cast spells.");
    } catch (InvalidMageException invalidMage) {
      System.out.println("This mage doesn't know how to cast this spell. Try another one.");
    } catch (InvalidManaValueException invalidMana) {
      System.out.println("This mage has run out of mana. The spell is too expensive.");
    } catch (InvalidStatValueException invalidStat) {
      System.out.println("There's been a problem setting new Hp value.");
    }
  }

  /**
   * Sets a new state to the game.
   *
   * @param gameState New {@link GameState}.
   */
  public void setGameState(GameState gameState) {
    this.gameState = gameState;
    gameState.setGameDriver(this);
  }

  /**
   * Checks if the enemies are still alive. If they're all dead, the game should end.
   *
   * @return True if at least one enemy is alive. False if they're all dead.
   */
  public boolean enemiesAlive() {
    boolean alive = false;
    for (Enemy enemy : enemyList) {
      alive = alive | enemy.isAlive();
    }
    return alive;
  }

  /**
   * Checks if the player's characters are still alive. If they're all dead, the game should end.
   *
   * @return True if at least one character is alive. False if they're all dead.
   */
  public boolean playerAlive() {
    return player.alive();
  }
}
