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
      PlayerCharacter knight = new Knight("Knight %d".formatted(i), rnd.nextInt(100) + 1, 0, queue);
      Weapon weapon = new Sword("Sword %d".formatted(i), 0, rnd.nextInt(50) + 50);
      knight.equip(weapon);
      System.out.printf("%s using %s\n", knight, weapon);
      if (i == 0) {
        queue.put(knight);
      }
    }
    // Engineers
    System.out.println("\n---Engineers---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter engineer = new Engineer("Engineer %d".formatted(i), rnd.nextInt(100) + 1,
          0, queue);
      Weapon weapon = new Axe("Sword %d".formatted(i), 0, rnd.nextInt(50) + 50);
      engineer.equip(weapon);
      System.out.printf("%s using %s\n", engineer, weapon);
      if (i == 0) {
        queue.put(engineer);
      }
    }
    // Thiefs
    System.out.println("\n---Thiefs---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter thief = new Thief("Thief %d".formatted(i), rnd.nextInt(100) + 1, 0, queue);
      Weapon weapon = new Bow("Sword %d".formatted(i), 0, rnd.nextInt(50) + 50);
      thief.equip(weapon);
      System.out.printf("%s using %s\n", thief, weapon);
      if (i == 0) {
        queue.put(thief);
      }
    }
    // Black Mages
    System.out.println("\n---Black Mages---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter blackMage = new BlackMage("Black Mage %d".formatted(i), rnd.nextInt(100) + 1,
          0, rnd.nextInt(100), queue);
      Weapon weapon = new Staff("Sword %d".formatted(i), 0, rnd.nextInt(50) + 50, 0);
      blackMage.equip(weapon);
      System.out.printf("%s using %s\n", blackMage, weapon);
      if (i == 0) {
        queue.put(blackMage);
      }
    }
    // White Mages
    System.out.println("\n---White Mages---");
    for (int i = 0; i < 5; i++) {
      PlayerCharacter whiteMage = new WhiteMage("White Mage %d".formatted(i), rnd.nextInt(100) + 1,
          0, rnd.nextInt(100), queue);
      Weapon weapon = new Knife("Sword %d".formatted(i), 0, rnd.nextInt(50) + 50);
      whiteMage.equip(weapon);
      System.out.printf("%s using %s\n", whiteMage, weapon);
      if (i == 0) {
        queue.put(whiteMage);
      }
    }
    // Enemies
    System.out.println("\n---Enemies---");
    for (int i = 0; i < 5; i++) {
      Enemy enemy = new Enemy("Enemy %d".formatted(i), rnd.nextInt(50) + 50,
          rnd.nextInt(100) + 1, 0, queue);
      System.out.println(enemy);
      queue.put(enemy);
    }

    /*
     * Turns example:
     * This part illustrates the turns order and wait time by printing on the console when a character
     * starts to wait for theit next round
     */

    System.out.println("\n---Turns example---\nCharacters will attack and wait for their next turn\n");

    long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - startTime < 10000) {
      if (!(queue.isEmpty())) {
        GameCharacter character = queue.poll();
        System.out.printf("%s attacked\n",character);
        character.waitTurn();
      }
    }
    Thread.sleep(1000);
  }
}
