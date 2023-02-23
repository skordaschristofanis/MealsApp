package org.mealsapp.view;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class MealStatisticsView extends JFrame {
    private JPanel pnlMealStatistics;
    private JLabel lblMostSearchedMeals;
    private JTable tblMealStatistics;
    private JPanel pnlMealStatisticsControls;
    private JButton btnRefreshTable;
    private JButton btnSaveToPDF;

    public MealStatisticsView() {

        // Run configuration methods
        this.configureMealStatisticsViewForm();
        this.configureMeanStatisticsTableHeaders();
    }

    public void configureMeanStatisticsTableHeaders() {
        // Define the table columns
        TableColumn strMealColumn = new TableColumn();
        TableColumn popularityColumn = new TableColumn();

        // Set the header values
        strMealColumn.setHeaderValue("strMeal");
        popularityColumn.setHeaderValue("Δημοφιλία");

        // Add the columns to the table
        this.tblMealStatistics.addColumn(strMealColumn);
        this.tblMealStatistics.addColumn(popularityColumn);
    }

    public void configureMealStatisticsViewForm() {
        // The main configuration for the MealStatisticsViewForm

        // Set the content pane
        this.setContentPane(this.pnlMealStatistics);

        // Set the application title
        this.setTitle("MealsApp - Στατιστικά Δεδομένα Γευμάτων");

        // Set the size
        this.setSize(650, 400);

        // Set the location
        this.setLocationRelativeTo(null);

        // Set close operation
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    public void displayWindow() {

        // Set visibility
        this.setVisible(true);

        // Bring to front
        this.toFront();
    }

}
