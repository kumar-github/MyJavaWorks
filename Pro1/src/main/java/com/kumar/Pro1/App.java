package com.kumar.Pro1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.stage.Stage;

public class App extends Application {
 
 @Override
 public void start(Stage stage) throws Exception {
  
  Label label = LabelBuilder.create().text("Maven + JavaFX = true").alignment(Pos.CENTER).build();
  Scene scene = new Scene(label, 200, 100);
  stage.setScene(scene);
  stage.show();
 }
 
 public static void main(String[] args) {
  Application.launch(args);
 }
}