package org.mealsapp.controller;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.mealsapp.model.MainModel;
import org.mealsapp.view.MealCategoriesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class MealCategoriesController {

    private final MealCategoriesView view;
    private final MainModel model;


    public MealCategoriesController(MealCategoriesView view, MainModel model) {
        this.view = view;
        this.model = model;

        // Click the search button
        this.view.btnSearchCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAPICategories();

            }
        });

        // Return pressed event
        this.view.txtCategoryName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getAPICategories();
                }
            }
        });
    }

    private void getAPICategories() {
        // Clean any existing category data
        view.categoryResultsTableModel.setRowCount(0);

        // Use the provided meal name.
        String category = view.txtCategoryName.getText();

        // Check for empty strings
        if (!category.isEmpty()) {
            // Call API
            String categoryURI = model.getCategoryURI();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(categoryURI + category).build();

            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (response.isSuccessful() && response.body() != null) {
                    // Save response
                    String responseString = response.body().string();
                    // Gson Builder
                    GsonBuilder builder = new GsonBuilder();
                    builder.setPrettyPrinting();
                    Gson gson = builder.create();

                    // Pass to JsonObject
                    JsonObject json = gson.fromJson(responseString, JsonObject.class);

                    // Check for null response
                    if (json != null) {

                        // Get the meals array from the json object
                        JsonArray meals = json.getAsJsonArray("meals");

                        // Iterate over the meals array and add them to the table model
                        for (JsonElement mealElement : meals) {
                            JsonObject mealObject = mealElement.getAsJsonObject();
                            String strMeal = mealObject.get("strMeal").getAsString();
                            view.categoryResultsTableModel.addRow(new Object[]{strMeal});
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }


}
