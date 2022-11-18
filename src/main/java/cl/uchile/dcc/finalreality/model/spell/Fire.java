package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

import static java.lang.Math.max;

public class Fire extends AbstractSpell {

  @Override
  public void induceEffect(GameCharacter character, MagicWeapon weapon) throws InvalidStatValueException {
    int newHp = max(0,character.getCurrentHp() - weapon.getMagicDamage());
    character.setCurrentHp(newHp);
  }

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    character.receiveFire(this, mage, weapon);
  }
}
