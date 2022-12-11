package cl.uchile.dcc.finalreality.controller.factories.character;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.concurrent.BlockingQueue;



/**
 * A class that represents a {@link AbstractCharacterFactory factory} that
 * creates a {@link WhiteMage white mage}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class WhiteMageFactory extends TemplateCharacterFactory {

  private int maxMp;

  /**
   * Sets the maxMp to a random value.
   */
  protected void setMaxMp() {
    this.maxMp = RANDOM_GENERATOR.nextInt(50, 150);
  }

  /**
   * Return the value generated for maxMp.
   *
   * @return The random generated value for maxMp.
   */
  protected int getMaxMp() {
    return this.maxMp;
  }

  @Override
  protected void setValues() {
    this.setName("WhiteMage");
    this.setMaxHp();
    this.setDefense();
    this.setMaxMp();
  }

  @Override
  protected PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    return new WhiteMage(this.getName(), this.getMaxHp(), this.getDefense(),
        this.getMaxMp(), turnsQueue);
  }
}
