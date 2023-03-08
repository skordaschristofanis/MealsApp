package org.mealsapp.model;

import jakarta.persistence.*;
import java.util.List;

public class MainModel {

    private final String baseURI;
    private final EntityManagerFactory entityManagerFactory;
    public EntityManager entityManager;

    public MainModel() {
        //constructor
        //Set the baseURI value
        baseURI = "https://www.themealdb.com/api/json/v1/1/";

        // Entity Manager
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public String getMealURI() {
        // Property for mealURI
        return baseURI + "search.php?s=";
    }

    public String getCategoryURI() {
        // Property for categoryURI
        return baseURI + "filter.php?c=";
    }

    public List<Object[]> getQueryResult() {
        Query query = entityManager.createNativeQuery("SELECT  * FROM MEAL ORDER BY STATUS DESC");
        List<Object[]> list = query.getResultList();
        return list;
    }

    public Integer getNextId() {
        Query query = entityManager.createNativeQuery("SELECT MAX(IDMEAL) FROM MEAL");
        Object nextId = query.getSingleResult();
        Integer id = null;
        // Check for null
        if (nextId == null) {
           id = 1;
        }
        else {
            id = ((Number) nextId).intValue() + 1;
        }
        return id;
    }

}
