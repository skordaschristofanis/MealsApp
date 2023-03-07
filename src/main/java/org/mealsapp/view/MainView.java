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
    public MealDataView mealDataView;
    public MealCategoriesView mealCategoriesView;
    private MealStatisticsView mealStatisticsView;

    public MainView() {

        // Check the Views
        this.checkViews();

        // Set the Nimbus look and feel
        this.setNimbusLook();


        // Open MealDataView form
        btnShowMealData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the window
                mealDataView.displayWindow();
            }
        });

        // Open MealCategoryView form
        btnShowMealList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display the window
                mealCategoriesView.displayWindow();
            }
        });

        // Open MealStatisticsView form
        btnShowMealStatsAndSaveToPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

    private void checkViews() {
        /*
        * Creates the initial instances for the Views
        * */

        // Check if the form is already open
        if (this.mealDataView == null) {
            // Create new form
            this.mealDataView = new MealDataView();
        }

        // Check if the form is already open
        if (this.mealCategoriesView == null) {
            // Create new form
            this.mealCategoriesView = new MealCategoriesView();
        }

        // Check if the form is already open
        if (this.mealStatisticsView == null) {
            // Create new form
            this.mealStatisticsView = new MealStatisticsView();
        }
    }

    public void setNimbusLook() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex
            );
        }
    }

    public void displayWindow(MainView view) {
        // Set the content pane
        view.setContentPane(view.pnlMain);

        // Set the application title
        view.setTitle("MealsApp");

        // Set the size
        view.setSize(550, 200);

        // Set the location
        view.setLocationRelativeTo(null);

        // Set visibility
        view.setVisible(true);

        // Set close operation
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
