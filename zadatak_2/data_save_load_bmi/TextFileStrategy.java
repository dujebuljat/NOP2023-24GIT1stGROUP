package com.dujebuljat.zadatak_2.data_save_load_bmi;

import com.dujebuljat.zadatak_2.Person;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class TextFileStrategy implements SaveLoadStrategy<Person> {
    @Override
    public void saveTo(String path, ArrayList<Person> persons) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true))){
            for (Person person : persons){
                bw.write(person.toString());
                bw.newLine();
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error saving to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public StringBuffer loadFrom(String path, ArrayList<Person> persons) {
        // ensure that persons is empty
        persons.clear();
        StringBuffer sb = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
            String line;
            while ((line = br.readLine()) != null){
                persons.add(parsePersonFromString(line));
                sb.append(line + "\n");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error loading from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return sb;
    }

    private static Person parsePersonFromString(String personString){
        // drop Person string and parentheses {}
        String[] personData = personString.substring(personString.indexOf("{") + 1, personString.indexOf("}")).split(",");
        float height = Float.parseFloat(personData[0].split("=")[1]);
        float weight = Float.parseFloat(personData[1].split("=")[1]);
        String category = personData[2].split("=")[1];
        float bmi = Float.parseFloat(personData[3].split("=")[1]);
        return new Person(height, weight, category, bmi);
    }


}
