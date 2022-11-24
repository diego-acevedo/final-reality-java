package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.effect.ParalysisEffect;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that paralyses enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Paralysis extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon) {
    character.changeParalysisStatus(new ParalysisEffect());
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
}
