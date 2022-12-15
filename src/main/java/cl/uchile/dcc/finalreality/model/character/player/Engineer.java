/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.model.character.player;

import cl.uchile.dcc.finalreality.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;


/**
 * A {@link PlayerCharacter} that can equip {@code Axe}s and {@code Bow}s.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Engineer extends AbstractPlayerCharacter {


  /**
   * Creates a new engineer.
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
  public Engineer(final @NotNull String name, final int maxHp, final int defense,
      final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
  }

  @Override
  public String toString() {
    return "Engineer{maxHp=%d, defense=%d, name='%s'}"
        .formatted(this.getMaxHp(), this.getDefense(), this.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(Engineer.class, this.getName(), this.getMaxHp(), this.getDefense());
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof final Engineer that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && this.getName().equals(that.getName())
        && this.getMaxHp() == that.getMaxHp()
        && this.getDefense() == that.getDefense();
  }

  @Override
  public void equip(Weapon weapon) throws InvalidEquipableWeaponException {
    this.setEquippedWeapon(weapon.equipToEngineer(this));
  }

  @Override
  public int getSpriteRow() {
    return 8;
  }

  @Override
  public String getInfo() {
    return "HP: " + getCurrentHp() + "/" + getMaxHp();
  }

  @Override
  public String getStats() {
    return """
        Name: %s
        HP: %d
        Defense: %d""".formatted(this.getName(), this.getCurrentHp(), this.getDefense());
  }
}
