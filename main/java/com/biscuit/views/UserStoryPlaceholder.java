package com.biscuit.views;

import java.util.ArrayList;
import java.util.List;

public class UserStoryPlaceholder {
    String name;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTasksList() {
        return tasksList;
    }

    String description;
    List<String> tasksList;
    List<String> assignee;

    public UserStoryPlaceholder(String name, String description, List<String> tasksList) {
        this.name = name;
        this.description = description;
        this.tasksList = tasksList;
        assignee = new ArrayList<>();
    }

    public String toString() {
        return (name + assignee);
    }

}
