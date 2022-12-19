package cl.uchile.dcc.finalreality.model_controller.model.character.player;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.spell.Spell;
import cl.uchile.dcc.finalreality.model_controller.model.weapon.MagicWeapon;

/**
 * A {@link PlayerCharacter} that can use magic.
 */
public interface Mage extends PlayerCharacter {

  void conjureSpell(Spell spell, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException;

  int getCurrentMp();

  void setCurrentMp(int currentMp) throws InvalidStatValueException;
}
