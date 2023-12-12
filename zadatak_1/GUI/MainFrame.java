package com.dujebuljat.zadatak_1.GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame{

    private ViewPanel viewPanel;
    private Calculation calculation;
    private MenuBar menuBar;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CalculatorApp");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        initComps();
        layoutComps();
    }

    private void initComps() {
        viewPanel = new ViewPanel();
        menuBar = new MenuBar(calculation, viewPanel);
        calculation = new Calculation(viewPanel);

//        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
        add(calculation, BorderLayout.SOUTH);
    }
}
