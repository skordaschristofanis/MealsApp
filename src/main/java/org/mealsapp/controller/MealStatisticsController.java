package org.mealsapp.controller;

import org.mealsapp.model.MainModel;
import org.mealsapp.view.MealStatisticsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MealStatisticsController {

    private final MealStatisticsView view;
    private final MainModel model;

    public MealStatisticsController(MealStatisticsView view, MainModel model) {
        this.view = view;
        this.model = model;

        this.view.btnRefreshTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queryMealDB();
            }
        });
    }

    public void queryMealDB() {
        // Get meals shorted by descending order of views
        List<Object[]> queryResults = model.getQueryResult();
        System.out.println(queryResults);
    }
}
