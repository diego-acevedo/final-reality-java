/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.modelcontroller.model.character.player;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PlayerCharacter} that can equip {@code Sword}s,{@code Knife}s and
 * {@code Bow}s.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 *
 * @version 2.0
 */
public class Thief extends AbstractPlayerCharacter {

  /**
   * Creates a new Thief.
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
  public Thief(final @NotNull String name, final int maxHp, final int defense,
      final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Thief.class, this.getName(), this.getMaxHp(), this.getDefense());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Thief that)) {
      return false;
    }
    return hashCode() == that.hashCode()
        && this.getName().equals(that.getName())
        && this.getMaxHp() == that.getMaxHp()
        && this.getDefense() == that.getDefense();
  }

  @Override
  public String toString() {
    return "Thief{maxHp=%d, defense=%d, name='%s'}"
        .formatted(this.getMaxHp(), this.getDefense(), this.getName());
  }

  @Override
  public void equip(Weapon weapon) throws InvalidEquipableWeaponException {
    this.setEquippedWeapon(weapon.equipToThief(this));

  }

  @Override
  public int getSpriteRow() {
    return 1;
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
