package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public interface Spell {

  void induceEffect(GameCharacter character, MagicWeapon weapon) throws InvalidStatValueException;

  void conjureByBlackMage(Mage mage, GameCharacter character) throws InvalidMageException;

  void conjureByWhiteMage(Mage mage, GameCharacter character) throws InvalidMageException;
}
