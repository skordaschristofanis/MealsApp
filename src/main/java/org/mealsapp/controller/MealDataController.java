package org.mealsapp.controller;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.mealsapp.model.MainModel;
import org.mealsapp.view.MealDataView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class MealDataController {

    private final MealDataView view;
    private final MainModel model;


    public MealDataController(MealDataView view, MainModel model) {
        this.view = view;
        this.model = model;

        // Click event for the search button
        this.view.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAPIResponse();
            }
        });

        // Return pressed event for the search field
        this.view.txtMealName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getAPIResponse();
                }

            }
        });
    }

    private void getAPIResponse() {
        // Use the provided meal name.
        String meal = view.txtMealName.getText();

        // Check for empty strings
        if (!meal.isEmpty()) {
            // Call API
            String mealURI = model.getMealURI();

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(mealURI + meal).build();

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
                            String strCategory = mealObject.get("strCategory").getAsString();
                            String strArea = mealObject.get("strArea").getAsString();
                            String strInstructions = mealObject.get("strInstructions").getAsString();
                            view.searchTableModel.addRow(new Object[]{strMeal, strCategory, strArea, strInstructions});
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

}
