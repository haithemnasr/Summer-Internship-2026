/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.mycompany.javafxapplicationphase2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class JavaFXApplicationPhase2 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
        Parent root = loader.load();

        FXMLController controller = loader.getController();

        XHashtable table = new XHashtable(10);
        XHashtableFXDrawer drawer = new XHashtableFXDrawer(table);
        controller.setDrawer(drawer, table);

        BorderPane borderPane = (BorderPane) ((AnchorPane) root).getChildren().get(0);
        borderPane.setCenter(drawer);

        Scene scene = new Scene(root, 700, 800);
        primaryStage.setTitle("XHashtable Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
