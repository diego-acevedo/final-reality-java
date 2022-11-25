package cl.uchile.dcc.finalreality.controller.factories.enemy;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class EnemyFactory implements AbstractEnemyFactory {

  private int n = 1;
  private String name;
  private int weight;
  private int maxHp;
  private int defense;
  private int attack;
  protected Random rnd = new Random();

  protected void setName() {
    this.name = "Enemy %d".formatted(this.n);
    this.n++;
  }

  protected void setWeight() {
    this.weight = this.rnd.nextInt(10, 100);
  }

  protected void setmaxHp() {
    this.maxHp = this.rnd.nextInt(1000, 2000);
  }

  protected void setDefense() {
    this.defense = this.rnd.nextInt(0, 40);
  }

  protected void setAttack() {
    this.attack = this.rnd.nextInt(50, 100);
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
