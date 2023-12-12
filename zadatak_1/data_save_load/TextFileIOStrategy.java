package com.dujebuljat.zadatak_1.data_save_load;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileIOStrategy implements FileIOStrategy{
    @Override
    public void save(String data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (IOException e) {
//            JOPtionPane.showMessageDialog(null, "Error saving data to file: " + fileName);
            JOptionPane.showMessageDialog(null, "Error saving data to file: " + fileName, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public String load(String fileName) {
        StringBuilder loadedData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedData.append(line).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading data from file: " + fileName, "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return loadedData.toString();
    }
}
