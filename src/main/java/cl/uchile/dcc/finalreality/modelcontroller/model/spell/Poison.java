package cl.uchile.dcc.finalreality.modelcontroller.model.spell;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.PoisonEffect;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that poisons enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Poison extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException {
    character.changePoisonousStatus(new PoisonEffect(weapon.getMagicDamage()));
    mage.setCurrentMp(mage.getCurrentMp() - 40);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException {
    checkMana(mage.getCurrentMp());
    character.receiveSpell(this, mage, weapon);
  }

  @Override
  public void checkMana(int mageMana) throws InvalidManaValueException {
    if (mageMana < 40) {
      throw new InvalidManaValueException("There's not enough mana to cast a poison spell.");
    }
  }

  @Override
  public String description() {
    return """
        Poisons the oponent.
        Type: White
        Cost: 40 MP""";
  }

  @Override
  public String toString() {
    return "Poison";
  }
}
