/*
 * "Final Reality" (c) by R8V and ~Your name~
 * "Final Reality" is licensed under a
 * Creative Commons Attribution 4.0 International License.
 * You should have received a copy of the license along with this
 * work. If not, see <http://creativecommons.org/licenses/by/4.0/>.
 */

package cl.uchile.dcc.finalreality.modelcontroller.model.character.player;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidEquipableWeaponException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidMageException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidManaValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.spell.Spell;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.MagicWeapon;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link PlayerCharacter} that can equip {@code Staff}s. They can also
 * cast white magic.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 * @version 2.0
 */
public class WhiteMage extends AbstractMage implements Mage {

  /**
   * Creates a new character.
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
  public WhiteMage(final @NotNull String name, final int maxHp, final int defense,
                      final int maxMp, final @NotNull BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, maxMp, turnsQueue);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final WhiteMage that)) {
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
    return "WhiteMage{maxMp=%d, maxHp=%d, defense=%d, name='%s'}"
        .formatted(this.getMaxMp(), this.getMaxHp(), this.getDefense(), this.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(WhiteMage.class, this.getName(), this.getMaxHp(),
        this.getDefense(), this.getMaxMp());
  }

  @Override
  public void equip(Weapon weapon) throws InvalidEquipableWeaponException {
    this.setEquippedWeapon(weapon.equipToWhiteMage(this));
  }

  @Override
  public int getSpriteRow() {
    return 4;
  }

  @Override
  public void conjureSpell(Spell spell, GameCharacter character, MagicWeapon weapon)
      throws InvalidMageException, InvalidStatValueException,
      InvalidTargetCharacterException, InvalidManaValueException {
    spell.conjureByWhiteMage(this, character, weapon);
  }

  @Override
  public String getInfo() {
    return "HP: " + getCurrentHp() + "/" + getMaxHp()
        + "\nMP: " + getCurrentMp() + "/" + getMaxMp();
  }

  @Override
  public String getStats() {
    return """
        Name: %s
        HP: %d
        MP: %d
        Defense: %d""".formatted(this.getName(), this.getCurrentHp(),
        this.getCurrentMp(), this.getDefense());
  }
}