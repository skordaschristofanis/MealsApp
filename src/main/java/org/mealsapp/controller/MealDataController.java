package org.mealsapp.controller;

import com.google.gson.*;
import jakarta.persistence.Query;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.mealsapp.model.MainModel;
import org.mealsapp.model.Meal;
import org.mealsapp.model.PreexistingEntityException;
import org.mealsapp.view.MealDataView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;


public class MealDataController {

    private final MealDataView view;
    private final MainModel model;
    private final MealJpaController controller;


    public MealDataController(MealDataView view, MainModel model, MealJpaController controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;

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

        // Save to DB
        this.view.btnSaveToDB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = view.tblSearchResults.getSelectedRow();
                // Check for selected row
                if (selectedRow != -1) {

                    // Meal data
                    String strMeal = view.searchTableModel.getValueAt(selectedRow, 0).toString();
                    String strCategory = view.searchTableModel.getValueAt(selectedRow, 1).toString();
                    String strArea = view.searchTableModel.getValueAt(selectedRow, 2).toString();
                    String strInstructions = view.searchTableModel.getValueAt(selectedRow, 3).toString();

                    // Check if the item is already in the DB
                    Query query = model.entityManager.createNamedQuery("Meal.findByStrmeal");
                    query.setParameter("strmeal", strMeal);

                    if (query.getResultList().isEmpty()) {
                        try {
                            // Add to database
                            Meal meal = new Meal();
                            meal.setIdmeal(model.getNextId());
                            meal.setStrmeal(strMeal);
                            meal.setStrcategory(strCategory);
                            meal.setStrarea(strArea);
                            // TODO: Fix adding instructions to the DB!
                            //  meal.setStrinstructions(strInstructions);
                            meal.setStatus(1);
                            // Info message for adding a new meal.
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Το γεύμα καταχωρήθηκε στην βάση δεδομένων ",
                                    "Ενημέρωση",
                                    JOptionPane.INFORMATION_MESSAGE);

                            try {
                                controller.create(meal);
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                    else {
                        // Increase the status if it exists
                        Meal meal = (Meal) query.getSingleResult();
                        meal.setStatus(meal.getStatus() + 1);
                        try {
                            controller.edit(meal);
                            // Status message for existing Meal
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Το γεύμα υπάρχει ήδη στην βάση δεδομένων ",
                                    "Ενημέρωση",
                                    JOptionPane.WARNING_MESSAGE);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });

        // Delete from DB
        this.view.btnDeleteMealData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete meal data
                int selectedRow = view.tblSearchResults.getSelectedRow();

                // Check for selected row
                if (selectedRow != -1) {
                    // Meal data
                    String strMeal = view.searchTableModel.getValueAt(selectedRow, 0).toString();

                    // Check if the item is in the DB
                    Query query = model.entityManager.createNamedQuery("Meal.findByStrmeal");
                    query.setParameter("strmeal", strMeal);

                    if (!query.getResultList().isEmpty()) {
                        int answer = JOptionPane.showOptionDialog(
                                null,
                                "Το γεύμα θα διαγραφεί από τη βάση. Σίγουρα \n" +
                                        "θέλετε να συνεχίσετε;",
                                "Επιβεβαίωση Διαγραφής",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[] {"Ναι", "Όχι"},
                                "Όχι"
                        );

                        if (answer == JOptionPane.YES_OPTION) {
                            // Delete it
                            try {
                                model.entityManager.getTransaction().begin();
                                Meal meal = (Meal) query.getSingleResult();
                                Integer idMeal = meal.getIdmeal();

                                Query deleteQuery = model.entityManager.createQuery(
                                        "DELETE FROM Meal m WHERE m.idmeal = :idmeal"
                                );
                                deleteQuery.setParameter("idmeal", idMeal).executeUpdate();

                                model.entityManager.persist(meal);
                                model.entityManager.getTransaction().commit();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }

                    } else {
                        // No selection message
                        JOptionPane.showMessageDialog(
                                null,
                                "Παρακαλώ επιλέξτε πρώτα ένα γεύμα που να υπάρχει στη ΒΔ.",
                                "Ενημέρωση",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
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
