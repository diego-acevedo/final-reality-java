package cl.uchile.dcc.finalreality.gui;

import cl.uchile.dcc.finalreality.controller.GameDriver;
import cl.uchile.dcc.finalreality.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model.character.Enemy;
import cl.uchile.dcc.finalreality.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model.character.player.PlayerCharacter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

import static cl.uchile.dcc.finalreality.controller.GameDriver.MAX_CHARACTERS;
import static cl.uchile.dcc.finalreality.controller.GameDriver.MAX_ENEMIES;

public class FinalReality extends Application {

  private Stage window;
  private Scene scene1, scene2;
  private AnimationTimer timer1, timer2;
  private final int height = 600;
  private final int width = 1200;
  private final String resource_path = "src/main/resources/";
  private GameDriver driver;
  private final Label[] optionsLabels = new Label[20];
  private Text instructions;
  private Text currentCharacter;
  private final Image spritesCharacters = new Image("file:" + resource_path + "sprites/sprites.png");
  private final Image[] enemySprites = {new Image("file:" + resource_path + "sprites/sprites-enemy1.png"),
                                  new Image("file:" + resource_path + "sprites/sprites-enemy2.png"),
                                  new Image("file:" + resource_path + "sprites/sprites-enemy3.png")};
  private final ImageView currentCharacterSprite = new ImageView();
  private final ImageView[] charactersSprites = new ImageView[MAX_CHARACTERS + MAX_ENEMIES];
  private MediaPlayer mediaPlayer;
  private final Media enterMedia =
      new Media(new File(resource_path + "sounds/ENTER.mp3").toURI().toString());
  private final Media cursorMedia =
      new Media(new File(resource_path + "sounds/CURSOR.mp3").toURI().toString());
  private final Text[] charactersHealth = new Text[MAX_CHARACTERS + MAX_ENEMIES];
  private final Text actionOutput = new Text("");
  private final Text stats = new Text("");
  private final int[][] colorValues = { {129, 226, 255},
                                        {179, 129, 255},
                                        {255, 129, 129},
                                        {255, 236, 129},
                                        {129, 255, 131} };
  private Text title = new Text("FINAL REALITY");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Final Reality");
    window.setResizable(false);

    try {
      driver = GameDriver.getGameDriver((new Random()).nextLong());
    } catch (InvalidStatValueException e) {
      System.out.println("Driver failed");
      window.close();
    }

    setUpScene1();
    setUpScene2();

    setUpTimer1();
    setUpTimer2();

    timer2.start();

    Media menuMusic = new Media(new File(resource_path + "sounds/menu.wav").toURI().toString());
    mediaPlayer = new MediaPlayer(menuMusic);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();

    window.setScene(scene1);
    window.show();
  }

  private void setUpScene1() {
    title.getStyleClass().add("gametitle");
    title.setFill(getNewColor());
    Text text = new Text("Press ENTER to continue");
    text.getStyleClass().add("gamesubtitle");

    VBox layout1 = new VBox(20);
    layout1.getStyleClass().add("layout1");
    layout1.getChildren().addAll(title, text);
    layout1.setAlignment(Pos.CENTER);

    scene1 = new Scene(layout1, width, height);
    scene1.getStylesheets().add("file:" + resource_path + "scene1style.css");

    setKeysToScene1();
  }

  private Color getNewColor() {
    long newTime = System.currentTimeMillis();
    double timeSeconds = ((int) newTime) * 0.001;
    int index = ((int) (timeSeconds / 5)) % 5;
    double delta = ((timeSeconds / 5) % 5) - (((int) (timeSeconds / 5)) % 5);

    int[] v1 = colorValues[index];
    int[] v2 = colorValues[(index + 1) % 5];

    int newR = v1[0] + (int) ((v2[0] - v1[0]) * delta);
    int newG = v1[1] + (int) ((v2[1] - v1[1]) * delta);
    int newB = v1[2] + (int) ((v2[2] - v1[2]) * delta);

    return Color.rgb(newR, newG, newB);
  }

  private void setUpScene2() {

    BorderPane layout2 = new BorderPane();
    layout2.setRight(setUpRightBorder());
    layout2.setLeft(setUpLeftBorder());
    layout2.setCenter(setUpCenter());

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
    instructions.setWrappingWidth(170);

    VBox optionsLayout = new VBox();
    optionsLayout.getChildren().addAll(optionsLabels);

    stats.setText(driver.getStats());
    stats.getStyleClass().add("instruction");
    stats.setWrappingWidth(170);

    VBox rightBorderLayout = new VBox(20);
    rightBorderLayout.getChildren().addAll(instructions, optionsLayout, stats);
    VBox.setMargin(instructions, new Insets(15, 15, 0, 15));
    VBox.setMargin(optionsLayout, new Insets(0, 15, 0, 15));
    VBox.setMargin(stats, new Insets(0, 15, 15, 15));
    rightBorderLayout.getStyleClass().add("right-border");
    rightBorderLayout.setAlignment(Pos.TOP_LEFT);
    rightBorderLayout.setPrefWidth(200);

    return rightBorderLayout;
  }

  private VBox setUpLeftBorder() {
    currentCharacter = new Text();
    currentCharacter.getStyleClass().add("current-char");
    GameCharacter currentChar = driver.getCurrentCharacter();
    if (currentChar != null) {
      String character = "Current Character:\n" + currentChar.getName()
          + "\n" + currentChar.getInfo();
      currentCharacter.setText(character);
    } else {
      currentCharacter.setText("");
    }
    currentCharacter.setTextAlignment(TextAlignment.CENTER);

    int row = 0;
    int column = 0;
    if (currentChar != null) {
      if (currentChar.isPlayable()) {
        row = ((PlayerCharacter) currentChar).getSpriteRow();
      } else {
        row = 9;
      }
      column = 1;
    }

    Rectangle2D cropped = new Rectangle2D(column * 120, row * 120, 120, 120);
    currentCharacterSprite.setImage(spritesCharacters);
    currentCharacterSprite.setViewport(cropped);
    currentCharacterSprite.setFitWidth(150);
    currentCharacterSprite.setFitHeight(150);
    currentCharacterSprite.setSmooth(true);

    actionOutput.setText("Result:\n" + driver.getActionOutput() + "\n----------");
    actionOutput.getStyleClass().add("action-output");
    actionOutput.setWrappingWidth(170);
    actionOutput.setFill(Color.DARKSLATEBLUE);

    VBox leftBorderLayout = new VBox();
    leftBorderLayout.getChildren().addAll(actionOutput, currentCharacter, currentCharacterSprite);
    VBox.setMargin(actionOutput, new Insets(15, 15, 0, 15));
    VBox.setMargin(currentCharacter, new Insets(15, 15, 15, 15));
    VBox.setMargin(currentCharacterSprite, new Insets(15, 15, 15, 15));
    leftBorderLayout.getStyleClass().add("left-border");
    leftBorderLayout.setAlignment(Pos.BOTTOM_CENTER);
    leftBorderLayout.setPrefWidth(200);

    return leftBorderLayout;
  }

  private Pane setUpCenter() {

    Pane centerBorderLayout = new StackPane();
    centerBorderLayout.getStyleClass().add("center-border");
    centerBorderLayout.setPrefWidth(800);
    centerBorderLayout.setPrefHeight(600);

    VBox characters = new VBox(10);
    VBox enemiesLeft = new VBox(10);
    VBox enemiesRight = new VBox((10));
    for (int i = 0; i < MAX_CHARACTERS; i++) {
      PlayerCharacter character = driver.getPlayerCharacters().get(i);
      int column = (int) (5 + (((double) (System.currentTimeMillis()/1000))/0.2) % 2);
      int row = character.getSpriteRow();

      charactersHealth[i] = new Text(character.getCurrentHp() + "/" + character.getMaxHp());
      charactersHealth[i].getStyleClass().add("healthbar");
      charactersHealth[i].setFill(Color.WHITE);

      Rectangle2D cropped = new Rectangle2D(column * 120, row * 120 + 1, 120, 120);

      charactersSprites[i] = new ImageView(spritesCharacters);
      charactersSprites[i].setViewport(cropped);
      charactersSprites[i].setFitHeight(60);
      charactersSprites[i].setFitWidth(60);
      charactersSprites[i].setSmooth(true);

      VBox characterBox = new VBox(2);
      characterBox.getChildren().addAll(charactersHealth[i], charactersSprites[i]);
      characterBox.setTranslateX(550);
      characterBox.setTranslateY(100);

      characters.getChildren().add(characterBox);
    }

    for (int i = MAX_CHARACTERS; i < MAX_CHARACTERS + MAX_ENEMIES; i++) {
      Enemy enemy = driver.getEnemyList().get(i - MAX_CHARACTERS);
      int index = enemy.getWeight() % 3;

      charactersHealth[i] = new Text(enemy.getCurrentHp() + "/" + enemy.getMaxHp());
      charactersHealth[i].getStyleClass().add("healthbar");
      charactersHealth[i].setFill(Color.WHITE);

      charactersSprites[i] = new ImageView(enemySprites[index]);
      charactersSprites[i].setFitHeight(80);
      charactersSprites[i].setFitWidth(80);
      charactersSprites[i].setSmooth(true);

      VBox characterBox = new VBox(2);
      characterBox.getChildren().addAll(charactersHealth[i], charactersSprites[i]);

      if (i < MAX_CHARACTERS + 3) {
        characterBox.setTranslateX(120);
        characterBox.setTranslateY(160);
        enemiesLeft.getChildren().add(characterBox);
      } else {
        characterBox.setTranslateX(230);
        characterBox.setTranslateY(200);
        enemiesRight.getChildren().add(characterBox);
      }
    }

    centerBorderLayout.getChildren().addAll(characters, enemiesLeft, enemiesRight);

    return centerBorderLayout;
  }

  private void beginGame() {
    timer2.stop();
    timer1.start();
    window.setScene(scene2);
    mediaPlayer.stop();
    Media battle = new Media(new File(resource_path + "/sounds/battle.wav").toURI().toString());
    mediaPlayer = new MediaPlayer(battle);
    mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    mediaPlayer.play();
    driver.execute();
  }

  private void setKeysToScene2() {
    scene2.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case UP -> {
          MediaPlayer cursor = new MediaPlayer(cursorMedia);
          cursor.play();
          driver.moveCursor(-1);
        }
        case DOWN -> {
          MediaPlayer cursor = new MediaPlayer(cursorMedia);
          cursor.play();
          driver.moveCursor(1);
        }
        case ENTER -> {
          MediaPlayer enter = new MediaPlayer(enterMedia);
          enter.play();
          driver.execute();
        }
        case ESCAPE -> Platform.exit();
      }
    });
  }

  private void setKeysToScene1() {
    scene1.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case ENTER -> {
          MediaPlayer enter = new MediaPlayer(enterMedia);
          enter.play();
          beginGame();
        }
        case ESCAPE -> Platform.exit();
      }
    });
  }

  private void setUpTimer1() {
    timer1 = new AnimationTimer() {
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
          String character = "Current character:\n" + currentChar.getName()
              + "\n" + currentChar.getInfo();
          currentCharacter.setText(character);
        } else {
          currentCharacter.setText("");
        }

        int row = 0;
        int column = 1;
        if (currentChar != null) {
          if (currentChar.isPlayable()) {
            row = ((PlayerCharacter) currentChar).getSpriteRow();
            Rectangle2D cropped = new Rectangle2D(column * 120, row * 120 + 1, 120, 120);
            currentCharacterSprite.setImage(spritesCharacters);
            currentCharacterSprite.setViewport(cropped);
          } else {
            int index = ((Enemy) currentChar).getWeight() % 3;
            currentCharacterSprite.setImage(enemySprites[index]);
            currentCharacterSprite.setViewport(new Rectangle2D(0, 0, 206, 206));
          }
          currentCharacterSprite.setFitWidth(150);
          currentCharacterSprite.setFitHeight(150);
        }

        for (int i = 0; i < MAX_CHARACTERS; i++) {
          PlayerCharacter character = driver.getPlayerCharacters().get(i);
          double time = System.currentTimeMillis() * 0.001;
          int r = character.getSpriteRow();
          int c = 5 + (int) ((time * 3) % 2);

          charactersHealth[i].setText(character.getCurrentHp() + "/" + character.getMaxHp());

          Rectangle2D newAnimationFrame;
          if (character.isAlive()) {
            newAnimationFrame = new Rectangle2D(c * 120, r * 120 + 1, 120, 120);
          } else {
            newAnimationFrame = new Rectangle2D(10 * 120, r * 120 + 1, 120, 120);
          }


          charactersSprites[i].setViewport(newAnimationFrame);
        }

        for (int i = MAX_CHARACTERS; i < MAX_CHARACTERS + MAX_ENEMIES; i++) {
          Enemy enemy = driver.getEnemyList().get(i - MAX_CHARACTERS);
          int index = enemy.getWeight() % 3;

          charactersHealth[i].setText(enemy.getCurrentHp() + "/" + enemy.getMaxHp());
        }

        actionOutput.setText("Result:\n" + driver.getActionOutput() + "\n-------------------------");

        stats.setText(driver.getStats());
      }
    };
  }

  void setUpTimer2() {
    timer2 = new AnimationTimer() {
      @Override
      public void handle(long now) {
        title.setFill(getNewColor());
      }
    };
  }
}
