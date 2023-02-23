package org.mealsapp.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JPanel pnlMain;
    private JButton btnShowMealData;
    private JButton btnShowMealList;
    private JButton btnShowMealStatsAndSaveToPDF;
    private JButton btnExit;

    // Reference to Forms
    private MealDataView mealDataView;
    private MealCategoriesView mealCategoriesView;
    private MealStatisticsView mealStatisticsView;

    public MainView() {

        // Open MealDataView form
        btnShowMealData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the form is already open
                if (mealDataView == null) {
                    // Create new form
                    mealDataView = new MealDataView();
                }
                // Display the window
                mealDataView.displayWindow();
            }
        });

        // Open MealCategoryView form
        btnShowMealList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the form is already open
                if (mealCategoriesView == null) {
                    // Create new form
                    mealCategoriesView = new MealCategoriesView();
                }
                // Display the window
                mealCategoriesView.displayWindow();
            }
        });

        // Open MealStatisticsVeiw form
        btnShowMealStatsAndSaveToPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the form is already open
                if (mealStatisticsView == null) {
                    // Create new form
                    mealStatisticsView = new MealStatisticsView();
                }
                // Display the window
                mealStatisticsView.displayWindow();
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
