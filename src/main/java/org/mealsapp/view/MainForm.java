package org.mealsapp.view;

import javax.swing.*;

public class MainForm extends JFrame {

    public MainForm() {
        // Initialize JFrame and set the title
        super("MealsApp");

        // Set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void displayWindow() {
        // Sets the main application window size and visibility

        // Set starting window size
        setSize(800, 600);

        // Set window visibility
        setVisible(true);
    }

}
