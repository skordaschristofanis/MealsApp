package org.mealsapp.view;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.mealsapp.transport.MealDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.IOException;

public class MealDataView extends JFrame {
    private JPanel pnlMealData;
    private JLabel lblMealName;
    private JTextField txtMealName;
    private JButton btnSearch;
    private JTable tblSearchResults;
    private JPanel pnlMealDataControls;
    private JButton btnSaveToDB;
    private JButton btnDeleteMealData;
    private JButton btnEditMealData;

    public MealDataView() {
        // Run the configuration methods
        this.configureMealDataViewForm();
        this.configureSearchResultsTableHeaders();

        // Listeners
        btnDeleteMealData.addActionListener(e -> {
            //default icon, custom title
            JOptionPane.showConfirmDialog(pnlMealData, "Είστε σίγουροι ότι θέλετε να συνεχίσετε με τη διαγραφή;",
                    "Επιβεβαίωση διαγραφής", JOptionPane.YES_NO_OPTION);
        });

        // Action listener to the search button to populate the search results table
        btnSearch.addActionListener(e -> populateSearchResultsTable(txtMealName.getText()));
    }

    /**
     * populates
     * @param mealName
     */
    private void populateSearchResultsTable(String mealName) {
        // Call the getMealByName method to fetch the search results
        MealDto mealDto = getMealByName(mealName);

        // Create a new table model with the search results
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Meal");
        model.addColumn("Category");
        model.addColumn("Area");
        model.addColumn("Instructions");
        Object[] rowData = new Object[4];
        rowData[0] = mealDto.getStrMeal();
        rowData[1] = mealDto.getStrCategory();
        rowData[2] = mealDto.getStrArea();
        rowData[3] = mealDto.getStrInstructions();
        model.addRow(rowData);

        // Set the new table model on the search results table
        this.tblSearchResults.setModel(model);
    }

    public static MealDto getMealByName(final String meal) {
        //todo constants.
        String urlToCall = "https://www.themealdb.com/api/json/v1/1/search.php?s="+meal;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(urlToCall).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Gson gson = new Gson();
                String responseBody = response.body().string();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
                JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
                if (mealsArray != null && mealsArray.size() > 0) {
                    JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
                    return new MealDto(
                            mealObject.get("strMeal").getAsString(),
                            mealObject.get("strCategory").getAsString(),
                            mealObject.get("strArea").getAsString(),
                            mealObject.get("strInstructions").getAsString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new MealDto();
        }
        return new MealDto();
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
    }

    public void displayWindow() {

        // Set visibility
        this.setVisible(true);

        // Bring to front
        this.toFront();
    }

}
