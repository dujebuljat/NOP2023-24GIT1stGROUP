package com.dujebuljat.zadatak_2;

import com.dujebuljat.zadatak_2.GUI.MainFrame;

import javax.swing.*;

public class TestALL {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
