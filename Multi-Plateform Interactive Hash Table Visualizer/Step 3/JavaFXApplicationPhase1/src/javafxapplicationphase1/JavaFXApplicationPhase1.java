/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxapplicationphase1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class JavaFXApplicationPhase1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        XHashtable table = new XHashtable(10);
        XHashtableFXDrawer drawer = new XHashtableFXDrawer(table);
        XHashtableFXAnimator animator = new XHashtableFXAnimator(drawer);
        TextField text = new TextField();
        Button insertButton = new Button ("Insert");
        Button deleteButton = new Button ("Delete");
        Button searchButton = new Button ("Search");
        primaryStage.setTitle("XHashtable Visualizer");
        primaryStage.setWidth(700);
        primaryStage.setHeight(800);
        
        HBox bottomPanel = new HBox(10);
        bottomPanel.getChildren().addAll(text, insertButton, deleteButton, searchButton);
        bottomPanel.setPadding(new Insets(10, 10, 10, 10));
        bottomPanel.setAlignment(Pos.CENTER);
        
        BorderPane root = new BorderPane();
        root.setCenter(drawer);
        root.setBottom(bottomPanel);
        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        
        insertButton.setOnAction(e -> {
            String key = text.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                table.add(key);
                animator.animate(index, Color.GREEN);
                drawer.draw();
                text.clear();
            }
        });
        deleteButton.setOnAction(e -> {
            String key = text.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                table.delete(key);
                animator.animate(index, Color.RED);
                drawer.draw();
                text.clear();
            }
        });
        searchButton.setOnAction(e -> {
            String key = text.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                boolean found = table.find(key);
                animator.animate(index, Color.YELLOW);
                drawer.draw();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(found ? "Found: " + key : "Not found: " + key);
                alert.show();
            }
        });
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
