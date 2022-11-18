package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public abstract class AbstractSpell implements Spell {

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }
}
