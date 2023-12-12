package com.dujebuljat.zadatak_1.data_save_load;

import javax.swing.*;
import java.io.*;

public class BinaryFileIOStrategy implements FileIOStrategy{
    @Override
    public void save(String data, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving data to file: " + fileName, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public String load(String fileName) {
        StringBuilder loadedData = new StringBuilder();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof String) {
                loadedData.append((String) obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading data from file: " + fileName, "Message", JOptionPane.INFORMATION_MESSAGE);
        }

        return loadedData.toString();
    }
}
