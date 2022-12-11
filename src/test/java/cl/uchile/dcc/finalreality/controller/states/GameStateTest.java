package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.player.*;
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
    assertEquals(new ArrayList<String>(), this.driver.getGameState().options());
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(16);
    assertEquals("->", this.driver.getGameState().options().get(16).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Engineer.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(1);
    assertEquals("->", this.driver.getGameState().options().get(1).substring(0, 2));
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(15);
    assertEquals("->", this.driver.getGameState().options().get(15).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Knight.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(1);
    assertEquals("->", this.driver.getGameState().options().get(1).substring(0, 2));
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(11);
    assertEquals("->", this.driver.getGameState().options().get(11).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(2);
    assertEquals("->", this.driver.getGameState().options().get(2).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(4);
    assertEquals("->", this.driver.getGameState().options().get(4).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(WhiteMage.class, this.driver.getCurrentCharacter().getClass());
    this.driver.getGameState().options();
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(3);
    assertEquals("->", this.driver.getGameState().options().get(3).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.getCurrentCharacter().setCurrentHp(0);
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(3);
    assertEquals("->", this.driver.getGameState().options().get(3).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(Thief.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(1);
    assertEquals("->", this.driver.getGameState().options().get(1).substring(0, 2));
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(SelectWeapon.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(10);
    assertEquals("->", this.driver.getGameState().options().get(10).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpell.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(1);
    assertEquals("->", this.driver.getGameState().options().get(1).substring(0, 2));
    driver.execute();
    assertEquals(SelectSpellTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(-1);
    assertEquals("->", this.driver.getGameState().options()
        .get(this.driver.getGameState().options().size() - 1).substring(0, 2));
    driver.execute();
    assertEquals(PlayerSelectAction.class, this.driver.getGameState().getClass());
    assertEquals(BlackMage.class, this.driver.getCurrentCharacter().getClass());
    driver.getCurrentCharacter().setCurrentHp(0);
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.moveCursor(1);
    assertEquals("->", this.driver.getGameState().options().get(1).substring(0, 2));
    driver.execute();
    assertEquals(SelectAttackTarget.class, this.driver.getGameState().getClass());
    assertEquals("->", this.driver.getGameState().options().get(0).substring(0, 2));
    driver.execute();
    assertEquals(End.class, this.driver.getGameState().getClass());
  }
}