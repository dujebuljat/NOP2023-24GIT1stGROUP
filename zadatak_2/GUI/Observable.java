package com.dujebuljat.zadatak_2.GUI;

public interface Observable {

    void addObserver(Observer observer);
    void notifyObservers(FormEvent formEvent);
}
