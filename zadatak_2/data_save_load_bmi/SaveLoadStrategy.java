package com.dujebuljat.zadatak_2.data_save_load_bmi;

import java.util.ArrayList;

public interface SaveLoadStrategy<T> {

    void saveTo(String path, ArrayList<T> persons);

    StringBuffer loadFrom(String path, ArrayList<T> persons);

    public static String fileExtension(String path){
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
