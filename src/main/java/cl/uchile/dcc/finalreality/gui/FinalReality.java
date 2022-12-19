package cl.uchile.dcc.finalreality.gui;

import cl.uchile.dcc.finalreality.model_controller.controller.GameDriver;
import cl.uchile.dcc.finalreality.model_controller.exceptions.InvalidStatValueException;
import cl.uchile.dcc.finalreality.model_controller.model.character.Enemy;
import cl.uchile.dcc.finalreality.model_controller.model.character.GameCharacter;
import cl.uchile.dcc.finalreality.model_controller.model.character.player.PlayerCharacter;
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
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.util.Random;

import static cl.uchile.dcc.finalreality.model_controller.controller.GameDriver.MAX_CHARACTERS;
import static cl.uchile.dcc.finalreality.model_controller.controller.GameDriver.MAX_ENEMIES;

public class FinalReality extends Application {

  private Stage window;
  private Scene scene1, scene2;
  private AnimationTimer timer1, timer2;
  private final int height = 700;
  private final int width = 1000;
  private final String resource_path = "src/main/resources/";
  private GameDriver driver;
  private final Label[] optionsLabels = new Label[3];
  private Text instructions;
  private Text currentCharacter;
  private final Image spritesCharacters = new Image("file:" + resource_path + "sprites/sprites.png");
  private final Image[] enemySprites = {new Image("file:" + resource_path + "sprites/sprites-enemy1.png"),
                                  new Image("file:" + resource_path + "sprites/sprites-enemy2.png"),
                                  new Image("file:" + resource_path + "sprites/sprites-enemy3.png")};
  private final ImageView currentCharacterSprite = new ImageView();
  private final ImageView[] charactersSprites = new ImageView[MAX_CHARACTERS + MAX_ENEMIES];
  private final ImageView[][] effectSprites = new ImageView[MAX_ENEMIES][3];
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
  private final Text title = new Text("FINAL REALITY");

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    window = primaryStage;
    window.setTitle("Final Reality");
    window.setResizable(true);

    try {
      driver = GameDriver.getGameDriver((new Random()).nextLong());
    } catch (InvalidStatValueException e) {
      System.out.println("Driver failed");
      window.close();
    }

    setUpScene1();
    setUpScene2();

    setUpTimerScene2();
    setUpTimerScene1();

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

    AnchorPane background = setBackground();
    HBox menu = setUpMenu();
    HBox output = setUpOutput();
    HBox playerCharacters = setUpPlayerCharacter();
    VBox enemies = setUpEnemies();
    background.getChildren().addAll(menu, output, playerCharacters, enemies);
    AnchorPane.setBottomAnchor(menu, 0.0);
    AnchorPane.setBottomAnchor(output, 170.0);
    AnchorPane.setLeftAnchor(output, 15.0);
    AnchorPane.setRightAnchor(playerCharacters, 100.0);
    AnchorPane.setTopAnchor(playerCharacters, 110.0);
    AnchorPane.setLeftAnchor(enemies, 100.0);
    AnchorPane.setTopAnchor(enemies, 100.0);

    scene2 = new Scene(background, width, height);
    scene2.getStylesheets().add("file:" + resource_path + "scene2style.css");

    setKeysToScene2();
  }

  private AnchorPane setBackground() {
    AnchorPane background = new AnchorPane();
    background.getStyleClass().add("background");
    return background;
  }

  private HBox setUpOutput() {

    HBox outputBox = new HBox();
    outputBox.getStyleClass().add("output");
    outputBox.setPrefWidth(685);
    outputBox.setPrefHeight(50);

    actionOutput.setText(driver.getActionOutput());
    actionOutput.getStyleClass().add("action-output");
    actionOutput.setWrappingWidth(585);

    outputBox.getChildren().add(actionOutput);
    outputBox.setAlignment(Pos.CENTER_LEFT);
    HBox.setMargin(actionOutput, new Insets(0, 0, 0, 20));

    return outputBox;
  }

  private HBox setUpMenu() {

    for (int i = 0; i < optionsLabels.length; i++) {
      optionsLabels[i] = new Label();
    }

    VBox options = new VBox(5);
    options.getStyleClass().add("menu");
    options.setPrefHeight(150);
    options.setPrefWidth(400);

    instructions = new Text();
    instructions.getStyleClass().add("instruction");
    instructions.setWrappingWidth(370);

    VBox optionsLayout = new VBox(optionsLabels);

    options.getChildren().add(instructions);
    options.getChildren().addAll(optionsLayout);

    VBox.setMargin(instructions, new Insets(15, 15, 15, 15));
    VBox.setMargin(optionsLayout, new Insets(0, 15, 15, 15));

    options.setPrefWidth(400);
    options.setPrefHeight(150);

    VBox statsLayout = new VBox();
    statsLayout.setPrefWidth(250);
    statsLayout.setPrefHeight(150);
    statsLayout.getStyleClass().add("stats");
    stats.setText(driver.getStats());
    stats.getStyleClass().add("instruction");
    statsLayout.getChildren().add(stats);
    VBox.setMargin(stats, new Insets(15, 15, 15, 25));
    stats.setWrappingWidth(210);

    HBox currentCharacterLayout = new HBox();
    currentCharacterLayout.setPrefWidth(250);
    currentCharacterLayout.setPrefHeight(150);
    currentCharacterLayout.getStyleClass().add("cur-char-layout");
    currentCharacterLayout.setAlignment(Pos.CENTER);

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

    Rectangle2D cropped = new Rectangle2D(0, 0, 120, 120);
    currentCharacterSprite.setImage(spritesCharacters);
    currentCharacterSprite.setViewport(cropped);
    currentCharacterSprite.setFitWidth(50);
    currentCharacterSprite.setFitHeight(50);
    currentCharacterSprite.setSmooth(true);

    currentCharacterLayout.getChildren().addAll(currentCharacter, currentCharacterSprite);

    HBox menu = new HBox();
    menu.getChildren().addAll(options, statsLayout, currentCharacterLayout);
    HBox.setMargin(options, new Insets(15, 15, 15, 15));
    HBox.setMargin(statsLayout, new Insets(15, 15, 15, 0));
    HBox.setMargin(currentCharacterLayout, new Insets(15, 15, 15, 0));

    return menu;
  }

  private HBox setUpPlayerCharacter() {
    VBox leftCharacters = new VBox(20);
    leftCharacters.setAlignment(Pos.CENTER);
    VBox rightCharacters = new VBox(20);
    rightCharacters.setAlignment(Pos.CENTER);
    for (int i = 0; i < MAX_CHARACTERS; i++) {
      PlayerCharacter character = driver.getPlayerCharacters().get(i);
      double time = System.currentTimeMillis() * 0.001;
      int column = 5 + (int) ((time * 3) % 2);
      int row = character.getSpriteRow();

      charactersHealth[i] = new Text(character.getName() + "\n" + character.getCurrentHp() + "/" + character.getMaxHp());
      charactersHealth[i].setTextAlignment(TextAlignment.CENTER);
      charactersHealth[i].getStyleClass().add("healthbar");

      Rectangle2D cropped = new Rectangle2D(column * 120, row * 120 + 1, 120, 120);

      charactersSprites[i] = new ImageView(spritesCharacters);
      charactersSprites[i].setViewport(cropped);
      charactersSprites[i].setFitHeight(80);
      charactersSprites[i].setFitWidth(80);
      charactersSprites[i].setSmooth(true);

      VBox characterBox = new VBox(2);
      characterBox.getChildren().addAll(charactersHealth[i], charactersSprites[i]);

      if (i < MAX_CHARACTERS / 2) {
        leftCharacters.getChildren().add(characterBox);
      } else {
        rightCharacters.getChildren().add(characterBox);
      }
    }

    HBox characters = new HBox(40);
    characters.getChildren().addAll(leftCharacters, rightCharacters);

    return characters;
  }

  private VBox setUpEnemies() {
    HBox enemiesTop = new HBox(20);
    HBox enemiesBottom = new HBox(20);

    for (int i = MAX_CHARACTERS; i < MAX_CHARACTERS + MAX_ENEMIES; i++) {
      Enemy enemy = driver.getEnemyList().get(i - MAX_CHARACTERS);
      int index = enemy.getWeight() % 3;

      charactersHealth[i] = new Text(enemy.getName() + "\n" + enemy.getCurrentHp() + "/" + enemy.getMaxHp());
      charactersHealth[i].setTextAlignment(TextAlignment.CENTER);
      charactersHealth[i].getStyleClass().add("healthbar");

      charactersSprites[i] = new ImageView(enemySprites[index]);
      charactersSprites[i].setFitHeight(140);
      charactersSprites[i].setFitWidth(140);

      int column;
      effectSprites[i - MAX_CHARACTERS][0] = new ImageView(new Image("file:" + resource_path + "sprites/effects.png"));
      column = enemy.getBurntStatus().spriteColumn();
      effectSprites[i - MAX_CHARACTERS][0].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
      effectSprites[i - MAX_CHARACTERS][0].setFitWidth(40);
      effectSprites[i - MAX_CHARACTERS][0].setFitHeight(40);
      effectSprites[i - MAX_CHARACTERS][1] = new ImageView(new Image("file:" + resource_path + "sprites/effects.png"));
      column = enemy.getPoisonStatus().spriteColumn();
      effectSprites[i - MAX_CHARACTERS][1].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
      effectSprites[i - MAX_CHARACTERS][1].setFitWidth(40);
      effectSprites[i - MAX_CHARACTERS][1].setFitHeight(40);
      effectSprites[i - MAX_CHARACTERS][2] = new ImageView(new Image("file:" + resource_path + "sprites/effects.png"));
      column = enemy.getParalysisStatus().spriteColumn();
      effectSprites[i - MAX_CHARACTERS][2].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
      effectSprites[i - MAX_CHARACTERS][2].setFitWidth(40);
      effectSprites[i - MAX_CHARACTERS][2].setFitHeight(40);

      HBox effects = new HBox(effectSprites[i - MAX_CHARACTERS]);
      effects.setAlignment(Pos.BOTTOM_CENTER);

      StackPane enemySprite = new StackPane(charactersSprites[i], effects);

      VBox characterBox = new VBox(2);
      characterBox.getChildren().addAll(charactersHealth[i], enemySprite);

      if (i < MAX_CHARACTERS + 3) {
        enemiesTop.getChildren().add(characterBox);
      } else {
        enemiesBottom.getChildren().add(characterBox);
      }
    }

    VBox enemies = new VBox(10);
    enemies.getChildren().addAll(enemiesTop, enemiesBottom);

    return enemies;
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
          if (driver.getCurrentCharacter().isPlayable()) {
            driver.execute();
          }
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

  private void setUpTimerScene2() {
    timer1 = new AnimationTimer() {
      @Override
      public void handle(long now) {

        String[] options = driver.getOptions().toArray(new String[0]);
        if (options.length >= 3) {
          int cursor = driver.getCursor();
          int index;
          if (cursor < 0) {
            index = (cursor % options.length) + options.length;
          } else {
            index = cursor % options.length;
          }
          if (index == 0) {
            for (int i = 0; i < optionsLabels.length; i++) {
              optionsLabels[i].setText(options[i]);
            }
          } else if (index == options.length - 1) {
            for (int i = options.length - 3; i < options.length; i++) {
              optionsLabels[i - options.length + 3].setText(options[i]);
            }
          } else {
            for (int i = index - 1; i < index + 2; i++) {
              optionsLabels[i - index + 1].setText(options[i]);
            }
          }
        } else {
          for (Label label : optionsLabels) {
            label.setText("");
          }
          for (int i = 0; i < options.length; i++) {
            optionsLabels[i].setText(options[i]);
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

        int row;
        int column = 1;
        if (currentChar != null) {
          if (currentChar.isPlayable()) {
            row = ((PlayerCharacter) currentChar).getSpriteRow();
            Rectangle2D cropped = new Rectangle2D(column * 120, row * 120 + 1, 120, 120);
            currentCharacterSprite.setImage(spritesCharacters);
            currentCharacterSprite.setViewport(cropped);
          } else {
            int image = ((Enemy) currentChar).getWeight() % 3;
            currentCharacterSprite.setImage(enemySprites[image]);
            currentCharacterSprite.setViewport(new Rectangle2D(0, 0, 206, 206));
          }
          currentCharacterSprite.setFitWidth(50);
          currentCharacterSprite.setFitHeight(50);
        }


        for (int i = 0; i < MAX_CHARACTERS; i++) {
          PlayerCharacter character = driver.getPlayerCharacters().get(i);
          int r = character.getSpriteRow();

          charactersHealth[i].setText(character.getName() + "\n" + character.getCurrentHp() + "/" + character.getMaxHp());

          Rectangle2D newAnimationFrame;
          if (character.isAlive()) {
            if (driver.isGameOver()) {
              if (driver.playerAlive()) {
                newAnimationFrame = new Rectangle2D(7 * 120, r * 120 + 1, 120, 120);
              } else {
                newAnimationFrame = new Rectangle2D(8 * 120, r * 120 + 1, 120, 120);
              }
            } else {
              double time = System.currentTimeMillis() * 0.001;
              int c = 5 + (int) ((time * 3) % 2);
              newAnimationFrame = new Rectangle2D(c * 120, r * 120 + 1, 120, 120);
            }
          } else {
            newAnimationFrame = new Rectangle2D(10 * 120, r * 120 + 1, 120, 120);
          }


          charactersSprites[i].setViewport(newAnimationFrame);
        }

        for (int i = MAX_CHARACTERS; i < MAX_CHARACTERS + MAX_ENEMIES; i++) {
          Enemy enemy = driver.getEnemyList().get(i - MAX_CHARACTERS);

          charactersHealth[i].setText(enemy.getName() + "\n" + enemy.getCurrentHp() + "/" + enemy.getMaxHp());

          column = enemy.getBurntStatus().spriteColumn();
          effectSprites[i - MAX_CHARACTERS][0].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
          column = enemy.getPoisonStatus().spriteColumn();
          effectSprites[i - MAX_CHARACTERS][1].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
          column = enemy.getParalysisStatus().spriteColumn();
          effectSprites[i - MAX_CHARACTERS][2].setViewport(new Rectangle2D(100 * column, 0, 100, 100));
        }

        actionOutput.setText(driver.getActionOutput());

        stats.setText(driver.getStats());

      }
    };
  }

  void setUpTimerScene1() {
    timer2 = new AnimationTimer() {
      @Override
      public void handle(long now) {
        title.setFill(getNewColor());
      }
    };
  }
}
