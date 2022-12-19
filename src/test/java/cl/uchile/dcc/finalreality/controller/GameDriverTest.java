package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character.*;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.enemy.EnemyFactory;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.controller.factories.weapon.*;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Poison;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Thunder;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class GameDriverTest {

  private GameDriver driver;
  private PlayerCharacter blackMage;
  private PlayerCharacter engineer;
  private PlayerCharacter knight;
  private PlayerCharacter thief;
  private PlayerCharacter whiteMage;
  private Enemy enemy;
  private final BlockingQueue<GameCharacter> turnsQueue = new LinkedBlockingQueue<>();

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    this.driver = GameDriver.getGameDriver((new Random()).nextLong());
    this.blackMage = (new BlackMageFactory()).create(turnsQueue);
    this.engineer = (new EngineerFactory()).create(turnsQueue);
    this.knight = (new KnightFactory()).create(turnsQueue);
    this.thief = (new ThiefFactory()).create(turnsQueue);
    this.whiteMage = (new WhiteMageFactory()).create(turnsQueue);
    this.enemy = (new EnemyFactory()).create(turnsQueue);
  }

  @Test
  void equip() throws InvalidStatValueException {
    Weapon sword = (new SwordFactory()).create();
    assertEquals("You cannot equip this Sword 1 to this BlackMage 1.", driver.equip(sword, blackMage));
    assertEquals("You cannot equip this Sword 1 to this Engineer 1.", driver.equip(sword, engineer));
    assertEquals("Sword 1 succesfully equipped to Knight 1.", driver.equip(sword, knight));
    assertEquals("Sword 1 succesfully equipped to Thief 1.", driver.equip(sword, thief));
    assertEquals("You cannot equip this Sword 1 to this WhiteMage 1.", driver.equip(sword, whiteMage));

    Weapon axe = (new AxeFactory()).create();
    assertEquals("You cannot equip this Axe 1 to this BlackMage 1.", driver.equip(axe, blackMage));
    assertEquals("Axe 1 succesfully equipped to Engineer 1.", driver.equip(axe, engineer));
    assertEquals("Axe 1 succesfully equipped to Knight 1.", driver.equip(axe, knight));
    assertEquals("You cannot equip this Axe 1 to this Thief 1.", driver.equip(axe, thief));
    assertEquals("You cannot equip this Axe 1 to this WhiteMage 1.", driver.equip(axe, whiteMage));

    Weapon knife = (new KnifeFactory()).create();
    assertEquals("Knife 1 succesfully equipped to BlackMage 1.", driver.equip(knife, blackMage));
    assertEquals("You cannot equip this Knife 1 to this Engineer 1.", driver.equip(knife, engineer));
    assertEquals("Knife 1 succesfully equipped to Knight 1.", driver.equip(knife, knight));
    assertEquals("Knife 1 succesfully equipped to Thief 1.", driver.equip(knife, thief));
    assertEquals("You cannot equip this Knife 1 to this WhiteMage 1.", driver.equip(knife, whiteMage));

    Weapon staff = (new StaffFactory()).create();
    assertEquals("Staff 1 succesfully equipped to BlackMage 1.", driver.equip(staff, blackMage));
    assertEquals("You cannot equip this Staff 1 to this Engineer 1.", driver.equip(staff, engineer));
    assertEquals("You cannot equip this Staff 1 to this Knight 1.", driver.equip(staff, knight));
    assertEquals("You cannot equip this Staff 1 to this Thief 1.", driver.equip(staff, thief));
    assertEquals("Staff 1 succesfully equipped to WhiteMage 1.", driver.equip(staff, whiteMage));

    Weapon bow = (new BowFactory()).create();
    assertEquals("You cannot equip this Bow 1 to this BlackMage 1.", driver.equip(bow, blackMage));
    assertEquals("Bow 1 succesfully equipped to Engineer 1.", driver.equip(bow, engineer));
    assertEquals("You cannot equip this Bow 1 to this Knight 1.", driver.equip(bow, knight));
    assertEquals("Bow 1 succesfully equipped to Thief 1.", driver.equip(bow, thief));
    assertEquals("You cannot equip this Bow 1 to this WhiteMage 1.", driver.equip(bow, whiteMage));
  }

  @Test
  void attack() throws InvalidStatValueException {
    Weapon sword = (new SwordFactory()).create();
    assertEquals("Knight 1 has no weapon equipped.", driver.attack(knight, engineer));
    driver.equip(sword, knight);
    assertEquals("Knight 1 cannot attack this Engineer 1.", driver.attack(knight, engineer));
    assertEquals("Knight 1 has attacked Enemy 1", driver.attack(knight, enemy));
    assertEquals("Enemy 1 has attacked Knight 1", driver.attack(enemy, knight));
    assertEquals("Enemy 1 cannot attack this Enemy 1.", driver.attack(enemy, enemy));
  }

  @Test
  void useMagic() throws InvalidStatValueException {
    Spell whiteSpell = new Poison();
    assertEquals("This is a non-magical character. Knight 1 can't cast spells.",
        driver.useMagic(knight, whiteSpell, enemy));
    assertEquals("You have to have equipped a magic weapon to cast spells.",
        driver.useMagic(blackMage, whiteSpell, enemy));
    driver.equip((new StaffFactory()).create(), blackMage);
    assertEquals("BlackMage doesn't know how to cast Poison.",
        driver.useMagic(blackMage, whiteSpell, enemy));
    Spell blackSpell = new Thunder();
    ((BlackMage) blackMage).setCurrentMp(5);
    assertEquals("This mage has run out of mana. The spell is too expensive.",
        driver.useMagic(blackMage, blackSpell, enemy));
    ((BlackMage) blackMage).setCurrentMp(49);
    assertEquals("BlackMage 1 cannot use Thunder on Knight 1.",
        driver.useMagic(blackMage, blackSpell, knight));
    assertEquals("BlackMage 1 has used Thunder on Enemy 1",
        driver.useMagic(blackMage, blackSpell, enemy));
  }
}