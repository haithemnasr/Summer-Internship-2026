package javaswingapplication;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private XHashtable hashtable;
    private XHashtableAnimator animator;
    private XHashtableSwingDrawer swingDrawer;
    private JTextField textField;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton searchButton;
    public MainFrame(){
        hashtable = new XHashtable(10);
        swingDrawer = new XHashtableSwingDrawer(hashtable);
        animator = new XHashtableAnimator(swingDrawer);
        textField = new JTextField(15);
        insertButton = new JButton("Insert");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        setTitle("XHashtable Visualizer");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(swingDrawer, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(textField);
        bottomPanel.add(insertButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(searchButton);
        add(bottomPanel, BorderLayout.SOUTH);
        insertButton.addActionListener(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = hashtable.hash(key);
                hashtable.add(key);
                animator.animate(index, Color.GREEN);
                textField.setText("");
            }
        });
        deleteButton.addActionListener(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = hashtable.hash(key);
                hashtable.delete(key);
                animator.animate(index, Color.RED);
                textField.setText("");
            }
        });
        searchButton.addActionListener(e -> {
            String key = textField.getText();
            if (!key.isEmpty()) {
                int index = hashtable.hash(key);
                boolean found = hashtable.find(key);
                animator.animate(index, Color.YELLOW);
                JOptionPane.showMessageDialog(this, found ? "Found: " + key : "Not found: " + key);
            }
        });
        setVisible(true);
    }
    
}
