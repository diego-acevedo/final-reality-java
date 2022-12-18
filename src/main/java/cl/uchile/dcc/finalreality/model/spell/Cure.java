package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

/**
 * A class thah holds the information of a {@link Spell} that cures.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Cure extends AbstractSpell {

  @Override
  public void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException {
    int newHp = (int) Math.min(character.getMaxHp(),
        character.getCurrentHp() + (character.getMaxHp() * 0.3));
    character.setCurrentHp(newHp);
    mage.setCurrentMp(mage.getCurrentMp() - 15);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException, InvalidManaValueException {
    checkMana(mage.getCurrentMp());
    character.receiveSpell(this, mage, weapon);
  }

  @Override
  public void checkMana(int mageMana) throws InvalidManaValueException {
    if (mageMana < 15) {
      throw new InvalidManaValueException("There's not enough mana to cast a healing spell.");
    }
  }

  @Override
  public String description() {
    return "Heals an ally 30% of their max HP.\nType: White Magic\nCost: 15 MP";
  }

  @Override
  public String toString() {
    return "Cure";
  }
}
