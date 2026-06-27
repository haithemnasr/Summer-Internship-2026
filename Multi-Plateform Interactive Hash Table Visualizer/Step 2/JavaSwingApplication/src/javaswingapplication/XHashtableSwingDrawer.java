/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingapplication;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
public class XHashtableSwingDrawer extends JPanel{
    public XHashtable hashtable;
    private int highlightIndex = -1;
    private Color highlightColor = Color.LIGHT_GRAY;
    public XHashtableSwingDrawer(XHashtable hashtable) {
        this.hashtable = hashtable;
    }
    public void highlight(int index, Color color) {
        this.highlightIndex = index;
        this.highlightColor = color;
        repaint();
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Node[] table = hashtable.getTable();
        int startX = 20;
        int startY = 30;
        int boxWidth = 80;
        int boxHeight = 30;
        int rowHeight = 60;

        for (int i = 0; i < table.length; i++) {
            int y = startY + i * rowHeight;
            g2.setColor(Color.BLACK);
            g2.drawString("[" + i + "]", startX, y + 20);

            Node current = table[i];
            int x = startX + 40;
            while (current != null) {
                if (i == highlightIndex) {
                    g2.setColor(highlightColor);
                } else {
                    g2.setColor(Color.LIGHT_GRAY);
                }
                g2.fillRect(x, y, boxWidth, boxHeight);
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, boxWidth, boxHeight);
                g2.drawString(current.key, x + 5, y + 20);
                if (current.next != null) {
                    g2.drawLine(x + boxWidth, y + 15, x + boxWidth + 20, y + 15);
                }
                x += boxWidth + 20;
                current = current.next;
            }

        }
    }
}
