package com.dujebuljat.zadatak_1.GUI;

import com.dujebuljat.zadatak_1.AdditionCalculate;
import com.dujebuljat.zadatak_1.SubtractionCalculate;
import com.dujebuljat.zadatak_1.MultiplicationCalculate;
import com.dujebuljat.zadatak_1.DivisionCalculate;
import com.dujebuljat.zadatak_1.PowerToCalculate;
import com.dujebuljat.zadatak_1.data_save_load.CalcData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculation extends JPanel {

    private JLabel firstNumberLabel;
    private JLabel secondNumberLabel;
    private JTextField firstNumberTextField;
    private JTextField secondNumberTextField;
    private JTextField resultTextField;
    private JList operationsList;
    private DefaultListModel listModel;
    private JScrollPane listScrollPane;
    private JButton calculateButton;
    private ViewPanel viewPanel;
    private CalcData calcData;

    public Calculation(ViewPanel viewPanel) {
        this.viewPanel = viewPanel;
        initComps();
        layoutComps();
        activateComps();
    }

    private void initComps() {
        Dimension dims = this.getPreferredSize();
        dims.height = 300;
        this.setPreferredSize(dims);

        firstNumberLabel = new JLabel("First number: ");
        secondNumberLabel = new JLabel("Second number: ");
        firstNumberTextField = new JTextField(10);
        secondNumberTextField = new JTextField(10);
        resultTextField = new JTextField(10);
        resultTextField.setEditable(false);

        listModel = new DefaultListModel();
        listModel.addElement("AdditionCalculation");
        listModel.addElement("SubtractionCalculation");
        listModel.addElement("MultiplicationCalculation");
        listModel.addElement("DivisionCalculation");
        listModel.addElement("PowerToCalculation");

        operationsList = new JList(listModel);
        operationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        operationsList.setSelectedIndex(0);
        operationsList.setVisibleRowCount(3);
        listScrollPane = new JScrollPane(operationsList);
        calculateButton = new JButton("Calculate");

        Border innerBorder = BorderFactory.createTitledBorder("Calculation");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    }

    private void layoutComps() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(firstNumberLabel, gbc);

        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(firstNumberTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(secondNumberLabel, gbc);

        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(secondNumberTextField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(listScrollPane, gbc);

        gbc.gridx += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resultTextField, gbc);

        gbc.gridy += 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);


    }

    private void activateComps() {
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double firstNumber = Double.parseDouble(firstNumberTextField.getText());
                    double secondNumber = Double.parseDouble(secondNumberTextField.getText());
                    String operation = (String) operationsList.getSelectedValue();

                    if (Double.isNaN(firstNumber) || Double.isNaN(secondNumber)) {
                        JOptionPane.showMessageDialog(Calculation.this, "Invalid input. Please enter valid numbers.");
                        return;
                    }

                    if (operation.equals("DivisionCalculation") && secondNumber == 0) {
                        JOptionPane.showMessageDialog(Calculation.this, "Cannot divide by zero.");
                        return;
                    }

                    switch (operation) {
                        case "AdditionCalculation":
                            AdditionCalculate additionCalculation = new AdditionCalculate();
                            resultTextField.setText(String.valueOf(additionCalculation.calculate(firstNumber, secondNumber)));
                            viewPanel.setTextOnTextArea(new CalcData(firstNumber, secondNumber, operation, resultTextField.getText()).toString());
                            break;
                        case "SubtractionCalculation":
                            SubtractionCalculate subtractionCalculation = new SubtractionCalculate();
                            resultTextField.setText(String.valueOf(subtractionCalculation.calculate(firstNumber, secondNumber)));
                            viewPanel.setTextOnTextArea(new CalcData(firstNumber, secondNumber, operation, resultTextField.getText()).toString());
                            break;
                        case "MultiplicationCalculation":
                            MultiplicationCalculate multiplicationCalculation = new MultiplicationCalculate();
                            resultTextField.setText(String.valueOf(multiplicationCalculation.calculate(firstNumber, secondNumber)));
                            viewPanel.setTextOnTextArea(new CalcData(firstNumber, secondNumber, operation, resultTextField.getText()).toString());
                            break;
                        case "DivisionCalculation":
                            DivisionCalculate divisionCalculation = new DivisionCalculate();
                            resultTextField.setText(String.valueOf(divisionCalculation.calculate(firstNumber, secondNumber)));
                            viewPanel.setTextOnTextArea(new CalcData(firstNumber, secondNumber, operation, resultTextField.getText()).toString());
                            break;
                        case "PowerToCalculation":
                            PowerToCalculate powerToCalculation = new PowerToCalculate();
                            resultTextField.setText(String.valueOf(powerToCalculation.calculate(firstNumber, (int)secondNumber)));
                            viewPanel.setTextOnTextArea(new CalcData(firstNumber, secondNumber, operation, resultTextField.getText()).toString());
                            break;
                        }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Calculation.this, "Invalid input. Please enter valid numbers.");
                    }
            }
        });
    }
}
