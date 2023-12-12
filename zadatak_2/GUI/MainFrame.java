package com.dujebuljat.zadatak_2.GUI;

import com.dujebuljat.zadatak_2.Person;
import com.dujebuljat.zadatak_2.data_save_load_bmi.BinFileStrategy;
import com.dujebuljat.zadatak_2.data_save_load_bmi.SaveLoadStrategy;
import com.dujebuljat.zadatak_2.data_save_load_bmi.TextFileStrategy;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame implements Observable{

    private ViewPanel viewPanel;
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private ProgressPanel progressPanel;
    private Person person;

    private ToolBar toolBar;

    private final JFileChooser fileChooser = new JFileChooser();

    private static final String DIR = "DATA";

    private ArrayList<Observer> observers;
    private ArrayList<Person> persons = new ArrayList<>();



    public MainFrame(){
        super("BMI APP_V2.0");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        initComps();
        layoutComps();
        activateFrame();

    }

    private void initComps(){


        viewPanel = new ViewPanel();
        formPanel = new FormPanel();
        toolBar = new ToolBar();
        progressPanel = new ProgressPanel();
        tablePanel = new TablePanel();

        observers = new ArrayList<>();
        persons = new ArrayList<>();
        fileChooser.setCurrentDirectory(new File(DIR));
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
                "TXT files", "txt");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                "BIN files", "bin");
        fileChooser.setFileFilter(filter1);
        fileChooser.addChoosableFileFilter(filter2);
        alignSaveWithExtensions();

    }

    private void layoutComps(){
        setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.WEST);
        add(tablePanel, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.EAST);
        add(formPanel, BorderLayout.SOUTH);
        add(toolBar, BorderLayout.NORTH);
    }

    private void alignSaveWithExtensions(){
            fileChooser.addActionListener(ae -> {
                if (ae.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)){
                    String path = fileChooser.getSelectedFile().getPath();
                    if (fileChooser.getFileFilter().getDescription().equals("TXT files")){
                        if (!path.endsWith(".txt")){
                            path += ".txt";
                        }
                    } else if (fileChooser.getFileFilter().getDescription().equals("BIN files")){
                        if (!path.endsWith(".bin")){
                            path += ".bin";
                        }
                    }
                    fileChooser.setSelectedFile(new File(path));
                }
            });

    }


    private boolean dirExists(String path){
        return new File(path).exists();
    }



    private void activateFrame(){
        addObserver(viewPanel);
        addObserver(progressPanel);
        addObserver(tablePanel);


        formPanel.setFormPanelListener(new FormPanelListener() {
            @Override
            public void formEventOccurred(FormEvent fe) {

                addPerson(fe.getPerson());
            }
        });

        toolBar.setToolBarListener(new ToolBarListener() {
            @Override
            public void clearEventOccurred() {
                viewPanel.clearText();
                progressPanel.setProgressBar(0);
                tablePanel.clearTable();
                persons.clear();

            }

            @Override
            public void saveEventOccurred() {
                int value = fileChooser.showSaveDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    SaveLoadStrategy<Person> strategy;

                    if (path.endsWith(".bin")) {
                        strategy = new BinFileStrategy();
                    } else {
                        strategy = new TextFileStrategy();
                    }

                    strategy.saveTo(path, persons);
                }
            }


            @Override
            public void loadEventOccurred() {
                viewPanel.clearText();
                persons.clear();
                StringBuffer sb;
                int value = fileChooser.showOpenDialog(null);
                if (value == JFileChooser.APPROVE_OPTION){
                    String path = fileChooser.getSelectedFile().getPath();
                    // read from file and then append to the viewPanel
                    SaveLoadStrategy<Person> strategy;

                    if (path.endsWith(".bin")) {
                        strategy = new BinFileStrategy();
                    } else {
                        strategy = new TextFileStrategy();
                    }

                    sb = strategy.loadFrom(path, persons);
                    viewPanel.appendText(sb.toString());
                    System.out.println(persons);
                    updateTablePanel();
                    updateProgressPanel();
                }
            }

            @Override
            public void exitEventOccurred() {
                int value = JOptionPane.showConfirmDialog(null, "Hvala na koristenju aplikacije!", "Izlaz", JOptionPane.CANCEL_OPTION);
                if (value == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });


    }

    private void updateTablePanel() {
        tablePanel.clearTable(); // Clear existing data

        for (Person person : persons) {
            tablePanel.addPersonToTable(person);
        }
    }

    private void updateProgressPanel() {
        int progress = persons.size() * 20;
        progressPanel.setProgressBar(progress);
    }

    private void addPerson(Person person){
        if (persons.size() < 5) {
            persons.add(person);
            notifyObservers(new FormEvent(this, person));
            updateProgressBar();
        } else {
            JOptionPane.showMessageDialog(this, "MAX CAPACITY REACHED!", "Warning!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProgressBar() {
        progressPanel.setProgressBar(persons.size()*20);
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(FormEvent event){
        for (Observer observer : observers){
            observer.updateData(event);
        }
    }
}
