package org.mealsapp.model;

public class MainModel {

    private final String baseURI;

    public MainModel() {
        //constructor
        //Set the baseURI value
        baseURI = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    }

    public String getBaseURI() {
        // Property for baseURI
        return baseURI;
    }

}
