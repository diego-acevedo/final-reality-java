package cl.uchile.dcc.finalreality.model_controller.model.spell;

import static cl.uchile.dcc.finalreality.model_controller.controller.GameDriver.RANDOM_GENERATOR;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model_controller.model.effect.ParalysisEffect;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that throws thunderes towards enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Thunder extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException {
    int newHp = Math.max(0, character.getCurrentHp() - weapon.getMagicDamage());
    character.setCurrentHp(newHp);
    if (RANDOM_GENERATOR.nextFloat() <= 0.3) {
      character.changeParalysisStatus(new ParalysisEffect());
    }
    mage.setCurrentMp(mage.getCurrentMp() - 15);
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
  public String description() {
    return """
        Reduces opponent's life with a 30% chance of paralysis.
        Type: Black
        Cost: 15 MP""";
  }

  @Override
  public String toString() {
    return "Thunder";
  }
}
