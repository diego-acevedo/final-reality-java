package cl.uchile.dcc.finalreality.model_controller.controller.factories.enemy;

import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a {@link AbstractEnemyFactory factory} that
 * creates an {@link Enemy enemy}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class EnemyFactory implements AbstractEnemyFactory {

  private int enemiesCreated = 1;
  private String name;
  private int weight;
  private int maxHp;
  private int defense;
  private int attack;

  /**
   * Sets name to a random value.
   */
  protected void setName() {
    this.name = "Enemy %d".formatted(this.enemiesCreated);
    this.enemiesCreated++;
  }

  /**
   * Sets weight to a random value.
   */
  protected void setWeight() {
    this.weight = GameDriver.RANDOM_GENERATOR.nextInt(10, 100);
  }

  /**
   * Sets maxHp to a random value.
   */
  protected void setmaxHp() {
    this.maxHp = GameDriver.RANDOM_GENERATOR.nextInt(1000, 2000);
  }

  /**
   * Sets defense to a random value.
   */
  protected void setDefense() {
    this.defense = GameDriver.RANDOM_GENERATOR.nextInt(0, 40);
  }

  /**
   * Sets attack to a random value.
   */
  protected void setAttack() {
    this.attack = GameDriver.RANDOM_GENERATOR.nextInt(50, 100);
  }

  @Override
  public Enemy create(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    this.setName();
    this.setmaxHp();
    this.setDefense();
    this.setAttack();
    this.setWeight();
    return new Enemy(this.name, this.weight, this.maxHp, this.defense, this.attack, turnsQueue);
  }
}
