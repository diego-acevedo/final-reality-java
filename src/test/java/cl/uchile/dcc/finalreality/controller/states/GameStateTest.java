package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.controller.states.*;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

  private GameDriver driver;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    GameDriver.resetDriver();
    this.driver = GameDriver.getGameDriver(7501803088527288789L);
  }

  @Test
  void transitionsTest() throws InvalidStatValueException {
    assertEquals(Preparation.class, this.driver.getGameState().getClass());
    assertEquals(new ArrayList<String>(), this.driver.getOptions());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Engineer", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(16);
    assertEquals(">", this.driver.getOptions().get(16).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Sword
        Damage: 71
        Weight: 38""", this.driver.getStats());
    driver.execute();
    assertEquals("You cannot equip this Sword 1 to this Engineer 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Engineer", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    assertEquals("""
        Stats:
        Type: Axe
        Damage: 79
        Weight: 62""", this.driver.getStats());
    driver.execute();
    assertEquals("Axe 1 succesfully equipped to Engineer 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Engineer", this.driver.getInstructions());
    driver.moveCursor(1);
    assertEquals(">", this.driver.getOptions().get(1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    assertEquals("""
        Stats:
        Name: Enemy 1
        HP: 1301
        Weight : 18
        Attack: 67
        Defense: 8""", this.driver.getStats());
    driver.execute();
    assertEquals("Engineer 1 has attacked Enemy 1", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Knight", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(15);
    assertEquals(">", this.driver.getOptions().get(15).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Sword
        Damage: 67
        Weight: 65""", this.driver.getStats());
    driver.execute();
    assertEquals("Sword 2 succesfully equipped to Knight 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Knight", this.driver.getInstructions());
    driver.moveCursor(1);
    assertEquals(">", this.driver.getOptions().get(1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    assertEquals("""
        Stats:
        Name: Enemy 1
        HP: 1222
        Weight : 18
        Attack: 67
        Defense: 8""", this.driver.getStats());
    driver.execute();
    assertEquals("Knight 1 has attacked Enemy 1", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this WhiteMage", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(11);
    assertEquals(">", this.driver.getOptions().get(11).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Staff
        Damage: 64
        Magic Damage: 29
        Weight: 93""", this.driver.getStats());
    driver.execute();
    assertEquals("Staff 1 succesfully equipped to WhiteMage 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this WhiteMage", this.driver.getInstructions());
    driver.moveCursor(2);
    assertEquals(">", this.driver.getOptions().get(2).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a spell", this.driver.getInstructions());
    driver.moveCursor(4);
    assertEquals(">", this.driver.getOptions().get(4).substring(0, 1));
    assertEquals("""
        Stats:
        Reduces opponent's life with a 30% chance of paralysis.
        Type: Black
        Cost: 15 MP""", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("""
        Stats:
        Name: Enemy 6
        HP: 1460
        Weight : 39
        Attack: 56
        Defense: 15""", this.driver.getStats());
    driver.execute();
    assertEquals("WhiteMage doesn't know how to cast Thunder.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("Select an action for this WhiteMage", this.driver.getInstructions());
    this.driver.getOptions();
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a spell", this.driver.getInstructions());
    driver.moveCursor(3);
    assertEquals(">", this.driver.getOptions().get(3).substring(0, 1));
    assertEquals("""
        Stats:
        Poisons the oponent.
        Type: White
        Cost: 40 MP""", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("""
        Stats:
        Name: Enemy 6
        HP: 1460
        Weight : 39
        Attack: 56
        Defense: 15""", this.driver.getStats());
    driver.execute();
    assertEquals("WhiteMage 1 has used Poison on Enemy 6", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("Select an action for this Thief", this.driver.getInstructions());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Sword
        Damage: 71
        Weight: 38""", this.driver.getStats());
    driver.execute();
    assertEquals("Sword 1 succesfully equipped to Thief 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Thief", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(3);
    assertEquals(">", this.driver.getOptions().get(3).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Bow
        Damage: 56
        Weight: 56""", this.driver.getStats());
    driver.execute();
    assertEquals("Bow 1 succesfully equipped to Thief 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this Thief", this.driver.getInstructions());
    driver.moveCursor(1);
    assertEquals(">", this.driver.getOptions().get(1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    assertEquals("""
        Stats:
        Name: Enemy 1
        HP: 1155
        Weight : 18
        Attack: 67
        Defense: 8""", this.driver.getStats());
    driver.execute();
    assertEquals("Thief 1 has attacked Enemy 1", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this BlackMage", this.driver.getInstructions());
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a weapon", this.driver.getInstructions());
    driver.moveCursor(10);
    assertEquals(">", this.driver.getOptions().get(10).substring(0, 1));
    assertEquals("""
        Stats:
        Type: Staff
        Damage: 82
        Magic Damage: 44
        Weight: 11""", this.driver.getStats());
    driver.execute();
    assertEquals("Staff 2 succesfully equipped to BlackMage 1.", this.driver.getActionOutput());
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this BlackMage", this.driver.getInstructions());
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a spell", this.driver.getInstructions());
    driver.moveCursor(1);
    assertEquals(">", this.driver.getOptions().get(1).substring(0, 1));
    assertEquals("""
        Stats:
        Reduces opponent's life with a 20% chance they'll burn.
        Type: Black
        Cost: 15 MP""", this.driver.getStats());
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    driver.moveCursor(-1);
    assertEquals(">", this.driver.getOptions()
        .get(this.driver.getOptions().size() - 1).substring(0, 1));
    assertEquals("""
        Stats:
        Name: Enemy 6
        HP: 1460
        Weight : 39
        Attack: 56
        Defense: 15""", this.driver.getStats());
    driver.execute();
    assertEquals("BlackMage 1 has used Fire on Enemy 6", this.driver.getActionOutput());
    try {
      Thread.sleep(14000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select an action for this BlackMage", this.driver.getInstructions());
    driver.moveCursor(1);
    assertEquals(">", this.driver.getOptions().get(1).substring(0, 1));
    assertEquals("", this.driver.getStats());
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals(">", this.driver.getOptions().get(0).substring(0, 1));
    assertEquals("Select a target", this.driver.getInstructions());
    assertEquals("""
        Stats:
        Name: Enemy 1
        HP: 1099
        Weight : 18
        Attack: 67
        Defense: 8""", this.driver.getStats());
    driver.execute();
    assertEquals("Enemies won this fight...", this.driver.getActionOutput());
    assertEquals(End.class, this.driver.getGameState().getClass());
  }
}