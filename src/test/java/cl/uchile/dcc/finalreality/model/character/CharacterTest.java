package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.NonMagicalCharacterException;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.spell.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
  GameCharacter enemy;
  PlayerCharacter blackMage;
  PlayerCharacter engineer;
  PlayerCharacter knight;
  PlayerCharacter thief;
  PlayerCharacter whiteMage;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  void setUp() throws InvalidStatValueException {
    queue = new LinkedBlockingQueue<>();

    enemy = new Enemy("character", 10, 10, 10, 10, queue);
    blackMage = new BlackMage("character", 10, 10, 10, queue);
    engineer = new Engineer("character", 10, 10, queue);
    knight = new Knight("character", 10, 10, queue);
    thief = new Thief("character", 10, 10, queue);
    whiteMage = new WhiteMage("character", 10, 10, 10, queue);
  }

  @Test
  void SpellTest() {
    Spell cure = new Cure();
    Spell fire = new Fire();
    Spell paralysis = new Paralysis();
    Spell poison = new Poison();
    Spell thunder = new Thunder();
    assertThrows(NonMagicalCharacterException.class, () -> engineer.useMagic(cure, blackMage));
    assertThrows(InvalidMagicWeaponException.class, () -> blackMage.useMagic(cure, enemy));
  }

  @Test
  void testEqualsBetweenCharacters() {
    assertNotEquals(enemy, blackMage);
    assertNotEquals(enemy, engineer);
    assertNotEquals(enemy, knight);
    assertNotEquals(enemy, thief);
    assertNotEquals(enemy, whiteMage);

    assertNotEquals(blackMage, enemy);
    assertNotEquals(blackMage, engineer);
    assertNotEquals(blackMage, knight);
    assertNotEquals(blackMage, thief);
    assertNotEquals(blackMage, whiteMage);

    assertNotEquals(engineer, blackMage);
    assertNotEquals(engineer, enemy);
    assertNotEquals(engineer, knight);
    assertNotEquals(engineer, thief);
    assertNotEquals(engineer, whiteMage);

    assertNotEquals(knight, blackMage);
    assertNotEquals(knight, enemy);
    assertNotEquals(knight, engineer);
    assertNotEquals(knight, thief);
    assertNotEquals(knight, whiteMage);

    assertNotEquals(thief, blackMage);
    assertNotEquals(thief, enemy);
    assertNotEquals(thief, engineer);
    assertNotEquals(thief, knight);
    assertNotEquals(thief, whiteMage);

    assertNotEquals(whiteMage, blackMage);
    assertNotEquals(whiteMage, enemy);
    assertNotEquals(whiteMage, engineer);
    assertNotEquals(whiteMage, knight);
    assertNotEquals(whiteMage, thief);
  }

}