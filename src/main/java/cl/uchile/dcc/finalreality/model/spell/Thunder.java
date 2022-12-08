package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.effect.ParalysisEffect;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import java.util.Random;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

/**
 * A class thah holds the information of a {@link Spell} that throws thunderes towards enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Thunder extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon)
      throws InvalidStatValueException {
    int newHp = Math.max(0, character.getCurrentHp() - weapon.getMagicDamage());
    character.setCurrentHp(newHp);
    Random random = new Random();
    if (RANDOM_GENERATOR.nextFloat() <= 0.3) {
      character.changeParalysisStatus(new ParalysisEffect());
    }
  }

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException {
    checkMana(mage.getCurrentMp());
    character.receiveSpell(this, mage, weapon);
  }

  @Override
  public void checkMana(int mageMana) throws InvalidManaValueException {
    if (mageMana < 15) {
      throw new InvalidManaValueException("There's not enough mana to cast a thunder spell.");
    }
  }

  @Override
  public String toString() {
    return "Thunder";
  }
}
