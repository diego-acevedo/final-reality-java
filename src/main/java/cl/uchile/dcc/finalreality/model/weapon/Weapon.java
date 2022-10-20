package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.model.character.player.*;

/**
 * This represents a weapon equipable by a
 * {@link cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter PlayerCharacter}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */

public interface Weapon {

  /**
   * Returns this weapon's name.
   */
  String getName();

  /**
   * Returns this weapon's damage.
   */
  int getDamage();

  /**
   * Returns this weapon's weight.
   */
  int getWeight();

  Weapon equipToKnight(Knight knight);

  Weapon equipToEngineer(Engineer engineer);

  Weapon equipToThief(Thief thief);

  Weapon equipToBlackMage(BlackMage blackmage);

  Weapon equipToWhiteMage(WhiteMage whitemage);

}
