package cl.uchile.dcc.finalreality.modelcontroller.controller.factories.character;

import cl.uchile.dcc.finalreality.modelcontroller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.Engineer;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a {@link AbstractCharacterFactory factory} that creates
 * an {@link Engineer engineer}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class EngineerFactory extends TemplateCharacterFactory {

  @Override
  protected void setValues() {
    this.setName("Engineer");
    this.setMaxHp();
    this.setDefense();
  }

  @Override
  protected PlayerCharacter newCharacter(BlockingQueue<GameCharacter> turnsQueue)
      throws InvalidStatValueException {
    return new Engineer(this.getName(), this.getMaxHp(), this.getDefense(), turnsQueue);
  }
}
