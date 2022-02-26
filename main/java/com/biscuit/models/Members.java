package com.biscuit.models;
import com.biscuit.models.Project;
import com.biscuit.models.enums.Roles;

public class Members {
    public transient Project project;

    public static String title;
    public static String[] fields;
    public static Roles role;

    static {
        fields = new String[]{"name", "Role"};
    }
    public void save() {
        project.save();
    }
}
