package com.mycompany.javafxapplicationphase2;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class XHashtableFXDrawer extends Canvas {

    private XHashtable hashtable;
    private int highlightIndex = -1;
    private Color highlightColor = Color.LIGHTGRAY;
    
    public void highlight(int index, Color color) {
        this.highlightIndex = index;
        this.highlightColor = color;
        draw();
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
        draw();
    }

    public XHashtableFXDrawer(XHashtable hashtable) {
        super(800, 600);
        this.hashtable = hashtable;
        draw();
    }
    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        Node[] table = hashtable.getTable();
        int startX = 20;
        int startY = 30;
        int boxWidth = 80;
        int boxHeight = 30;
        int rowHeight = 60;
        for (int i = 0; i < table.length; i++) {
            int y = startY + i * rowHeight;
            gc.setStroke(Color.BLACK);
            gc.strokeText("[" + i + "]", startX, y + 20);
            Node current = table[i];
            int x = startX + 40;
            while (current != null) {
                if (i == highlightIndex) {
                    gc.setFill(highlightColor);
                } else {
                    gc.setFill(Color.LIGHTGRAY);
                }
                gc.fillRect(x, y, boxWidth, boxHeight);
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x, y, boxWidth, boxHeight);
                gc.strokeText(current.key, x + 5, y + 20);

                if (current.next != null) {
                    gc.strokeLine(x + boxWidth, y + 15, x + boxWidth + 20, y + 15);
                }
                x += boxWidth + 20;
                current = current.next;
            }
        }
    }
}