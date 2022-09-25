package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;
import cl.uchile.dcc.finalreality.model.weapon.*;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainTest {

  public static void main(String[] args) throws InvalidStatValueException, InterruptedException {
    BlockingQueue<GameCharacter> queue = new LinkedBlockingQueue<>();
    Random rnd = new Random();
    // Knights
    System.out.println("---Knights---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter knight = new Knight("Knight %d".formatted(i), rnd.nextInt(100)+1, 0, queue);
      Weapon weapon = new Sword("Sword %d".formatted(i), 0, rnd.nextInt(100)+1);
      knight.equip(weapon);
      System.out.printf("%s using %s\n", knight, weapon);
      if (i==0) {
        queue.put(knight);
      }
    }
    // Engineers
    System.out.println("---Engineers---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter engineer = new Engineer("Engineer %d".formatted(i), rnd.nextInt(100)+1,
          0, queue);
      Weapon weapon = new Axe("Sword %d".formatted(i), 0, rnd.nextInt(100)+1);
      engineer.equip(weapon);
      System.out.printf("%s using %s\n", engineer, weapon);
      if (i==0) {
        queue.put(engineer);
      }
    }
    // Thiefs
    System.out.println("---Thiefs---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter thief = new Thief("Thief %d".formatted(i), rnd.nextInt(100)+1, 0, queue);
      Weapon weapon = new Bow("Sword %d".formatted(i), 0, rnd.nextInt(100)+1);
      thief.equip(weapon);
      System.out.printf("%s using %s\n", thief, weapon);
      if (i==0) {
        queue.put(thief);
      }
    }
    // Black Mages
    System.out.println("---Black Mages---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter blackMage = new BlackMage("Black Mage %d".formatted(i), rnd.nextInt(100)+1,
          0, rnd.nextInt(100), queue);
      Weapon weapon = new Staff("Sword %d".formatted(i), 0, rnd.nextInt(100)+1, 0);
      blackMage.equip(weapon);
      System.out.printf("%s using %s\n", blackMage, weapon);
      if (i==0) {
        queue.put(blackMage);
      }
    }
    // White Mages
    System.out.println("---White Mages---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter whiteMage = new WhiteMage("White Mage %d".formatted(i), rnd.nextInt(100)+1,
          0, rnd.nextInt(100), queue);
      Weapon weapon = new Knife("Sword %d".formatted(i), 0, rnd.nextInt(100)+1);
      whiteMage.equip(weapon);
      System.out.printf("%s using %s\n", whiteMage, weapon);
      if (i==0) {
        queue.put(whiteMage);
      }
    }
    // Enemies
    System.out.println("---Enemies---");
    for (int i = 0; i < 5; i++) {
      Enemy enemy = new Enemy("Enemy %d".formatted(i), rnd.nextInt(100)+1,
          rnd.nextInt(100)+1, 0, queue);
      System.out.println(enemy);
      queue.put(enemy);
    }
    long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis()-startTime < 10000) {
      if (!(queue.isEmpty())) {
        GameCharacter character = queue.poll();
        System.out.printf("%s attacked\n",character);
        character.addToQueue();
      }
    }
  }
}
