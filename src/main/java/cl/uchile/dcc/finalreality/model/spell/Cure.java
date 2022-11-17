package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import static java.lang.Math.min;

public class Cure implements Spell {

  @Override
  public void induceEffect(GameCharacter character, MagicWeapon weapon) throws InvalidStatValueException {
    int newHp = (int) min(character.getMaxHp(), character.getCurrentHp() + (character.getMaxHp()*0.3));
    character.setCurrentHp(newHp);
  }
}
