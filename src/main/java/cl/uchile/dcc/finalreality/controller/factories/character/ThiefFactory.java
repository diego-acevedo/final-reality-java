package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Thief;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents an {@link AbstractCharacterFactory} that creates a {@link Thief}.
 */
public class ThiefFactory extends TemplateCharacterFactory {

  @Override
  protected void setValues() {
    this.setName("Thief");
    this.setMaxHp();
    this.setDefense();
  }

  @Override
  protected PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    return new Thief(this.getName(), this.getMaxHp(), this.getDefense(), turnsQueue);
  }
}
