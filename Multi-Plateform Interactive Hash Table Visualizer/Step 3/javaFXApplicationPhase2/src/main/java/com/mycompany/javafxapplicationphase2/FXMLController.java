package com.mycompany.javafxapplicationphase2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

public class FXMLController {
    @FXML private Button insertButton;
    @FXML private Button deleteButton;
    @FXML private Button searchButton;
    @FXML private TextField textField;
    
    private XHashtable table;
    private XHashtableFXDrawer drawer;
    private XHashtableFXAnimator animator;
    
    @FXML
    public void initialize() {}
    public void setDrawer(XHashtableFXDrawer drawer, XHashtable table) {
        this.drawer = drawer;
        this.animator = new XHashtableFXAnimator(drawer);
        
        insertButton.setOnAction(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                table.add(key);
                animator.animate(index, Color.GREEN);
                drawer.draw();
                textField.clear();
            }
        });
        deleteButton.setOnAction(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                animator.animate(index, Color.RED);
                table.delete(key);
                drawer.draw();
                textField.clear();
            }
        });
        searchButton.setOnAction(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = table.hash(key);
                boolean found = table.find(key);
                animator.animate(index, Color.YELLOW);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(found ? "Found: " + key : "Not found: " + key);
                alert.show();
                textField.clear();
            }
        });
    }
}