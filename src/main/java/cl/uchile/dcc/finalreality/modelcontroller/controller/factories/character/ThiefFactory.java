package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Thief;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a {@link AbstractCharacterFactory factory} that
 * creates a {@link Thief thief}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
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
