package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import cl.uchile.dcc.finalreality.model.weapon.Axe;
import cl.uchile.dcc.finalreality.model.weapon.Bow;
import cl.uchile.dcc.finalreality.model.weapon.Knife;
import cl.uchile.dcc.finalreality.model.weapon.Staff;
import cl.uchile.dcc.finalreality.model.weapon.Sword;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Pseudo-test that shows the project works.
 */
public class MainTest {

  /**
   * Main function to test the project implementation.
   */
  public static void main(String[] args) throws InvalidStatValueException, InterruptedException {
    BlockingQueue<GameCharacter> queue = new LinkedBlockingQueue<>();
    Random rnd = new Random();

    PlayerCharacter knight1 = new Knight("Knight", 100, 50, queue);
    PlayerCharacter knight2 = new Knight("Knight", 100, 50, queue);
    PlayerCharacter knight3 = new Knight("AlternativeKnight", 100, 50, queue);
    PlayerCharacter knight4 = new Knight("Knight", 150, 50, queue);
    PlayerCharacter knight5 = new Knight("Knight", 100, 100, queue);

    PlayerCharacter thief1 = new Thief("Thief", 100, 50, queue);
    PlayerCharacter thief2 = new Thief("Thief", 100, 50, queue);
    PlayerCharacter thief3 = new Thief("AlternativeThief", 100, 50, queue);
    PlayerCharacter thief4 = new Thief("Thief", 150, 50, queue);
    PlayerCharacter thief5 = new Thief("Thief", 100, 100, queue);

    PlayerCharacter engineer1 = new Engineer("Engineer", 100, 50, queue);
    PlayerCharacter engineer2 = new Engineer("Engineer", 100, 50, queue);
    PlayerCharacter engineer3 = new Engineer("AlternativeEngineer", 100, 50, queue);
    PlayerCharacter engineer4 = new Engineer("Engineer", 150, 50, queue);
    PlayerCharacter engineer5 = new Engineer("Engineer", 100, 100, queue);

    PlayerCharacter blackmage1 = new BlackMage("BlackMage", 100, 50, 100, queue);
    PlayerCharacter blackmage2 = new BlackMage("BlackMage", 100, 50, 100, queue);
    PlayerCharacter blackmage3 = new BlackMage("AlternativeBlackMage", 100, 50, 100, queue);
    PlayerCharacter blackmage4 = new BlackMage("BlackMage", 150, 50, 100, queue);
    PlayerCharacter blackmage5 = new BlackMage("BlackMage", 100, 100, 100, queue);
    PlayerCharacter blackmage6 = new BlackMage("BlackMage", 100, 50, 200, queue);

    PlayerCharacter whitemage1 = new WhiteMage("WhiteMage", 100, 50, 100, queue);
    PlayerCharacter whitemage2 = new WhiteMage("WhiteMage", 100, 50, 100, queue);
    PlayerCharacter whitemage3 = new WhiteMage("AlternativeWhiteMage", 100, 50, 100, queue);
    PlayerCharacter whitemage4 = new WhiteMage("WhiteMage", 150, 50, 100, queue);
    PlayerCharacter whitemage5 = new WhiteMage("WhiteMage", 100, 100, 100, queue);
    PlayerCharacter whitemage6 = new WhiteMage("WhiteMage", 100, 50, 200, queue);

    // Equals

    System.out.println("-----Testing equals() methods and getters-----\n");

    if (!(knight1.equals(knight1)) ||
        !(knight1.equals(knight2)) ||
           knight1.equals(knight3) ||
           knight1.equals(knight4) ||
           knight1.equals(knight5)) {
      System.out.println("Method equals() fails for Knight class\n");
    } else System.out.println("Method equals() worked correctly for Knight class\n");

    if (!(engineer1.equals(engineer1)) ||
        !(engineer1.equals(engineer2)) ||
        engineer1.equals(engineer3) ||
        engineer1.equals(engineer4) ||
        engineer1.equals(engineer5)) {
      System.out.println("Method equals() fails for Engineer class\n");
    } else System.out.println("Method equals() worked correctly for Engineer class\n");

    if (!(thief1.equals(thief1)) ||
        !(thief1.equals(thief2)) ||
        thief1.equals(thief3) ||
        thief1.equals(thief4) ||
        thief1.equals(thief5)) {
      System.out.println("Method equals() fails for Thief class\n");
    } else System.out.println("Method equals() worked correctly for Thief class\n");

    if (!(blackmage1.equals(blackmage1)) ||
        !(blackmage1.equals(blackmage2)) ||
        blackmage1.equals(blackmage3) ||
        blackmage1.equals(blackmage4) ||
        blackmage1.equals(blackmage5) ||
        blackmage1.equals(blackmage6)) {
      System.out.println("Method equals() fails for BlackMage class\n");
    } else System.out.println("Method equals() worked correctly for BlackMage class\n");

    if (!(whitemage1.equals(whitemage1)) ||
        !(whitemage1.equals(whitemage2)) ||
        whitemage1.equals(whitemage3) ||
        whitemage1.equals(whitemage4) ||
        whitemage1.equals(whitemage5) ||
        whitemage1.equals(whitemage6)) {
      System.out.println("Method equals() fails for WhiteMage class\n");
    } else System.out.println("Method equals() worked correctly for WhiteMage class\n");

    System.out.println("-----Testing toString() methods and equip() method (incomplete implementation)-----\n");

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
     * This part illustrates the turns order and wait time by
     * printing on the console when a character starts to wait for theit next round
     */

    System.out.println(
        "\n---Turns example---\nCharacters will attackand wait for their next turn\n");

    long startTime = System.currentTimeMillis();
    while (System.currentTimeMillis() - startTime < 10000) {
      if (!(queue.isEmpty())) {
        GameCharacter character = queue.poll();
        System.out.printf("%s attacked\n", character);
        character.waitTurn();
      }
    }
    Thread.sleep(1000);
  }
}
