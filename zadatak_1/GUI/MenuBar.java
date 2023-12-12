package com.dujebuljat.zadatak_1.GUI;
import com.dujebuljat.zadatak_1.data_save_load.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuBar extends JMenuBar {

    private MainFrame main;
    private Calculation calculation;
    private ViewPanel viewPanel;

    private JMenu fileMenu = new JMenu("File");
    private JMenuItem save = new JMenuItem("Save");
    private JMenuItem open = new JMenuItem("Load");
    private JMenuItem exit = new JMenuItem("Exit");

    private final JFileChooser fileChooser = new JFileChooser();

    private static final String DIR = "DATA";


    public MenuBar(Calculation calculation, ViewPanel viewPanel) {
        this.calculation = calculation;
        this.viewPanel = viewPanel;

        createMenuBar();
        activateMenuBar();

    }

    public void createMenuBar() {

        fileMenu.add(save);
        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        add(fileMenu);


        fileChooser.setCurrentDirectory(new File(DIR));
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
                "TXT files", "txt");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                "BIN files", "bin");
        fileChooser.setFileFilter(filter1);
        fileChooser.addChoosableFileFilter(filter2);
        alignSaveWithExtensions();

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

    public void activateMenuBar() {

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int value = fileChooser.showSaveDialog(null);
                if (value == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getPath();
                    FileIOContext fileIOContext = new FileIOContext();

                    if (path.endsWith(".bin")) {
                        fileIOContext.setStrategy(new BinaryFileIOStrategy());
                    } else {
                        fileIOContext.setStrategy(new TextFileIOStrategy());
                    }

                    viewPanel.save(fileIOContext, path);
                }


//                String[] fileNameAndType = getFileNameAndTypeFromUser();
//                if (fileNameAndType != null) {
//                    FileIOContext fileIOContext = new FileIOContext();
//
//                    if (fileNameAndType[1].equals(".bin")) {
//                        fileIOContext.setStrategy(new BinaryFileIOStrategy());
//                    } else {
//                        fileIOContext.setStrategy(new TextFileIOStrategy());
//                    }
//
//                    viewPanel.save(fileIOContext, fileNameAndType[0]);
//                }
            }
        });

        open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                viewPanel.setTextOnTextArea("");
//                StringBuffer sb;
                int value = fileChooser.showOpenDialog(null);
                if (value == JFileChooser.APPROVE_OPTION){
                    String path = fileChooser.getSelectedFile().getPath();
                    // read from file and then append to the viewPanel
                    FileIOContext fileIOContext = new FileIOContext();

                    if (path.endsWith(".bin")) {
                        fileIOContext.setStrategy(new BinaryFileIOStrategy());
                    } else {
                        fileIOContext.setStrategy(new TextFileIOStrategy());
                    }

                    viewPanel.load(fileIOContext, path);
                }


//                String[] fileNameAndType = getFileNameAndTypeFromUser();
//                if (fileNameAndType != null) {
//                    FileIOContext fileIOContext = new FileIOContext();
//
//                    if (fileNameAndType[1].equals(".bin")) {
//                        fileIOContext.setStrategy(new BinaryFileIOStrategy());
//                    } else {
//                        fileIOContext.setStrategy(new TextFileIOStrategy());
//                    }
//
//                    viewPanel.load(fileIOContext, fileNameAndType[0]);
//                }
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int val = JOptionPane.showConfirmDialog(main, "Really want to exit!?", "Exit confirmation",
                        JOptionPane.OK_CANCEL_OPTION);


                if(val == JOptionPane.OK_OPTION) {

                    System.exit(0);

                } else {
                    System.out.println("canceled by user...");

                    JOptionPane.showMessageDialog(main, "Canceled by user", "Message", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

    }

//    private String[] getFileNameAndTypeFromUser() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Choose a file");
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//        // Create a FileFilter for .bin files
//        FileFilter binFilter = new FileNameExtensionFilter("Binary Files (*.bin)", "bin");
//
//        // Create a FileFilter for .txt files
//        FileFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
//
//        // Add the filters to the file chooser
//        fileChooser.addChoosableFileFilter(binFilter);
//        fileChooser.addChoosableFileFilter(txtFilter);
//
//        int userSelection = fileChooser.showSaveDialog(null);
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            String fileName = selectedFile.getAbsolutePath();
//            String fileType = null;
//
//            // Determine the selected file filter
//            FileFilter selectedFilter = fileChooser.getFileFilter();
//            if (selectedFilter == binFilter) {
//                fileType = ".bin";
//            } else if (selectedFilter == txtFilter) {
//                fileType = ".txt";
//            }
//
//            return new String[]{fileName, fileType};
//        } else {
//            return null;
//        }
//    }

}

