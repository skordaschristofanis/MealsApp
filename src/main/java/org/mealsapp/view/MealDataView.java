package org.mealsapp.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MealDataView extends JFrame {
    private JPanel pnlMealData;
    private JLabel lblMealName;
    public JTextField txtMealName;
    public JButton btnSearch;
    public JTable tblSearchResults;
    private JPanel pnlMealDataControls;
    public JButton btnSaveToDB;
    public JButton btnDeleteMealData;
    public JButton btnEditMealData;
    private JButton btnClearTable;
    public DefaultTableModel searchTableModel;

    public MealDataView() {

        // Run the configuration methods
        this.configureMealDataViewForm();
        this.configureSearchResultsTableHeaders();

        // Listeners
         btnClearTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the table
                searchTableModel.setRowCount(0);
            }
        });
     }

    private void configureMealDataViewForm() {
        // The main configuration for the MealDataViewForm

        // Set the content pane
        this.setContentPane(this.pnlMealData);

        // Set the application title
        this.setTitle("MealsApp - Δεδομένα Γευμάτων");

        // Set the size
        this.setSize(650, 400);

        // Set the location
        this.setLocationRelativeTo(null);

        // Set close operation
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Set single selection mode on the table
        this.tblSearchResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set Auto resize mode
        this.tblSearchResults.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Set cell renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        tblSearchResults.setDefaultRenderer(Object.class, centerRenderer);
    }

    private void configureSearchResultsTableHeaders() {
        // Define the table columns
        TableColumn strMealColumn = new TableColumn();
        TableColumn strCategoryColumn = new TableColumn();
        TableColumn strAreaColumn = new TableColumn();
        TableColumn strInstructionsColumn = new TableColumn();

        // Set the header values
        strMealColumn.setHeaderValue("strMeal");
        strCategoryColumn.setHeaderValue("strCategory");
        strAreaColumn.setHeaderValue("strArea");
        strInstructionsColumn.setHeaderValue("strInstructions");

        // Add the columns to the table
        this.tblSearchResults.addColumn(strMealColumn);
        this.tblSearchResults.addColumn(strCategoryColumn);
        this.tblSearchResults.addColumn(strAreaColumn);
        this.tblSearchResults.addColumn(strInstructionsColumn);

        // Add the table model
        String[] columnNames = {"strMeal", "strCategory", "strArea", "strInstructions"};
        searchTableModel = new DefaultTableModel(columnNames, 0);
        tblSearchResults.setModel(searchTableModel);
    }

    public void displayWindow() {

        // Set visibility
        this.setVisible(true);

        // Bring to front
        this.toFront();
    }

}
