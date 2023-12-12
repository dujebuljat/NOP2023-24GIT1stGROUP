package com.dujebuljat.zadatak_2.data_save_load_bmi;

import com.dujebuljat.zadatak_2.Person;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class BinFileStrategy implements SaveLoadStrategy<Person>{
    @Override
    public void saveTo(String path, ArrayList<Person> persons) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path), true))){
            for (Person person : persons){
                oos.writeObject(person);
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
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))){
            while (true){
                Person person = (Person) ois.readObject();
                persons.add(person);
                sb.append(person + "\n");
            }
        } catch (EOFException e){
            // end of file reached
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error loading from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return sb;
    }


}
