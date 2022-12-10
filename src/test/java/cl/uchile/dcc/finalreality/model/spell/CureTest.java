package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class CureTest {

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
    GameDriver.getGameDriver(-4836872931923509408L);
    GameDriver.getGameDriver(-4836872931923509408L);
    this.spell = new Cure();
    this.staff = new Staff("Staff", 100, 100, 100);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.enemy = new Enemy("Enemy", 100, 100, 100,100, turnsQueue);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    blackMage.equip(staff);
    whiteMage.equip(staff);
  }

  @Test
  void cureSpellTest()
      throws InvalidStatValueException, InvalidMagicWeaponException,
      InvalidManaValueException, InvalidMageException,
      InvalidTargetCharacterException {
    enemy.setCurrentHp(50);
    knight.setCurrentHp(50);
    assertThrows(InvalidTargetCharacterException.class, () -> whiteMage.useMagic(spell, enemy));
    assertThrows(InvalidMageException.class, () -> blackMage.useMagic(spell, knight));
    whiteMage.useMagic(spell, knight);
    assertEquals(80, knight.getCurrentHp());
    whiteMage.setCurrentMp(10);
    assertThrows(InvalidManaValueException.class, () -> whiteMage.useMagic(spell, knight));
  }
}