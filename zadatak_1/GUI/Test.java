package com.dujebuljat.zadatak_1.GUI;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
