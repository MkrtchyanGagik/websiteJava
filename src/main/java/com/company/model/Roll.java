package com.company.model;

public class Roll {
    private int id;
    private String roll;

    public int getId() {
        return id;
    }

    public String getRoll() {
        return roll;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "Roll{" +
                "id=" + id +
                ", roll='" + roll + '\'' +
                '}';
    }
}
