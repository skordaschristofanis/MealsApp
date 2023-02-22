package org.mealsapp.controller;

import org.mealsapp.model.MainModel;
import org.mealsapp.view.MainView;

public class MainController {

    MainView mainView;
    MainModel mainModel;

    public MainController() {

        this.mainView = new MainView();
        this.mainModel = new MainModel();

    }

    public void RunApplication() {
        mainView.displayWindow();
    }

}
