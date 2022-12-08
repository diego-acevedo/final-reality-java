package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

import static cl.uchile.dcc.finalreality.controller.GameDriver.RANDOM_GENERATOR;

/**
 * A class that holds the basic {@link AbstractCharacterFactory} logic.
 */
public abstract class TemplateCharacterFactory implements AbstractCharacterFactory {

  private int charactersCreated = 1;
  private String name;
  private int maxHp;
  private int defense;

  protected void setName(String characterClass) {
    this.name = "%s %d".formatted(characterClass, this.charactersCreated);
    this.charactersCreated++;
  }

  protected void setMaxHp() {
    this.maxHp = RANDOM_GENERATOR.nextInt(1000, 2000);
  }

  protected void setDefense() {
    this.defense = RANDOM_GENERATOR.nextInt(0, 40);
  }

  protected String getName() {
    return this.name;
  }

  protected int getMaxHp() {
    return this.maxHp;
  }

  protected int getDefense() {
    return this.defense;
  }

  protected abstract void setValues();

  protected abstract PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException;

  @Override
  public PlayerCharacter create(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    this.setValues();
    return this.newCharacter(turnsQueue);
  }
}
