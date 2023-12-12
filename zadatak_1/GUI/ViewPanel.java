package com.dujebuljat.zadatak_1.GUI;
import com.dujebuljat.zadatak_1.data_save_load.CalcData;
import com.dujebuljat.zadatak_1.data_save_load.FileIOContext;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    public ViewPanel() {
        initComps();
        layoutComps();
    }

    private void initComps() {
        Dimension dims = this.getPreferredSize();
        dims.height = 300;
        this.setPreferredSize(dims);
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void layoutComps() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setTextOnTextArea(String someText) {
        textArea.append(someText + "\n");
    }

    public void clearAll() {
        textArea.setText("");
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void save(FileIOContext fileIOContext, String fileName) {
        String data = textArea.getText();
        fileIOContext.save(data, fileName);
    }

    public void load(FileIOContext fileIOContext, String fileName) {
        String loadedData = fileIOContext.load(fileName);
        textArea.setText(loadedData);
    }
}

