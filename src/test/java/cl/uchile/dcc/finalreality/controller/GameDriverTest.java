package cl.uchile.dcc.finalreality.controller;

import cl.uchile.dcc.finalreality.controller.factories.character.*;
import cl.uchile.dcc.finalreality.controller.factories.enemy.EnemyFactory;
import cl.uchile.dcc.finalreality.controller.factories.weapon.*;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.NullWeaponException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.spell.Poison;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.spell.Thunder;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
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
    assertEquals("You cannot equip this Sword to this BlackMage.", driver.equip(sword, blackMage));
    assertEquals("You cannot equip this Sword to this Engineer.", driver.equip(sword, engineer));
    assertEquals("Sword succesfully equipped to Knight.", driver.equip(sword, knight));
    assertEquals("Sword succesfully equipped to Thief.", driver.equip(sword, thief));
    assertEquals("You cannot equip this Sword to this WhiteMage.", driver.equip(sword, whiteMage));

    Weapon axe = (new AxeFactory()).create();
    assertEquals("You cannot equip this Axe to this BlackMage.", driver.equip(axe, blackMage));
    assertEquals("Axe succesfully equipped to Engineer.", driver.equip(axe, engineer));
    assertEquals("Axe succesfully equipped to Knight.", driver.equip(axe, knight));
    assertEquals("You cannot equip this Axe to this Thief.", driver.equip(axe, thief));
    assertEquals("You cannot equip this Axe to this WhiteMage.", driver.equip(axe, whiteMage));

    Weapon knife = (new KnifeFactory()).create();
    assertEquals("Knife succesfully equipped to BlackMage.", driver.equip(knife, blackMage));
    assertEquals("You cannot equip this Knife to this Engineer.", driver.equip(knife, engineer));
    assertEquals("Knife succesfully equipped to Knight.", driver.equip(knife, knight));
    assertEquals("Knife succesfully equipped to Thief.", driver.equip(knife, thief));
    assertEquals("You cannot equip this Knife to this WhiteMage.", driver.equip(knife, whiteMage));

    Weapon staff = (new StaffFactory()).create();
    assertEquals("Staff succesfully equipped to BlackMage.", driver.equip(staff, blackMage));
    assertEquals("You cannot equip this Staff to this Engineer.", driver.equip(staff, engineer));
    assertEquals("You cannot equip this Staff to this Knight.", driver.equip(staff, knight));
    assertEquals("You cannot equip this Staff to this Thief.", driver.equip(staff, thief));
    assertEquals("Staff succesfully equipped to WhiteMage.", driver.equip(staff, whiteMage));

    Weapon bow = (new BowFactory()).create();
    assertEquals("You cannot equip this Bow to this BlackMage.", driver.equip(bow, blackMage));
    assertEquals("Bow succesfully equipped to Engineer.", driver.equip(bow, engineer));
    assertEquals("You cannot equip this Bow to this Knight.", driver.equip(bow, knight));
    assertEquals("Bow succesfully equipped to Thief.", driver.equip(bow, thief));
    assertEquals("You cannot equip this Bow to this WhiteMage.", driver.equip(bow, whiteMage));
  }

  @Test
  void attack() throws InvalidStatValueException {
    Weapon sword = (new SwordFactory()).create();
    assertEquals("Knight has no weapon equipped.", driver.attack(knight, engineer));
    driver.equip(sword, knight);
    assertEquals("Knight cannot attack this Engineer.", driver.attack(knight, engineer));
    assertEquals("Knight has attacked Enemy", driver.attack(knight, enemy));
    assertEquals("Enemy has attacked Knight", driver.attack(enemy, knight));
    assertEquals("Enemy cannot attack this Enemy.", driver.attack(enemy, enemy));
  }

  @Test
  void useMagic() throws InvalidStatValueException {
    Spell whiteSpell = new Poison();
    assertEquals("This is a non-magical character. Knight can't cast spells.",
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
    assertEquals("BlackMage cannot use Thunder on Knight.",
        driver.useMagic(blackMage, blackSpell, knight));
    assertEquals("BlackMage has used Thunder on Enemy",
        driver.useMagic(blackMage, blackSpell, enemy));
  }
}