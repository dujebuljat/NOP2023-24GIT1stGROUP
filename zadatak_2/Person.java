package com.dujebuljat.zadatak_2;

import java.io.Serializable;

public class Person implements Serializable {

    private float height;
    private float weight;
    private String category;
    private float bmi;


    public Person(float height, float weight, String category, float bmi) {
        this.height = height;
        this.weight = weight;
        this.category = category;
        this.bmi = bmi;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", weight=" + weight +
                ", category='" + category + '\'' +
                ", bmi=" + bmi +
                '}';
    }
}
