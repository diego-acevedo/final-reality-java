package cl.uchile.dcc.finalreality.model_controller.controller;

import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.AbstractCharacterFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.BlackMageFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.EngineerFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.KnightFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.ThiefFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.character.WhiteMageFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.enemy.AbstractEnemyFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.factories.enemy.EnemyFactory;
import cl.uchile.dcc.finalreality.model_controller.controller.player.Inventory;
import cl.uchile.dcc.finalreality.model_controller.controller.player.Player;
import cl.uchile.dcc.finalreality.model_controller.controller.states.GameState;
import cl.uchile.dcc.finalreality.model_controller.controller.player.Party;
import cl.uchile.dcc.finalreality.model_controller.controller.states.Preparation;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.NonMagicalCharacterException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.spell.Spell;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that controls the logic of the game.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
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
  private String actionOutput = "";
  public static final int MAX_CHARACTERS = 5;
  public static final int MAX_ENEMIES = 6;
  public static Random RANDOM_GENERATOR = new Random();

  /**
   * Creates a {@link GameDriver game controller} with all {@link GameCharacter characters}
   * and {@link Weapon weapons} initialized.
   *
   * @throws InvalidStatValueException Characters and weapons must be created with the right values.
   */
  private GameDriver() throws InvalidStatValueException {
    this.cursor = 0;
    this.gameOver = false;
    setGameState(new Preparation(this));
    this.player = new Player();
    this.enemyList = new ArrayList<>();
    this.turnsQueue = new LinkedBlockingQueue<>();

    this.initializePlayerCharacters();
    this.initializeEnemies();
  }

  /**
   * If a {@link GameDriver driver} has already been created, it returns that driver.
   * If there isn't a driver created, it creates one and returns it.
   *
   * @param seed A seed to randomly generate the game's values.
   * @return The sigleton object {@link GameDriver game driver}.
   * @throws InvalidStatValueException Values must be valid.
   */
  public static GameDriver getGameDriver(long seed) throws InvalidStatValueException {
    RANDOM_GENERATOR = new Random(seed);
    if (DRIVER == null) {
      DRIVER = new GameDriver();
    }
    return DRIVER;
  }

  /**
   * Deletes the current {@link GameDriver driver}.
   */
  public static void resetDriver() {
    DRIVER = null;
  }

  /**
   * Creates all {@link PlayerCharacter characters} the {@link Player player} will hold
   * in their {@link Party party}.
   *
   * @throws InvalidStatValueException Characters must be created with valid values.
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
   * Creates all {@link Enemy enemies} the player has to fight against.
   *
   * @throws InvalidStatValueException Enemies must be created with valid values.
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
   * Equips a {@link Weapon weapon} to the {@link PlayerCharacter character} if possible.
   *
   * @param weapon {@link Weapon Weapon} to be equipped.
   * @param character {@link PlayerCharacter Character} who will recieve the {@link Weapon weapon}.
   */
  public String equip(Weapon weapon, PlayerCharacter character) {
    try {
      Weapon oldWeapon = character.getEquippedWeapon();
      character.equip(weapon);
      if (!oldWeapon.isNull()) {
        this.player.getInventory().addItem(oldWeapon);
      }
      return "%s succesfully equipped to %s."
          .formatted(weapon.getName(), character.getName());
    } catch (InvalidEquipableWeaponException e) {
      this.player.getInventory().addItem(weapon);
      return "You cannot equip this %s to this %s."
          .formatted(weapon.getName(), character.getName());
    }
  }

  /**
   * Attacks a {@link GameCharacter character} using another {@link GameCharacter character}
   * if possible.
   *
   * @param attacker {@link GameCharacter Character} who attacks.
   * @param target {@link GameCharacter Character} who gets attacked.
   */
  public String attack(GameCharacter attacker, GameCharacter target) {
    try {
      attacker.attack(target);
      attacker.waitTurn();
      setTransitionSucceeded(true);
      return "%s has attacked %s"
          .formatted(attacker.getName(), target.getName());
    } catch (InvalidTargetCharacterException e1) {
      return "%s cannot attack this %s."
          .formatted(attacker.getName(), target.getName());
    } catch (InvalidStatValueException e2) {
      return "There's been a problem setting new Hp value.";
    } catch (NullWeaponException e3) {
      return "%s has no weapon equipped."
          .formatted(attacker.getName());
    }
  }

  /**
   * Cast a {@link Spell spell} on a {@link GameCharacter character} by another
   * {@link GameCharacter character}.
   *
   * @param attacker {@link GameCharacter Character} who attacks.
   * @param spell {@link Spell Spell} being cast.
   * @param target {@link GameCharacter Character} who gets attacked.
   */
  public String useMagic(PlayerCharacter attacker, Spell spell, GameCharacter target) {
    try {
      attacker.useMagic(spell, target);
      attacker.waitTurn();
      setTransitionSucceeded(true);
      return "%s has used %s on %s"
          .formatted(attacker.getName(),
              spell.getClass().getSimpleName(),
              target.getName());
    } catch (InvalidTargetCharacterException invalidTarget) {
      return "%s cannot use %s on %s."
          .formatted(attacker.getName(),
              spell.getClass().getSimpleName(),
              target.getName());
    } catch (InvalidMagicWeaponException invalidWeapon) {
      return "You have to have equipped a magic weapon to cast spells.";
    } catch (NonMagicalCharacterException nonMagical) {
      return "This is a non-magical character. %s can't cast spells."
          .formatted(attacker.getName());
    } catch (InvalidMageException invalidMage) {
      return "%s doesn't know how to cast %s."
          .formatted(attacker.getClass().getSimpleName(),
              spell.getClass().getSimpleName());
    } catch (InvalidManaValueException invalidMana) {
      return "This mage has run out of mana. The spell is too expensive.";
    } catch (InvalidStatValueException invalidStat) {
      return "There's been a problem setting new Hp value.";
    }
  }

  /**
   * Sets a new {@link GameState state} to the game.
   *
   * @param gameState New {@link GameState state}.
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
   * Returns the current {@link GameState state}.
   *
   * @return The current {@link GameState state}.
   */
  public GameState getGameState() {
    return gameState;
  }

  /**
   * Executes the action the game should do in the current {@link GameState state}.
   */
  public void execute() {
    this.gameState.execute();
  }

  /**
   * Checks if the {@link Enemy enemies} are still alive. If they're all dead, the game should end.
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
   * Checks if the {@link Player player}'s {@link PlayerCharacter characters} are still alive.
   * If they're all dead, the game should end.
   *
   * @return True if at least one character is alive. False if they're all dead.
   */
  public boolean playerAlive() {
    return player.alive();
  }

  /**
   * Checks if the game is over.
   */
  public void checkGameStatus() {
    this.gameOver = !(playerAlive() && enemiesAlive());
  }

  /**
   * Returns the game current status.
   *
   * @return The game current status.
   */
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

  /**
   * Returns the {@link GameDriver driver}'s turns queue.
   *
   * @return The {@link GameDriver driver}'s turns queue.
   */
  public BlockingQueue<GameCharacter> getTurnsQueue() {
    return turnsQueue;
  }

  /**
   * Sets a new {@link GameCharacter character} as the current character playing.
   *
   * @param character The {@link GameCharacter character} being set.
   */
  public void setCurrentCharacter(GameCharacter character) {
    this.currentCharacter = character;
  }

  /**
   * Returns the current {@link GameCharacter character} playing.
   *
   * @return The current {@link GameCharacter character} playing.
   */
  public GameCharacter getCurrentCharacter() {
    return this.currentCharacter;
  }

  /**
   * Returns the list of {@link Enemy enemies} in the game.
   *
   * @return The list of {@link Enemy enemies} in the game.
   */
  public List<Enemy> getEnemyList() {
    return enemyList;
  }

  /**
   * Returns the list of {@link PlayerCharacter characters} in the
   * {@link Party party}.
   *
   * @return The list of {@link PlayerCharacter characters} in the
   *     {@link Party party}.
   */
  public List<PlayerCharacter> getPlayerCharacters() {
    return this.player.getParty().getCharacters();
  }

  /**
   * Returns the list of {@link Enemy enemies} that are still alive.
   *
   * @return The list of {@link Enemy enemies} that are still alive.
   */
  public List<Enemy> getAliveEnemies() {
    List<Enemy> enemies = getEnemyList();
    List<Enemy> aliveEnemies = new ArrayList<>();
    for (Enemy enemy : enemies) {
      if (enemy.isAlive()) {
        aliveEnemies.add(enemy);
      }
    }
    return aliveEnemies;
  }

  /**
   * Returns the list of {@link PlayerCharacter characters} that are still alive.
   *
   * @return The list of {@link PlayerCharacter characters} that are still alive.
   */
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

  /**
   * Returns the list of {@link Weapon weapons} in the
   * {@link Inventory inventory}.
   *
   * @return The list of {@link Weapon weapons} in the
   *     {@link Inventory inventory}.
   */
  public List<Weapon> getWeapons() {
    return this.player.getInventory().getItems();
  }

  /**
   * Returns true if the action was succefully executed and, therefore, the transition can occur.
   *
   * @return True if the action was succefully executed, false if not.
   */
  public boolean isTransitionSucceeded() {
    return transitionSucceeded;
  }

  /**
   * Change the status of the transition.
   *
   * @param transitionSucceeded New status of the transition.
   */
  public void setTransitionSucceeded(boolean transitionSucceeded) {
    this.transitionSucceeded = transitionSucceeded;
  }

  /**
   * Returns the options that can be executed in the current state.
   *
   * @return The game state options.
   */
  public List<String> getOptions() {
    return gameState.options();
  }

  /**
   * Returns the instructions for the current {@link GameState phase}.
   *
   * @return Instruction for current {@link GameState phase}.
   */
  public String getInstructions() {
    return gameState.stateInstruction();
  }

  /**
   * Set the action output to a new value. Often called when a {@link PlayerCharacter player's character}
   * completes an action.
   *
   * @param s New string value for the output.
   */
  public void setActionOutput(String s) {
    this.actionOutput = s;
  }

  /**
   * Returns the action output.
   *
   * @return Action output.
   */
  public String getActionOutput() {
    return actionOutput;
  }

  /**
   * Returns the stats of the element being pointed by the cursor.
   *
   * @return The stats of the element being pointed by the cursor.
   */
  public String getStats() {
    return gameState.getStats();
  }
}
