package org.mealsapp.view;

import javax.swing.*;

public class MealCategoriesView extends JFrame {
    private JPanel pnlMealCategories;
    private JLabel lblMealCategory;
    private JComboBox comboMealCategory;
    private JTextArea txtCategoryResults;

    public MealCategoriesView() {
        // Run the configuration methods
        this.configureMealCategoriesViewForm();
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


    public void displayWindow(){

        // Set visibility
        this.setVisible(true);

        // Bring to front
        this.toFront();
    }
}
