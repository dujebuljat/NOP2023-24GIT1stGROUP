package com.dujebuljat.zadatak_2.GUI;

import com.dujebuljat.zadatak_2.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TablePanel extends JPanel implements Observer{

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private Person person;

    public TablePanel(){
        initComps();
        layoutComps();
    }

    private void initComps(){
        Dimension dims = this.getPreferredSize();
        dims.width = 350;
        this.setPreferredSize(dims);
        model = new DefaultTableModel(new Object[]{"Height", "Weight", "Category", "BMI"}, 0);
        table = new JTable(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(87);
        table.getColumnModel().getColumn(1).setPreferredWidth(87);
        table.getColumnModel().getColumn(2).setPreferredWidth(87);
        table.getColumnModel().getColumn(3).setPreferredWidth(87);
        scrollPane = new JScrollPane(table);
    }

    private void layoutComps(){
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setTableModel(TableModel tableModel){
        table.setModel(tableModel);
    }

    @Override
    public void updateData(FormEvent event) {
        person = event.getPerson();

        if (person != null) {
            Object[] rowData = {person.getHeight(), person.getWeight(), person.getCategory(), person.getBmi()};
            model.addRow(rowData);
        } else {
            // Handle the case where the person object is null
            System.err.println("Person object is null in updateData method. - TablePanel");
        }
    }

    public void clearTable() {
        model.setRowCount(0);
    }

    public void addPersonToTable(Person person) {
        if (person != null) {
            Object[] rowData = {person.getHeight(), person.getWeight(), person.getCategory(), person.getBmi()};
            model.addRow(rowData);
        } else {
            // Handle the case where the person object is null
            System.err.println("Person object is null in addPersonToTable method. - TablePanel");
        }
    }
}
