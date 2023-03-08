package org.mealsapp.controller;

import org.mealsapp.model.MainModel;
import org.mealsapp.view.MainView;

import jakarta.persistence.EntityManagerFactory;

public class MainController {

    private final MainView mainView;
    private final MainModel mainModel;

    public MainController() {

        this.mainView = new MainView();
        this.mainModel = new MainModel();

        // Controllers
        MealJpaController mealJpaController = new MealJpaController(mainModel);
        MealDataController mealDataController = new MealDataController(mainView.mealDataView, mainModel);
        MealCategoriesController mealCategoriesController = new MealCategoriesController(
                mainView.mealCategoriesView, mainModel
        );
        MealStatisticsController mealStatisticsController = new MealStatisticsController(
                mainView.mealStatisticsView, mainModel
        );
    }

    public void RunApplication() {

        mainView.displayWindow(mainView);
    }

}
