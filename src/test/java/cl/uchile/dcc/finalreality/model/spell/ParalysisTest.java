package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.exceptions.*;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.model_controller.model.spell.Paralysis;
import cl.uchile.dcc.finalreality.model_controller.model.spell.Spell;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ParalysisTest {

  private Spell spell;
  private BlackMage blackMage;
  private WhiteMage whiteMage;
  private PlayerCharacter knight;
  private Enemy enemy;
  private MagicWeapon staff;
  private BlockingQueue<GameCharacter> turnsQueue;

  @BeforeEach
  void setUp() throws InvalidStatValueException, InvalidEquipableWeaponException {
    turnsQueue = new LinkedBlockingQueue<>();
    GameDriver.resetDriver();
    GameDriver.getGameDriver(-6114617559689290466L);
    GameDriver.getGameDriver(-6114617559689290466L);
    this.spell = new Paralysis();
    this.staff = new Staff("Staff", 100, 100, 10);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.enemy = new Enemy("Enemy", 100, 100, 100,100, turnsQueue);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    blackMage.equip(staff);
    whiteMage.equip(staff);
  }

  @Test
  void paralysisSpellTest()
      throws InvalidMagicWeaponException, InvalidManaValueException,
      InvalidStatValueException, InvalidMageException,
      InvalidTargetCharacterException {
    assertEquals("""
        Paralyze the oponent.
        Type: White
        Cost: 25 MP""", spell.description());
    assertThrows(InvalidTargetCharacterException.class, () -> whiteMage.useMagic(spell, knight));
    assertThrows(InvalidMageException.class, () -> blackMage.useMagic(spell, enemy));
    whiteMage.useMagic(spell, enemy);
    assertTrue(enemy.isParalysed());
    whiteMage.setCurrentMp(20);
    assertThrows(InvalidManaValueException.class, () -> whiteMage.useMagic(spell, enemy));
  }

}