package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public abstract class AbstractSpell implements Spell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidTargetCharacterException(this + " cannot be used on " + character);
  }

  @Override
  public void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidTargetCharacterException(this + " cannot be used on " + character);
  }

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
