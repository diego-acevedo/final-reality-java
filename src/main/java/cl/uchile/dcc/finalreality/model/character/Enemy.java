package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.exceptions.InvalidTargetCharacterException;
import cl.uchile.dcc.finalreality.exceptions.Require;
import cl.uchile.dcc.finalreality.model.character.player.Mage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.effect.FireEffect;
import cl.uchile.dcc.finalreality.model.effect.NoEffect;
import cl.uchile.dcc.finalreality.model.effect.ParalyzableEffect;
import cl.uchile.dcc.finalreality.model.effect.PoisonousEffect;
import cl.uchile.dcc.finalreality.model.spell.Spell;
import cl.uchile.dcc.finalreality.model.weapon.MagicWeapon;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author <a href="https://www.github.com/r8vnhill">R8V</a>
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class Enemy extends AbstractCharacter {

  private final int weight;
  private final int attack;
  private FireEffect burntStatus;
  private ParalyzableEffect paralysisStatus;
  private PoisonousEffect poisonStatus;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   *
   * @param name
   *     this enemy's name.
   * @param weight
   *     this enemy's weight.
   * @param maxHp
   *     this enemy's max health points.
   * @param defense
   *     this enemy's defense
   * @param turnsQueue
   *     the combat queue that manages the turns.
   * @throws InvalidStatValueException weight must be greater than 1.
   */
  public Enemy(@NotNull final String name, final int weight, int maxHp, int defense,
      int attack, @NotNull final BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    super(name, maxHp, defense, turnsQueue);
    Require.statValueAtLeast(1, weight, "Weight");
    this.weight = weight;
    this.attack = attack;
    NoEffect fine = new NoEffect();
    fine.setEnemy(this);
    this.burntStatus = fine;
    this.paralysisStatus = fine;
    this.poisonStatus = fine;
  }

  /**
   * Starts a cooldown for a {@link GameCharacter} to be able to attack again.
   * Cooldown depends on the enemy's weight.
   */
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(
            /* command = */ this::addToQueue,
            /* delay = */ this.getWeight() / 10,
            /* unit = */ TimeUnit.SECONDS);
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the attack of this enemy.
   */
  public int getAttack() {
    return attack;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Enemy enemy)) {
      return false;
    }
    return hashCode() == enemy.hashCode()
        && this.getName().equals(enemy.getName())
        && this.getWeight() == enemy.getWeight()
        && this.getMaxHp() == enemy.getMaxHp()
        && this.getDefense() == enemy.getDefense()
        && this.getAttack() == enemy.getAttack();
  }

  @Override
  public int hashCode() {
    return Objects.hash(Enemy.class, this.getName(), this.getWeight(),
        this.getMaxHp(), this.getDefense(), this.getAttack());
  }

  @Override
  public String toString() {
    return "Enemy{maxHp=%d, defense=%d, name='%s', weight=%d, attack=%d}"
            .formatted(this.getMaxHp(), this.getDefense(), this.getName(),
                this.getWeight(), this.getAttack());
  }

  public void receiveSpell(Spell spell, Mage mage, MagicWeapon weapon)
      throws InvalidStatValueException, InvalidTargetCharacterException {
    spell.induceEffectOnEnemy(this, weapon);
  }

  @Override
  public void attack(GameCharacter character) throws InvalidTargetCharacterException {
    character.getAttackFromEnemy(this);
  }

  @Override
  public void getAttackFromPlayerCharacter(PlayerCharacter character)
      throws InvalidStatValueException {
    int newHp = Math.max(0, this.getCurrentHp() - character.getEquippedWeapon().getDamage());
    this.setCurrentHp(newHp);
  }

  public void changeParalysisStatus(ParalyzableEffect s) {
    this.paralysisStatus = s;
    s.setEnemy(this);
  }

  public void changeBurntStatus(FireEffect s) {
    this.burntStatus = s;
    s.setEnemy(this);
  }

  public void changePoisonousStatus(PoisonousEffect s) {
    this.poisonStatus = s;
    s.setEnemy(this);
  }
}
