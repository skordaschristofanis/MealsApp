package org.mealsapp.controller;

import org.mealsapp.model.MainModel;
import org.mealsapp.view.MainView;

public class MainController {

    private final MainView mainView;
    private final MainModel mainModel;

    public MainController() {

        this.mainView = new MainView();
        this.mainModel = new MainModel();

        MealDataController mealDataController = new MealDataController(mainView.mealDataView, mainModel);
        MealCategoriesController mealCategoriesController = new MealCategoriesController(
                mainView.mealCategoriesView, mainModel
        );
    }

    public void RunApplication() {

        mainView.displayWindow(mainView);
    }

}
