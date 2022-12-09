package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(16);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(1);
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(15);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(1);
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(11);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(2);
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    driver.moveCursor(4);
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    driver.moveCursor(3);
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(3);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(1);
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    driver.moveCursor(10);
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    driver.moveCursor(1);
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    driver.moveCursor(-1);
    driver.execute();
    assertEquals(End.class, this.driver.getGameState().getClass());
  }
}