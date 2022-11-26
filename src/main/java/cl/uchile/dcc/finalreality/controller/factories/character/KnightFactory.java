package cl.uchile.dcc.finalreality.controller.factories.character;

import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.Knight;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents an {@link AbstractCharacterFactory} that creates a {@link Knight}.
 */
public class KnightFactory extends TemplateCharacterFactory {

  @Override
  protected void setValues() {
    this.setName("Knight");
    this.setMaxHp();
    this.setDefense();
  }

  @Override
  protected PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    return new Knight(this.getName(), this.getMaxHp(), this.getDefense(), turnsQueue);
  }
}
