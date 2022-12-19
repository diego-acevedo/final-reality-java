package cl.uchile.dcc.finalreality.model_controller.model.spell;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.MagicWeapon;

/**
 * A class that holds all the information of a spell.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class AbstractSpell implements Spell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidTargetCharacterException(this + " cannot be used on " + character);
  }

  @Override
  public void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon, Mage mage)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    throw new InvalidTargetCharacterException(this + " cannot be used on " + character);
  }

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }

  @Override
  public void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new InvalidMageException(mage + " cannot conjure " + this);
  }
}
