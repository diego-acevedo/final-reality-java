/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * * A {@link PlayerCharacter} that can equip {@code Staff}s and {@code Knife}s.
 * They can also cast white magic.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 * @version 2.0
 */
public class BlackMage extends AbstractMage implements Mage {

  /**
   * Creates a new Black Mage.
   *
   * @param name
   *     the character's name
   * @param maxHp
   *     the character's max hp
   * @param defense
   *     the character's defense
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public BlackMage(final @NotNull String name, final int maxHp, final int defense,
      final int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, maxMp, turnsQueue);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final BlackMage that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && this.getName().equals(that.getName())
        && this.getMaxHp() == that.getMaxHp()
        && this.getDefense() == that.getDefense()
        && this.getMaxMp() == that.getMaxMp();
  }

  @Override
  public String toString() {
    return "BlackMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(this.getMaxMp(), this.getMaxHp(), this.getDefense(), this.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(BlackMage.class, this.getName(), this.getMaxHp(),
        this.getDefense(), this.getMaxMp());
  }

  @Override
  public void equip(Weapon weapon) {
    try {
      this.setEquippedWeapon(weapon.equipToBlackMage(this));
    } catch (InvalidStatValueException e) {
      System.out.println("Invalid weapon given.");
    }
  }

  @Override
  public void conjureSpell(Spell spell, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException, InvalidTargetCharacterException {
    spell.conjureByBlackMage(this, character, weapon);
  }
}
