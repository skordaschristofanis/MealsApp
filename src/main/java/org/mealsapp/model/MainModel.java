package org.mealsapp.model;

public class MainModel {

    private final String baseURI;

    public MainModel() {
        //constructor
        //Set the baseURI value
        baseURI = "https://www.themealdb.com/api/json/v1/1/";
    }

    public String getMealURI() {
        // Property for mealURI
        return baseURI + "search.php?s=";
    }

    public String getCategoryURI() {
        // Property for categoryURI
        return baseURI + "filter.php?c=";
    }
}
