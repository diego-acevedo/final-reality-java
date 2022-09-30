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

    // Knights
    PlayerCharacter knight1 = new Knight("1", 100, 50, queue);
    PlayerCharacter knight2 = new Knight("1", 100, 50, queue);
    PlayerCharacter knight3 = new Knight("2", 100, 50, queue);
    PlayerCharacter knight4 = new Knight("1", 150, 50, queue);
    PlayerCharacter knight5 = new Knight("1", 100, 100, queue);

    // Thiefs
    PlayerCharacter thief1 = new Thief("1", 100, 50, queue);
    PlayerCharacter thief2 = new Thief("1", 100, 50, queue);
    PlayerCharacter thief3 = new Thief("2", 100, 50, queue);
    PlayerCharacter thief4 = new Thief("1", 150, 50, queue);
    PlayerCharacter thief5 = new Thief("1", 100, 100, queue);

    // Engineers
    PlayerCharacter engineer1 = new Engineer("1", 100, 50, queue);
    PlayerCharacter engineer2 = new Engineer("1", 100, 50, queue);
    PlayerCharacter engineer3 = new Engineer("2", 100, 50, queue);
    PlayerCharacter engineer4 = new Engineer("1", 150, 50, queue);
    PlayerCharacter engineer5 = new Engineer("1", 100, 100, queue);

    // Black Mages
    PlayerCharacter blackmage1 = new BlackMage("1", 100, 50, 100, queue);
    PlayerCharacter blackmage2 = new BlackMage("1", 100, 50, 100, queue);
    PlayerCharacter blackmage3 = new BlackMage("2", 100, 50, 100, queue);
    PlayerCharacter blackmage4 = new BlackMage("1", 150, 50, 100, queue);
    PlayerCharacter blackmage5 = new BlackMage("1", 100, 100, 100, queue);
    PlayerCharacter blackmage6 = new BlackMage("1", 100, 50, 200, queue);

    // White Mages
    PlayerCharacter whitemage1 = new WhiteMage("1", 100, 50, 100, queue);
    PlayerCharacter whitemage2 = new WhiteMage("1", 100, 50, 100, queue);
    PlayerCharacter whitemage3 = new WhiteMage("2", 100, 50, 100, queue);
    PlayerCharacter whitemage4 = new WhiteMage("1", 150, 50, 100, queue);
    PlayerCharacter whitemage5 = new WhiteMage("1", 100, 100, 100, queue);
    PlayerCharacter whitemage6 = new WhiteMage("1", 100, 50, 200, queue);

    // Enemies
    Enemy enemy1 = new Enemy("1", 100, 100, 50, queue);
    Enemy enemy2 = new Enemy("1", 100, 100, 50, queue);
    Enemy enemy3 = new Enemy("2", 100, 100, 50, queue);
    Enemy enemy4 = new Enemy("1", 50, 100, 50, queue);
    Enemy enemy5 = new Enemy("1", 100, 50, 50, queue);
    Enemy enemy6 = new Enemy("1", 100, 100, 100, queue);

    // Swords
    Weapon sword1 = new Sword("1", 50, 20);
    Weapon sword2 = new Sword("1", 50, 20);
    Weapon sword3 = new Sword("2", 50, 20);
    Weapon sword4 = new Sword("1", 70, 20);
    Weapon sword5 = new Sword("1", 50, 30);

    // Axes
    Weapon axe1 = new Axe("1", 50, 20);
    Weapon axe2 = new Axe("1", 50, 20);
    Weapon axe3 = new Axe("2", 50, 20);
    Weapon axe4 = new Axe("1", 70, 20);
    Weapon axe5 = new Axe("1", 50, 30);

    // Bows
    Weapon bow1 = new Bow("1", 50, 20);
    Weapon bow2 = new Bow("1", 50, 20);
    Weapon bow3 = new Bow("2", 50, 20);
    Weapon bow4 = new Bow("1", 70, 20);
    Weapon bow5 = new Bow("1", 50, 30);

    // Knifes
    Weapon knife1 = new Knife("1", 50, 20);
    Weapon knife2 = new Knife("1", 50, 20);
    Weapon knife3 = new Knife("2", 50, 20);
    Weapon knife4 = new Knife("1", 70, 20);
    Weapon knife5 = new Knife("1", 50, 30);

    // Staffs
    Weapon staff1 = new Staff("1", 50, 20, 50);
    Weapon staff2 = new Staff("1", 50, 20, 50);
    Weapon staff3 = new Staff("2", 50, 20, 50);
    Weapon staff4 = new Staff("1", 70, 20, 50);
    Weapon staff5 = new Staff("1", 50, 30, 50);
    Weapon staff6 = new Staff("1", 50, 20, 100);

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

    if (!(enemy1.equals(enemy1)) ||
        !(enemy1.equals(enemy2)) ||
        enemy1.equals(enemy3) ||
        enemy1.equals(enemy4) ||
        enemy1.equals(enemy5) ||
        enemy1.equals(enemy6)) {
      System.out.println("Method equals() fails for Enemy class\n");
    } else System.out.println("Method equals() worked correctly for Enemy class\n");

    if (!(sword1.equals(sword1)) ||
        !(sword1.equals(sword2)) ||
        sword1.equals(sword3) ||
        sword1.equals(sword4) ||
        sword1.equals(sword5)) {
      System.out.println("Method equals() fails for Sword class\n");
    } else System.out.println("Method equals() worked correctly for Sword class\n");

    if (!(axe1.equals(axe1)) ||
        !(axe1.equals(axe2)) ||
        axe1.equals(axe3) ||
        axe1.equals(axe4) ||
        axe1.equals(axe5)) {
      System.out.println("Method equals() fails for Axe class\n");
    } else System.out.println("Method equals() worked correctly for Axe class\n");

    if (!(bow1.equals(bow1)) ||
        !(bow1.equals(bow2)) ||
        bow1.equals(bow3) ||
        bow1.equals(bow4) ||
        bow1.equals(bow5)) {
      System.out.println("Method equals() fails for Bow class\n");
    } else System.out.println("Method equals() worked correctly for Bow class\n");

    if (!(knife1.equals(knife1)) ||
        !(knife1.equals(knife2)) ||
        knife1.equals(knife3) ||
        knife1.equals(knife4) ||
        knife1.equals(knife5)) {
      System.out.println("Method equals() fails for Knife class\n");
    } else System.out.println("Method equals() worked correctly for Knife class\n");

    if (!(staff1.equals(staff1)) ||
        !(staff1.equals(staff2)) ||
        staff1.equals(staff3) ||
        staff1.equals(staff4) ||
        staff1.equals(staff5) ||
        staff1.equals(staff6)) {
      System.out.println("Method equals() fails for Staff class\n");
    } else System.out.println("Method equals() worked correctly for Satff class\n");

    if (knight1.equals(engineer1) ||
        engineer1.equals(thief1) ||
        thief1.equals(whitemage1) ||
        whitemage1.equals(blackmage1) ||
        sword1.equals(knife1) ||
        knife1.equals(axe1) ||
        axe1.equals(staff1) ||
        staff1.equals(bow1) ||
        knight1.equals(knife1) ||
        engineer1.equals(sword1) ||
        thief1.equals(axe1) ||
        whitemage1.equals(staff1)) {
      System.out.println("Method equals() fails for objects of different classes\n");
    } else System.out.println("Method equals() worked correctly for objects of different classes\n");

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
