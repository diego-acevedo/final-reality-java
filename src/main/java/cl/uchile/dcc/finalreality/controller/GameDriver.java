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
import cl.uchile.dcc.finalreality.controller.states.Preparation;
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
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that controls the logic of the game.
 */
public class GameDriver {

  public static GameDriver DRIVER;
  private GameState gameState;
  private boolean transitionSucceeded;
  private final Player player;
  private final List<Enemy> enemyList;
  private final BlockingQueue<GameCharacter> turnsQueue;
  private GameCharacter currentCharacter;
  private int cursor;
  private boolean gameOver;
  public static final int MAX_CHARACTERS = 5;
  public static final int MAX_ENEMIES = 6;
  public static Random RANDOM_GENERATOR;

  /**
   * Creates a game controller with all characters and weapons initialized.
   *
   * @throws InvalidStatValueException Characters and weapons must be created with the right values.
   */
  private GameDriver(long seed) throws InvalidStatValueException {
    this.cursor = 0;
    this.gameOver = false;
    setGameState(new Preparation(this));
    this.player = new Player();
    this.enemyList = new ArrayList<>();
    this.turnsQueue = new LinkedBlockingQueue<>();

    this.initializePlayerCharacters();
    this.initializeEnemies();
  }

  public static GameDriver getGameDriver(long seed) throws InvalidStatValueException {
    if (DRIVER == null) {
      DRIVER = new GameDriver(seed);
    }
    RANDOM_GENERATOR = new Random(seed);
    return DRIVER;
  }

  /**
   * Creates all characters the player has to choose for their party.
   *
   * @throws InvalidStatValueException Characters must be created with the right values.
   */
  private void initializePlayerCharacters()
      throws InvalidStatValueException {
    AbstractCharacterFactory[] factories = {new EngineerFactory(), new KnightFactory(),
        new ThiefFactory(), new BlackMageFactory(), new WhiteMageFactory()};
    for (int i = 0; i < MAX_CHARACTERS; i++) {
      int n = RANDOM_GENERATOR.nextInt(0, 5);
      PlayerCharacter character = factories[n].create(turnsQueue);
      player.addCharacter(character);
      turnsQueue.add(character);
    }
  }

  /**
   * Creates all enemies the player has to fight against.
   *
   * @throws InvalidStatValueException Enemies must be created with the right values.
   */
  private void initializeEnemies() throws InvalidStatValueException {
    AbstractEnemyFactory factory = new EnemyFactory();
    for (int i = 0; i < MAX_ENEMIES; i++) {
      Enemy enemy = factory.create(turnsQueue);
      enemyList.add(enemy);
      turnsQueue.add(enemy);
    }
  }

  /**
   * Equips a {@code weapon} to the {@code character} if possible.
   *
   * @param weapon {@link Weapon} to be equipped.
   * @param character {@link PlayerCharacter} who will recieve the {@link Weapon}.
   */
  public String equip(Weapon weapon, PlayerCharacter character) {
    try {
      character.equip(weapon);
      setTransitionSucceeded(true);
      return "%s succesfully equipped to %s.".formatted(weapon.getClass(), character.getClass());
    } catch (InvalidEquipableWeaponException e) {
      return "You cannot equip this %s to this %s.".formatted(weapon.getClass(), character.getClass());
    }
  }

  /**
   * Attacks a {@link GameCharacter} using a {@link GameCharacter} if possible.
   *
   * @param attacker {@link GameCharacter} who attacks.
   * @param target {@link GameCharacter} who gets attacked.
   */
  public String attack(GameCharacter attacker, GameCharacter target) {
    try {
      attacker.attack(target);
      setTransitionSucceeded(true);
      return "%s has attacked %s".formatted(attacker.getClass(), target.getClass());
    } catch (InvalidTargetCharacterException e1) {
      return "%s cannot attack this %s.".formatted(attacker.getClass(), target.getClass());
    } catch (InvalidStatValueException e2) {
      return "There's been a problem setting new Hp value.";
    }
  }

  /**
   * Cast a {@link Spell} on a {@link GameCharacter} by another {@link GameCharacter}.
   *
   * @param attacker {@link GameCharacter} who attacks.
   * @param spell {@link Spell} being cast.
   * @param target {@link GameCharacter} who gets attacked.
   */
  public String useMagic(PlayerCharacter attacker, Spell spell, GameCharacter target) {
    try {
      attacker.useMagic(spell, target);
      setTransitionSucceeded(true);
      return "%s has used %s on %s".formatted(attacker.getClass(), spell.getClass(), target.getClass());
    } catch (InvalidTargetCharacterException invalidTarget) {
      return "%s cannot use %s on %s.".formatted(attacker.getClass(), spell.getClass(), target.getClass());
    } catch (InvalidMagicWeaponException invalidWeapon) {
      return "You have to have equipped a magic weapon to cast spells.";
    } catch (NonMagicalCharacterException nonMagical) {
      return "This is a non-magical character. %s can't cast spells.".formatted(attacker.getClass());
    } catch (InvalidMageException invalidMage) {
      return "%s doesn't know how to cast %s.".formatted(attacker.getClass(), spell.getClass());
    } catch (InvalidManaValueException invalidMana) {
      return "This mage has run out of mana. The spell is too expensive.";
    } catch (InvalidStatValueException invalidStat) {
      return "There's been a problem setting new Hp value.";
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
    this.transitionSucceeded = false;
    this.resetCursor();
    if (this.gameState.executeAutomatically()) {
      this.gameState.execute();
    }
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

  public void checkGameStatus() {
    this.gameOver = !(playerAlive() && enemiesAlive());
  }

  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Returns the position of the cursor.
   *
   * @return Position of the cursor.
   */
  public int getCursor() {
    return cursor;
  }

  /**
   * Moves the cursor n positions (typically 1 or -1).
   *
   * @param n Spaces the cursor must move.
   */
  public void moveCursor(int n) {
    this.cursor += n;
  }

  /**
   * Moves the cursor to the initial position (0).
   */
  public void resetCursor() {
    this.cursor = 0;
  }

  public BlockingQueue<GameCharacter> getTurnsQueue() {
    return turnsQueue;
  }

  public void setCurrentCharacter(GameCharacter character) {
    this.currentCharacter = character;
  }

  public GameCharacter getCurrentCharacter() {
    return this.currentCharacter;
  }

  public List<Enemy> getEnemyList() {
    return enemyList;
  }

  public List<PlayerCharacter> getPlayerCharacters() {
    return this.player.getParty().getCharacters();
  }

  public List<Enemy> getAliveEnemies() {
    List<Enemy> enemies = getEnemyList();
    List<Enemy> aliveEnemies = new ArrayList<>();
    for (Enemy enemy: enemies) {
      if (enemy.isAlive()) {
        aliveEnemies.add(enemy);
      }
    }
    return aliveEnemies;
  }

  public List<PlayerCharacter> getAliveCharacters() {
    List<PlayerCharacter> characters = getPlayerCharacters();
    List<PlayerCharacter> aliveCharacters = new ArrayList<>();
    for (PlayerCharacter character : characters) {
      if (character.isAlive()) {
        aliveCharacters.add(character);
      }
    }
    return aliveCharacters;
  }

  public List<Weapon> getWeapons() {
    return this.player.getInventory().getItems();
  }

  public boolean isTransitionSucceeded() {
    return transitionSucceeded;
  }

  public void setTransitionSucceeded(boolean transitionSucceeded) {
    this.transitionSucceeded = transitionSucceeded;
  }
}
