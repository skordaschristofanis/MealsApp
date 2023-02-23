package org.mealsapp.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {
    private JPanel pnlMain;
    private JButton btnShowMealData;
    private JButton btnShowMealList;
    private JButton btnShowMealStatsAndSaveToPDF;
    private JButton btnExit;

    // Reference to Forms
    private MealDataView mealDataView;

    public MainView() {

        // Open MealDataView form
        btnShowMealData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the for is already open
                if (mealDataView == null) {
                    // Create new form
                    mealDataView = new MealDataView();
                    mealDataView.displayWindow();
                }
                // Display the window
                mealDataView.displayWindow();
            }
        });

        // Exit application
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
    }

    public void displayWindow() {
        // The main application window
        MainView mainView = new MainView();

        // Set the content pane
        mainView.setContentPane(mainView.pnlMain);

        // Set the application title
        mainView.setTitle("MealsApp");

        // Set the size
        mainView.setSize(550, 200);

        // Set the location
        mainView.setLocationRelativeTo(null);

        // Set visibility
        mainView.setVisible(true);

        // Set close operation
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
