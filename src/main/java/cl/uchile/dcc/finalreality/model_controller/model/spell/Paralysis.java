package cl.uchile.dcc.finalreality.model_controller.model.spell;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model_controller.model.effect.ParalysisEffect;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that paralyses enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Paralysis extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException {
    character.changeParalysisStatus(new ParalysisEffect());
    mage.setCurrentMp(mage.getCurrentMp() - 25);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException {
    checkMana(mage.getCurrentMp());
    character.receiveSpell(this, mage, weapon);
  }

  @Override
  public void checkMana(int mageMana) throws InvalidManaValueException {
    if (mageMana < 25) {
      throw new InvalidManaValueException("There's not enough mana to cast a paralysing spell.");
    }
  }

  @Override
  public String description() {
    return """
        Paralyze the oponent.
        Type: White
        Cost: 25 MP""";
  }

  @Override
  public String toString() {
    return "Paralysis";
  }
}
