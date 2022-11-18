package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import static java.lang.Math.min;

public class Cure extends AbstractSpell {

  @Override
  public void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException {
    int newHp = (int) min(character.getMaxHp(), character.getCurrentHp() + (character.getMaxHp()*0.3));
    character.setCurrentHp(newHp);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    character.receiveSpell(this, mage, weapon);
  }
}
