package cl.uchile.dcc.finalreality.controller.factories.enemy;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import java.util.concurrent.BlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

/**
 * A class that represents an {@link AbstractEnemyFactory} that creates an {@link Enemy}.
 */
public class EnemyFactory implements AbstractEnemyFactory {

  private int enemiesCreated = 1;
  private String name;
  private int weight;
  private int maxHp;
  private int defense;
  private int attack;

  protected void setName() {
    this.name = "Enemy %d".formatted(this.enemiesCreated);
    this.enemiesCreated++;
  }

  protected void setWeight() {
    this.weight = RANDOM_GENERATOR.nextInt(10, 100);
  }

  protected void setmaxHp() {
    this.maxHp = RANDOM_GENERATOR.nextInt(1000, 2000);
  }

  protected void setDefense() {
    this.defense = RANDOM_GENERATOR.nextInt(0, 40);
  }

  protected void setAttack() {
    this.attack = RANDOM_GENERATOR.nextInt(50, 100);
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
