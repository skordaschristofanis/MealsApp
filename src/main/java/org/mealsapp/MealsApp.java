package org.mealsapp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.mealsapp.controller.MainController;
import org.mealsapp.transport.MealCategoryDto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class MealsApp {

  public static void main(String[] args) {
    // Application start point
    new MainController().RunApplication();
    createMealTable();

    //System.out.println(getMealByName("saganaki").toString());
    //System.out.println(getMealByCategory());


  }

  /**
   * CREATE TABLE Meal (idMeal integer NOT NULL, strMeal
   * varchar(255), strCategory varchar(255), strArea
   * varchar(255), strInstructions varchar(2000), status
   * integer, PRIMARY KEY (idMeal));
   *
   * @return
   */

  private static Connection connect() {
    String url = "jdbc:derby:MealsApp;create=true";
    try (Connection connection = DriverManager.getConnection(url)) {
      System.out.println("Database created!");
      return connection;
    } catch (SQLException ex) {
      System.out.println("Failed to create database! " + ex.getMessage());
      return null;
    }
  }

  private static void createMealTable() {
    try(Connection connection = connect()) {
      String createTableSQL = """
              CREATE TABLE Meal (
              meal_id integer NOT NULL, 
              str_meal varchar(255), 
              str_category varchar(255), 
              str_area varchar(255), 
              str_instructions varchar(2000), 
              status integer, 
              PRIMARY KEY (meal_id));
              """;
      Objects.requireNonNull(connection, "Connection to database cannot be null, but it is!");
      Statement statement = connection.createStatement();
      statement.executeUpdate(createTableSQL);
      statement.close();
      System.out.println("Done!");
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  /**
   * Στην οθόνη προβολής λίστας γευμάτων ανά κατηγορία γεύματος, ο χρήστης έχει τη
   * δυνατότητα να επιλέξει από μια λίστα που περιέχει τις διαθέσιμες κατηγορίες γευμάτων μια κατηγορία που τον ενδιαφέρει.
   * Με την επιλογή της κατηγορίας εμφανίζονται τα διαθέσιμα γεύματά της. Για κάθε γεύμα που υπάρχει στην επιλεγμένη
   * από τον χρήση κατηγορία, μας ενδιαφέρει να εμφανίζονται μόνο τα ονόματα των γευμάτων και όχι άλλη πληροφορία.
   * (Αν κάποιος χρήστης ενδιαφέρεται περαιτέρω για ένα γεύμα, μπορεί απλά να το αναζητήσει βάσει ονόματος,
   * κάνοντας χρήση της προηγούμενης λειτουργίας)
   *
   * @return
   */
  public static MealCategoryDto getMealByCategory() {
    String urlToCall = "https://www.themealdb.com/api/json/v1/1/categories.php";

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder().url(urlToCall).build();

    try (Response response = client.newCall(request).execute()) {
      if (response.isSuccessful() && response.body() != null) {
        Gson gson = new Gson();
        String responseBody = response.body().string();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        JsonArray mealsArray = jsonObject.getAsJsonArray("categories");
        if (mealsArray != null && mealsArray.size() > 0) {
          JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
          return new MealCategoryDto(mealObject.get("strCategory").getAsString());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return null;
  }


}



