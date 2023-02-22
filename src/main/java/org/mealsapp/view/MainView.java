package org.mealsapp.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

    private JPanel pnlMain;
    private JButton btnShowMealData;
    private JButton btnShowMealList;
    private JButton btnShowMealStatsAndSaveToPDF;
    private JButton btnExit;

    public MainView() {

        // Exit application
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
    }

    public void displayWindow() {
        // The main application window
        MainView mainView = new MainView();

        // Set the content pane
        mainView.setContentPane(mainView.pnlMain);

        // Set the application title
        mainView.setTitle("MealsApp");

        // Set the geometry
        mainView.setSize(550, 200);

        // Set visibility
        mainView.setVisible(true);

        // Set close operation
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
