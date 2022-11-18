package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public interface Mage extends PlayerCharacter {

  void conjureSpell(Spell spell, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException;
}
