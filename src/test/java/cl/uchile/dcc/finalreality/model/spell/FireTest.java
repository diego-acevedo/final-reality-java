package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.*;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Knight;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Fire;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class FireTest {

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
    this.spell = new Fire();
    this.staff = new Staff("Staff", 100, 100, 10);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.enemy = new Enemy("Enemy", 100, 100, 100,100, turnsQueue);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    blackMage.equip(staff);
    whiteMage.equip(staff);
  }

  @Test
  void fireSpellTest()
      throws InvalidMagicWeaponException, InvalidManaValueException,
      InvalidStatValueException, InvalidMageException,
      InvalidTargetCharacterException {
    assertEquals("""
        Reduces opponent's life with a 20% chance they'll burn.
        Type: Black
        Cost: 15 MP""", spell.description());
    assertThrows(InvalidTargetCharacterException.class, () -> blackMage.useMagic(spell, knight));
    assertThrows(InvalidMageException.class, () -> whiteMage.useMagic(spell, enemy));
    blackMage.useMagic(spell, enemy);
    assertEquals(90, enemy.getCurrentHp());
    assertFalse(enemy.getBurntStatus().isBurnt());
    blackMage.useMagic(spell, enemy);
    assertEquals(80, enemy.getCurrentHp());
    assertTrue(enemy.getBurntStatus().isBurnt());
    blackMage.setCurrentMp(10);
    assertThrows(InvalidManaValueException.class, () -> blackMage.useMagic(spell, enemy));
  }

}