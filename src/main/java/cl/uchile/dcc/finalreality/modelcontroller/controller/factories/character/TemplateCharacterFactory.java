package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds the basic {@link AbstractCharacterFactory factory} logic.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public abstract class TemplateCharacterFactory implements AbstractCharacterFactory {

  private int charactersCreated = 1;
  private String name;
  private int maxHp;
  private int defense;

  /**
   * Sets the name to a random value.
   *
   * @param characterClass The name of the {@link PlayerCharacter character}'s class.
   */
  protected void setName(String characterClass) {
    this.name = "%s %d".formatted(characterClass, this.charactersCreated);
    this.charactersCreated++;
  }

  /**
   * Sets the maxHp to a random value.
   */
  protected void setMaxHp() {
    this.maxHp = GameDriver.RANDOM_GENERATOR.nextInt(1000, 2000);
  }

  /**
   * Sets the defense to a random value.
   */
  protected void setDefense() {
    this.defense = GameDriver.RANDOM_GENERATOR.nextInt(0, 40);
  }

  /**
   * Returns the value generated for name.
   *
   * @return The random generated value for name.
   */
  protected String getName() {
    return this.name;
  }

  /**
   * Returns the value generated for maxHp.
   *
   * @return The random generated value for maxHp.
   */
  protected int getMaxHp() {
    return this.maxHp;
  }

  /**
   * Returns the value generated for defense.
   *
   * @return The random generated value for defense.
   */
  protected int getDefense() {
    return this.defense;
  }

  /**
   * Assigns a new random generated value to all parameters.
   */
  protected abstract void setValues();

  /**
   * Creates a new {@link PlayerCharacter character} with random parameters.
   *
   * @param turnsQueue Turns queue of the
   *     {@link GameDriver game driver}.
   * @return New {@link PlayerCharacter character} with random generated attributes.
   * @throws InvalidStatValueException Values must be valid.
   */
  protected abstract PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException;

  @Override
  public PlayerCharacter create(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    this.setValues();
    return this.newCharacter(turnsQueue);
  }
}
