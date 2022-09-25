package cl.uchile.dcc.finalreality;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.*;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MainTest {

  public static void main(String[] args) throws InvalidStatValueException {
    // Test de personajes
    BlockingQueue<GameCharacter> queue = new LinkedBlockingQueue<>();
    Random rnd = new Random();
    // Knights
    for (int i = 0; i < 5; i++) {
      PlayerCharacter knight = new Knight("Knight %d".formatted(i), rnd.nextInt(100), 0, queue);
      System.out.println(knight);
    }
    // Engineer
    for (int i = 0; i < 5; i++) {
      PlayerCharacter engineer = new Engineer("Engineer %d".formatted(i), rnd.nextInt(100), 0, queue);
      System.out.println(engineer);
    }
    // Knights
    for (int i = 0; i < 5; i++) {
      PlayerCharacter thief = new Thief("Knight %d".formatted(i), rnd.nextInt(100), 0, queue);
      System.out.println(thief);
    }
    // Knights
    for (int i = 0; i < 5; i++) {
      PlayerCharacter blackMage = new BlackMage("Knight %d".formatted(i), rnd.nextInt(100),
          0, rnd.nextInt(100), queue);
      System.out.println(blackMage);
    }
    // Knights
    for (int i = 0; i < 5; i++) {
      PlayerCharacter whiteMage = new WhiteMage("Knight %d".formatted(i), rnd.nextInt(100),
          0, rnd.nextInt(100), queue);
      System.out.println(whiteMage);
    }
  }
}
