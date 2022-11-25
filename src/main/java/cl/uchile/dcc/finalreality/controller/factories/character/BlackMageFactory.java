package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.BlackMage;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;

import java.util.concurrent.BlockingQueue;

public class BlackMageFactory extends TemplateCharacterFactory {

  private int maxMp;

  protected void setMaxMp() {
    this.maxMp = this.rnd.nextInt(50, 150);
  }

  protected int getMaxMp() {
    return this.maxMp;
  }

  @Override
  protected void setValues() {
    this.setName("BlackMage");
    this.setMaxHp();
    this.setDefense();
    this.setMaxMp();
  }

  @Override
  protected PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    return new BlackMage(this.getName(), this.getMaxHp(), this.getDefense(), this.getMaxMp(), turnsQueue);
  }
}
