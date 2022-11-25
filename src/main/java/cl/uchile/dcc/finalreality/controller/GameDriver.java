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
import cl.uchile.dcc.finalreality.exceptions.*;
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
      Enemy enemy = factory.create(turnsQueue);
      enemyList.add(enemy);
      turnsQueue.add(enemy);
    }
  }

  public void addCharacterToParty(int n) {
    PlayerCharacter character = playerCharacterList.get(n);
    player.addCharacter(character);
    turnsQueue.add(character);
  }

  public void equip(Weapon weapon, PlayerCharacter character) {
    try {
      character.equip(weapon);
    } catch (InvalidEquipableWeaponException e) {
      System.out.println("You cannot equip this weapon to this character.");
    }
  }

  public void attack(GameCharacter attacker, GameCharacter target) {
    try {
      attacker.attack(target);
    } catch (InvalidTargetCharacterException e1) {
      System.out.println("You cannot attack this character.");
    } catch (InvalidStatValueException e2) {
      System.out.println("There's been a problem setting new Hp value.");
    }
  }

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
