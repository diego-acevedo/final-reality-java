package cl.uchile.dcc.finalreality.model.spell;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.effect.BurntEffect;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;

import java.util.Random;

import static java.lang.Math.max;

public class Fire extends AbstractSpell {

  @Override
  public void induceEffectOnEnemy(Enemy character, MagicWeapon weapon) throws InvalidStatValueException {
    int newHp = max(0,character.getCurrentHp() - weapon.getMagicDamage());
    character.setCurrentHp(newHp);
    Random random = new Random();
    if (random.nextFloat() <= 0.2) {
      character.burntStatus = new BurntEffect(weapon.getMagicDamage());
    }
  }

  @Override
  public void conjureByBlackMage(Mage mage, GameCharacter character, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    character.receiveSpell(this, mage, weapon);
  }
}
