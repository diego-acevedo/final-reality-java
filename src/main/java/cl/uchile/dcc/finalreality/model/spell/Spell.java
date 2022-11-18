package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

public interface Spell {

  void induceEffectOnEnemy(Enemy character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  void induceEffectOnPlayerCharacter(PlayerCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException;

  void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException;

  void conjureByWhiteMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException;
}
