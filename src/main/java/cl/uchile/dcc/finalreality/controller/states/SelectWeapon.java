package cl.uchile.dcc.finalreality.controller.states;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import cl.uchile.dcc.finalreality.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class SelectWeapon extends AbstractState {

  private final List<Weapon> items;

  public SelectWeapon(GameDriver driver, GameState failed) {
    super(driver, failed);
    this.nextState = new NewTurn(driver);
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
    if (gameDriver.isTransitionSucceeded()) {
      nextState();
    } else {
      goBack();
    }
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
        options.add("-> " + items.get(i).getName());
      } else {
        options.add(items.get(i).getName());
      }
    }
    return options;
  }
}
