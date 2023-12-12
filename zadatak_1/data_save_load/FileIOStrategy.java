package com.dujebuljat.zadatak_1.data_save_load;

public interface FileIOStrategy {
    void save(String data, String fileName);
    String load(String fileName);
}
