package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;

public abstract class AbstractSpell implements Spell {

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character) throws InvalidMageException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character) throws InvalidMageException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }
}
