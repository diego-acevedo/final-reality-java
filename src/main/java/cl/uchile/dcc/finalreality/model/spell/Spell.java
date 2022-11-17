package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public interface Spell {

  void induceEffect(GameCharacter character, MagicWeapon weapon) throws InvalidStatValueException;
}
