package cl.uchile.dcc.finalreality.gui;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Random;

public class FinalReality extends Application {

  private Stage window;
  private Scene scene1, scene2;
  private int height = 600;
  private int width = 1000;
  private String resource_path = "src/main/resources/";
  private GameDriver driver;
  private final Label[] optionsLabels = new Label[20];
  private Text instructions;
  private Text currentCharacter;
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Final Reality");

    try {
      driver = GameDriver.getGameDriver((new Random()).nextLong());
    } catch (InvalidStatValueException e) {
      System.out.println("Driver failed");
      window.close();
    }

    setUpScene1();
    setUpScene2();

    setUpTimer();

    window.setScene(scene1);
    window.show();
  }

  private void setUpScene1() {
    Text title = new Text("FINAL REALITY");
    title.getStyleClass().add("gametitle");
    Text text = new Text("Press ENTER to continue");
    text.getStyleClass().add("gamesubtitle");

    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(title, text);
    layout1.setAlignment(Pos.CENTER);

    scene1 = new Scene(layout1, width, height);
    scene1.getStylesheets().add("file:" + resource_path + "scene1style.css");

    setKeysToScene1();
  }

  private void setUpScene2() {

    BorderPane layout2 = new BorderPane();
    layout2.setRight(setUpRightBorder());
    layout2.setLeft(setUpLeftBorder());

    scene2 = new Scene(layout2, width, height);
    scene2.getStylesheets().add("file:" + resource_path + "scene2style.css");

    setKeysToScene2();
  }

  private VBox setUpRightBorder() {
    for (int i = 0; i < 20; i++) {
      optionsLabels[i] = new Label();
    }
    instructions = new Text();
    instructions.getStyleClass().add("instruction");
    instructions.setWrappingWidth(200);

    VBox optionsLayout = new VBox();
    optionsLayout.getChildren().addAll(optionsLabels);

    VBox rightBorderLayout = new VBox(20);
    rightBorderLayout.getChildren().addAll(instructions, optionsLayout);
    rightBorderLayout.setAlignment(Pos.TOP_LEFT);
    rightBorderLayout.setPrefWidth(200);

    return rightBorderLayout;
  }

  private VBox setUpLeftBorder() {
    currentCharacter = new Text();
    currentCharacter.getStyleClass().add("current-char");
    GameCharacter currentChar = driver.getCurrentCharacter();
    if (currentChar != null) {
      String character = "Current Character:\n" + currentChar.getClass().getSimpleName()
          + "\n" + currentChar.getCurrentHp() + "/" + currentChar.getMaxHp();
      currentCharacter.setText(character);
    } else {
      currentCharacter.setText("");
    }
    currentCharacter.setTextAlignment(TextAlignment.CENTER);

    VBox leftBorderLayout = new VBox();
    leftBorderLayout.getChildren().add(currentCharacter);
    leftBorderLayout.setAlignment(Pos.BOTTOM_CENTER);
    leftBorderLayout.setPrefWidth(200);

    return leftBorderLayout;
  }

  private void beginGame() {
    window.setScene(scene2);
    driver.execute();
  }

  private void setKeysToScene2() {
    scene2.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case UP -> driver.moveCursor(-1);
        case DOWN -> driver.moveCursor(1);
        case ENTER -> driver.execute();
        case ESCAPE -> Platform.exit();
      }
    });
  }

  private void setKeysToScene1() {
    scene1.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case ENTER:
          window.setScene(scene2);
          driver.execute();
          break;
        case ESCAPE:
          Platform.exit();
      }
    });
  }

  private void setUpTimer() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {

        String[] options = driver.getOptions().toArray(new String[0]);
        for (int i = 0; i < optionsLabels.length; i++) {
          if (i < options.length) {
            optionsLabels[i].setText(options[i]);
          } else {
            optionsLabels[i].setText("");
          }
        }

        String instruction = driver.getInstructions();
        instructions.setText(instruction);

        GameCharacter currentChar = driver.getCurrentCharacter();
        if (currentChar != null) {
          String character = "Current character:\n" + currentChar.getClass().getSimpleName()
              + "\n" + currentChar.getCurrentHp() + "/" + currentChar.getMaxHp();
          currentCharacter.setText(character);
        } else {
          currentCharacter.setText("");
        }
      }
    };
    timer.start();
  }
}
