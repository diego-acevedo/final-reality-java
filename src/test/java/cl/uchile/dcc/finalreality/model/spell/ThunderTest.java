package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.*;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Knight;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Thunder;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import static org.junit.jupiter.api.Assertions.*;

class ThunderTest {

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
    this.spell = new Thunder();
    this.staff = new Staff("Staff", 100, 100, 10);
    this.whiteMage = new WhiteMage("White Mage", 100, 100, 100, turnsQueue);
    this.blackMage = new BlackMage("Black Mage", 100, 100, 100, turnsQueue);
    this.enemy = new Enemy("Enemy", 100, 100, 100,100, turnsQueue);
    this.knight = new Knight("Knight", 100, 100, turnsQueue);
    blackMage.equip(staff);
    whiteMage.equip(staff);
  }

  @Test
  void thunderSpellTest()
      throws InvalidMagicWeaponException, InvalidManaValueException,
      InvalidStatValueException, InvalidMageException,
      InvalidTargetCharacterException {
    assertEquals("""
        Reduces opponent's life with a 30% chance of paralysis.
        Type: Black
        Cost: 15 MP""", spell.description());
    assertThrows(InvalidMageException.class, () -> whiteMage.useMagic(spell, enemy));
    assertThrows(InvalidTargetCharacterException.class, () -> blackMage.useMagic(spell, knight));
    blackMage.useMagic(spell, enemy);
    assertEquals(90, enemy.getCurrentHp());
    assertFalse(enemy.isParalysed());
    blackMage.useMagic(spell, enemy);
    assertEquals(80, enemy.getCurrentHp());
    assertTrue(enemy.isParalysed());
    blackMage.setCurrentMp(10);
    assertThrows(InvalidManaValueException.class, () -> blackMage.useMagic(spell, enemy));
  }

}