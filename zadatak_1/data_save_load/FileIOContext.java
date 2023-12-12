package com.dujebuljat.zadatak_1.data_save_load;

public class FileIOContext {

    private FileIOStrategy strategy;

    public void setStrategy(FileIOStrategy strategy) {
        this.strategy = strategy;
    }

    public void save(String data, String fileName) {
        strategy.save(data, fileName);
    }

    public String load(String fileName) {
        return strategy.load(fileName);
    }
}
