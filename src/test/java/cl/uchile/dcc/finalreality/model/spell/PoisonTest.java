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

class PoisonTest {

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
    this.spell = new Poison();
    this.staff = new Staff("Staff", 100, 100, 10);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.enemy = new Enemy("Enemy", 100, 100, 100,100, turnsQueue);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    blackMage.equip(staff);
    whiteMage.equip(staff);
  }

  @Test
  void poisonSpellTest()
      throws InvalidMagicWeaponException, InvalidManaValueException,
      InvalidStatValueException, InvalidMageException,
      InvalidTargetCharacterException {
    assertThrows(InvalidTargetCharacterException.class, () -> whiteMage.useMagic(spell, knight));
    assertThrows(InvalidMageException.class, () -> blackMage.useMagic(spell, enemy));
    whiteMage.useMagic(spell, enemy);
    assertTrue(enemy.getPoisonStatus().isPoisoned());
    whiteMage.setCurrentMp(35);
    assertThrows(InvalidManaValueException.class, () -> whiteMage.useMagic(spell, enemy));
  }
}