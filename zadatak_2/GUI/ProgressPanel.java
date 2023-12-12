package com.dujebuljat.zadatak_2.GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ProgressPanel extends JPanel implements Observer{

    private JProgressBar progressBar;
    private TitledBorder innerBorder;

    public ProgressPanel(){
        initComps();
        layoutComps();
    }

    private void initComps(){
        Dimension dims = this.getPreferredSize();
        dims.width = 175;
        this.setPreferredSize(dims);
        innerBorder = BorderFactory.createTitledBorder("Progress");
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(150, 25));
        progressBar.setStringPainted(true);
    }

    private void layoutComps(){
        setBorder(innerBorder);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx=0;
        gbc.gridy=0;
        add(progressBar, gbc);
    }

    public void setProgressBar(int i) {
        progressBar.setValue(i);
    }

    @Override
    public void updateData(FormEvent event) {
        // Update progressPanel based on FormEvent
        progressBar.setValue(progressBar.getValue()+1);
    }
}
