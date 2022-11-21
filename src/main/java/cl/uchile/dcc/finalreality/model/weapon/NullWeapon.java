package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.*;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.spell.Spell;

public class NullWeapon implements Weapon {
  @Override
  public String getName() {
    return null;
  }

  @Override
  public int getDamage() {
    return 0;
  }

  @Override
  public int getWeight() {
    return 0;
  }

  @Override
  public Weapon equipToKnight(Knight knight) throws InvalidStatValueException {
    return null;
  }

  @Override
  public Weapon equipToEngineer(Engineer engineer) throws InvalidStatValueException {
    return null;
  }

  @Override
  public Weapon equipToThief(Thief thief) throws InvalidStatValueException {
    return null;
  }

  @Override
  public Weapon equipToBlackMage(BlackMage blackmage) throws InvalidStatValueException {
    return null;
  }

  @Override
  public Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidStatValueException {
    return null;
  }

  @Override
  public void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new InvalidMagicWeaponException(this + " cannot cast spells.");
  }
}
