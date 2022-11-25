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
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that controls the logic of the game.
 */
public class GameDriver {

  private GameState gameState;
  private Player player;
  private final List<PlayerCharacter> playerCharacterList;
  private final List<Enemy> enemyList;
  private final BlockingQueue<GameCharacter> turnsQueue;

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



  private void initializePlayerCharacters(AbstractCharacterFactory factory)
      throws InvalidStatValueException {
    for (int i = 0; i < 3; i++) {
      playerCharacterList.add(factory.create(turnsQueue));
    }
  }

  private void initializeEnemies() throws InvalidStatValueException {
    AbstractEnemyFactory factory = new EnemyFactory();
    for (int i = 0; i < 5; i++) {
      enemyList.add(factory.create(turnsQueue));
    }
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
    gameState.setGameDriver(this);
  }

  public boolean enemiesAlive() {
    boolean alive = false;
    for (Enemy enemy : enemyList) {
      alive = alive | enemy.isAlive();
    }
    return alive;
  }

  public boolean playerAlive() {
    return player.alive();
  }
}
