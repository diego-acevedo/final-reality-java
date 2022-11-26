package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.WhiteMage;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents an {@link AbstractCharacterFactory} that creates a {@link WhiteMage}.
 */
public class WhiteMageFactory extends TemplateCharacterFactory {

  private int maxMp;

  protected void setMaxMp() {
    this.maxMp = this.rnd.nextInt(50, 150);
  }

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
