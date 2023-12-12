package com.dujebuljat.zadatak_2.GUI;

import com.dujebuljat.zadatak_2.Person;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel implements Observer{

    private JTextArea txtArea;
    private JScrollPane scrollPane;
    private Person person;

    public ViewPanel(){
        initComps();
        layoutComps();
    }

    private void initComps(){
        Dimension dims = this.getPreferredSize();
        dims.width = 275;
        this.setPreferredSize(dims);
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(txtArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void layoutComps(){
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void appendText(String text){
        txtArea.append(text);
    }

    public void clearText(){
        txtArea.setText(null);
    }

    @Override
    public void updateData(FormEvent event) {
        // Update viewPanel based on FormEvent
        person = event.getPerson();

        if (person != null) {
            appendText(person.toString() + "\n");
        } else {
            // Handle the case where the person object is null
            System.err.println("Person object is null in updateData method. - ViewPanel");
        }
    }

}
