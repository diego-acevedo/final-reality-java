package cl.uchile.dcc.finalreality.modelcontroller.model.spell;

import static cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver.RANDOM_GENERATOR;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.modelcontroller.model.effect.BurntEffect;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that burns enemies.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Fire extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException {
    int newHp = Math.max(0, character.getCurrentHp() - weapon.getMagicDamage());
    character.setCurrentHp(newHp);
    if (RANDOM_GENERATOR.nextFloat() <= 0.2) {
      character.changeBurntStatus(new BurntEffect(weapon.getMagicDamage()));
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
      throw new InvalidManaValueException("There's not enough mana to cast a fire spell.");
    }
  }

  @Override
  public String description() {
    return """
        Reduces opponent's life with a 20% chance they'll burn.
        Type: Black
        Cost: 15 MP""";
  }

  @Override
  public String toString() {
    return "Fire";
  }
}
