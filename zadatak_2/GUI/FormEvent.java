package com.dujebuljat.zadatak_2.GUI;

import com.dujebuljat.zadatak_2.Person;

import java.util.EventObject;

public class FormEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FormEvent(Object source) {
        super(source);
    }

    private float height;
    private float weight;
    private String category;

    private Person person;

    public FormEvent(Object source, float height, float weight, String category) {
        super(source);
        this.height = height;
        this.weight = weight;
        this.category = category;
    }

    public FormEvent(Object source, Person person) {
        super(source);
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null in FormEvent constructor.");
        }
        this.person = person;
        this.height = person.getHeight();
        this.weight = person.getWeight();
        this.category = person.getCategory();
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "FormEvent{" +
                "height=" + height +
                ", weight=" + weight +
                ", category='" + category + '\'' +
                '}';
    }

    public Person getPerson() {
        return person;
    }
}
