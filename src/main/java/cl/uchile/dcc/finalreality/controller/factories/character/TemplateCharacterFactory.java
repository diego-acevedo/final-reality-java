package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public abstract class TemplateCharacterFactory implements AbstractCharacterFactory {

  private int n = 1;
  private String name;
  private int maxHp;
  private int defense;
  protected final Random rnd = new Random();

  protected void setName(String characterClass) {
    this.name = "%s %d".formatted(characterClass, this.n);
    this.n++;
  }

  protected void setMaxHp() {
    this.maxHp = this.rnd.nextInt(1000, 2000);
  }

  protected void setDefense() {
    this.defense = this.rnd.nextInt(0, 40);
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
