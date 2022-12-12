package cl.uchile.dcc.finalreality.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FinalReality extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Hello World!");

    StackPane root = new StackPane();
    primaryStage.setScene(new Scene(root, 300, 250));
    primaryStage.show();
  }
}
