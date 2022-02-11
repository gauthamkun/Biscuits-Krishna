package com.biscuit.models;

import java.util.ArrayList;
import java.util.List;

public class Epic {
    public transient Project project;
    public List<UserStory> userStories = new ArrayList<UserStory>();


    public void addUserStory(UserStory userStory) {
        this.userStories.add(userStory);

    }


    public void save() {
        project.save();
    }
}
