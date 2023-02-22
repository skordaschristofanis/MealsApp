package org.mealsapp.controller;

import org.mealsapp.model.MainModel;
import org.mealsapp.view.MainForm;

public class MainController {

    MainForm mainForm;
    MainModel mainModel;

    public MainController() {

        this.mainForm = new MainForm();
        this.mainModel = new MainModel();

    }

    public void RunApplication() {
        mainForm.displayWindow();
    }

}
