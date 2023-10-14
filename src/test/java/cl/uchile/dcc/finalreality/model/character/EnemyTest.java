package cl.uchile.dcc.finalreality.model.character;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.Enemy;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
  Enemy enemy1;
  Enemy enemy2;
  Enemy enemy3;
  BlockingQueue<GameCharacter> queue;

  @BeforeEach
  public void setUp() throws InvalidStatValueException {
    Random rnd = new Random();
    queue = new LinkedBlockingQueue<>();

    enemy1 = new Enemy("Enemy1", 10, rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    enemy2 = new Enemy("Enemy2", 20, rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
    enemy3 = new Enemy("Enemy3", 30, rnd.nextInt(10,100),
        rnd.nextInt(10,100), rnd.nextInt(10,100), queue);
  }

  @Test
  void testWaitTurn() throws InterruptedException {
    enemy1.waitTurn();
    enemy2.waitTurn();
    enemy3.waitTurn();
    Thread.sleep(10000);
    assertEquals(queue.poll(),enemy1);
    assertEquals(queue.poll(),enemy2);
    assertEquals(queue.poll(),enemy3);
  }

  @Test
  void testHp() throws InvalidStatValueException {
    assertEquals(enemy1.getMaxHp(), enemy1.getCurrentHp());
    enemy1.setCurrentHp(5);
    assertEquals(5, enemy1.getCurrentHp());
    try {
      enemy1.setCurrentHp(-10);
      fail("Current HP cannot be negative. An exception should've been thrown.");
    } catch (InvalidStatValueException e) {
      assertTrue(true);
    }
  }

  @Test
  void testEquals() throws InvalidStatValueException {
    assertEquals(enemy1, enemy1);
    assertEquals(enemy2, enemy2);
    assertEquals(enemy3, enemy3);
    assertEquals(enemy1, new Enemy(enemy1.getName(), enemy1.getWeight(), enemy1.getMaxHp(),
        enemy1.getDefense(), enemy1.getAttack(), queue));
    assertEquals(enemy2, new Enemy(enemy2.getName(), enemy2.getWeight(), enemy2.getMaxHp(),
        enemy2.getDefense(), enemy2.getAttack(), queue));
    assertEquals(enemy3, new Enemy(enemy3.getName(), enemy3.getWeight(), enemy3.getMaxHp(),
        enemy3.getDefense(), enemy3.getAttack(), queue));
    assertNotEquals(enemy1, enemy2);
    assertNotEquals(enemy3, enemy2);
    assertNotEquals(enemy1, enemy3);
  }

  @Test
  void testToString() {
     assertEquals(enemy1.toString(), "Enemy{maxHp=%d, defense=%d, name='%s', weight=%d, attack=%d}"
         .formatted(enemy1.getMaxHp(), enemy1.getDefense(), enemy1.getName(),
             enemy1.getWeight(), enemy1.getAttack()));
    assertEquals(enemy2.toString(), "Enemy{maxHp=%d, defense=%d, name='%s', weight=%d, attack=%d}"
        .formatted(enemy2.getMaxHp(), enemy2.getDefense(), enemy2.getName(),
            enemy2.getWeight(), enemy2.getAttack()));
    assertEquals(enemy3.toString(), "Enemy{maxHp=%d, defense=%d, name='%s', weight=%d, attack=%d}"
        .formatted(enemy3.getMaxHp(), enemy3.getDefense(), enemy3.getName(),
            enemy3.getWeight(), enemy3.getAttack()));
    assertNotEquals(enemy1.toString(),enemy2.toString());
    assertNotEquals(enemy2.toString(),enemy3.toString());
    assertNotEquals(enemy1.toString(),enemy3.toString());
  }
}