package org.mealsapp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealStatisticsView extends JFrame {
    private JPanel pnlMealStatistics;
    private JLabel lblMostSearchedMeals;
    public JTable tblMealStatistics;
    private JPanel pnlMealStatisticsControls;
    public JButton btnRefreshTable;
    public JButton btnSaveToPDF;

    public DefaultTableModel statisticsTableModel;

    public MealStatisticsView() {

        // Run configuration methods
        this.configureMealStatisticsViewForm();
        this.configureMeanStatisticsTableHeaders();
     }

    public void configureMeanStatisticsTableHeaders() {
        // Define the table columns
        TableColumn mealIdColumn = new TableColumn();
        TableColumn strMealColumn = new TableColumn();
        TableColumn strCategoryColumn = new TableColumn();
        TableColumn strAreaColumn = new TableColumn();
        TableColumn viewsColumn = new TableColumn();

        // Set the header values
        mealIdColumn.setHeaderValue("idMeal");
        strMealColumn.setHeaderValue("strMeal");
        strCategoryColumn.setHeaderValue("strCategory");
        strAreaColumn.setHeaderValue("strArea");
        viewsColumn.setHeaderValue("views");

        // Add the columns to the table
        this.tblMealStatistics.addColumn(mealIdColumn);
        this.tblMealStatistics.addColumn(strMealColumn);
        this.tblMealStatistics.addColumn(strCategoryColumn);
        this.tblMealStatistics.addColumn(strAreaColumn);
        this.tblMealStatistics.addColumn(viewsColumn);

        // Add the table model
        String[] columnNames = {"idMeal", "strMeal", "strCategory", "strArea", "views"};
        statisticsTableModel = new DefaultTableModel(columnNames, 0);
        tblMealStatistics.setModel(statisticsTableModel);

        // Set Auto resize mode
        tblMealStatistics.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set cell renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        tblMealStatistics.setDefaultRenderer(Object.class, centerRenderer);
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
