package org.mealsapp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MealCategoriesView extends JFrame {
    private JPanel pnlMealCategories;
    private JLabel lblMealCategory;
    public JTextField txtCategoryName;
    public JButton btnSearchCategory;
    private JTable tblCategoryResults;
    public DefaultTableModel categoryResultsTableModel;

    public MealCategoriesView() {
        // Run the configuration methods
        this.configureMealCategoriesViewForm();
        this.configureCategoryResultsTableHeaders();
    }

    private void configureMealCategoriesViewForm() {
        // The main configuration for the MealCategoriesViewForm

        // Set the content pane
        this.setContentPane(this.pnlMealCategories);

        // Set the application title
        this.setTitle("MealsApp - Λίστα γευμάτων ανά κατηγορία");

        // Set the size
        this.setSize(500, 400);

        // Set the location
        this.setLocationRelativeTo(null);

        // Set close operation
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void configureCategoryResultsTableHeaders() {
        // Define the table columns
        TableColumn strMealColumn = new TableColumn();

        // Set the header values
        strMealColumn.setHeaderValue("strMeal");

        // Add the columns to the table
        this.tblCategoryResults.addColumn(strMealColumn);

        // Add the table model
        String[] columnNames = {"strMeal"};
        categoryResultsTableModel = new DefaultTableModel(columnNames, 0);
        tblCategoryResults.setModel(categoryResultsTableModel);
    }

    public void displayWindow(){

        // Set visibility
        this.setVisible(true);

        // Bring to front
        this.toFront();
    }
}
