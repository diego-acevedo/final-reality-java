package cl.uchile.dcc.finalreality.model.weapon;

import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMagicWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.model.spell.Spell;

/**
 * A class that holds all the information of a weapon.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class AbstractWeapon implements Weapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage and its weight.
   *
   * @param name
   *     the weapon's name.
   * @param damage
   *     the weapon's damage.
   * @param weight
   *     the weapon's weight.
   *
   * @throws InvalidStatValueException the weapon's weight can't be less than 1.
   */
  protected AbstractWeapon(final String name, final int damage, final int weight)
      throws InvalidStatValueException {
    Require.statValueAtLeast(1, weight, "Weight");
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public int getDamage() {
    return damage;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public Weapon equipToKnight(Knight knight) throws InvalidStatValueException {
    return this;
  }

  @Override
  public Weapon equipToEngineer(Engineer engineer) throws InvalidStatValueException {
    return this;
  }

  @Override
  public Weapon equipToThief(Thief thief) throws InvalidStatValueException {
    return this;
  }

  @Override
  public Weapon equipToBlackMage(BlackMage blackmage) throws InvalidStatValueException {
    return this;
  }

  @Override
  public Weapon equipToWhiteMage(WhiteMage whitemage) throws InvalidStatValueException {
    return this;
  }

  @Override
  public abstract boolean equals(final Object o);

  @Override
  public abstract int hashCode();

  @Override
  public String toString() {
    return "{name='%s', damage=%d, weight=%d}"
        .formatted(name, damage, weight);
  }

  public void castSpell(GameCharacter character, Spell spell, Mage mage)
      throws InvalidMagicWeaponException, InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    throw new InvalidMagicWeaponException(this + " cannot cast spells.");
  }
}
