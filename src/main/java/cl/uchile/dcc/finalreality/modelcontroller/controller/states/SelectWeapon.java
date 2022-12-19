package cl.uchile.dcc.finalreality.modelcontroller.controller.states;

import cl.uchile.dcc.finalreality.modelcontroller.controller.GameDriver;
import cl.uchile.dcc.finalreality.modelcontroller.controller.player.Inventory;
import cl.uchile.dcc.finalreality.modelcontroller.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.modelcontroller.model.weapon.Weapon;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link GameState} that represents the player is selecting a {@link Weapon weapon} from
 * the {@link Inventory inventory}.
 *
 * @author <a href="https://github.com/diego-acevedo">Diego Acevedo</a>
 */
public class SelectWeapon extends AbstractState {

  private final List<Weapon> items;

  /**
   * Creates a {@link SelectWeapon "select weapon" state}. Its failed state is
   * {@link PlayerSelectAction "player select action" state} Its next state is
   * {@link NewTurn "new turn" state}.
   *
   * @param driver The {@link GameDriver driver} this {@link SelectWeapon state} belongs to.
   * @param failed The {@link GameState state} the game should go back to when something fails.
   */
  public SelectWeapon(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.items = gameDriver.getWeapons();
  }

  @Override
  public void execute() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % items.size())
          + items.size();
    } else {
      selectPos = gameDriver.getCursor() % items.size();
    }
    Weapon weapon = items.remove(selectPos);
    String status = gameDriver.equip(weapon, (PlayerCharacter) gameDriver.getCurrentCharacter());
    System.out.println(status);
    gameDriver.setActionOutput(status);
    goBack();
  }

  @Override
  public List<String> options() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % items.size())
          + items.size();
    } else {
      selectPos = gameDriver.getCursor() % items.size();
    }
    List<String> options = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
      if (i == selectPos) {
        options.add("> " + items.get(i).getName());
      } else {
        options.add("  " + items.get(i).getName());
      }
    }
    return options;
  }

  @Override
  public String stateInstruction() {
    return "Select a weapon";
  }

  @Override
  public String getStats() {
    int selectPos;
    if (gameDriver.getCursor() < 0) {
      selectPos = (gameDriver.getCursor() % items.size())
          + items.size();
    } else {
      selectPos = gameDriver.getCursor() % items.size();
    }
    Weapon weapon = items.get(selectPos);
    return "Stats:\n" + weapon.getStats();
  }
}
