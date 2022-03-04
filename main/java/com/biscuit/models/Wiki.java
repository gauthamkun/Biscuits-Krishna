package com.biscuit.models;

public class Wiki {
    public transient Project project;

    public String title;
    public String description;


    public void save() {
        project.save();
    }
}
