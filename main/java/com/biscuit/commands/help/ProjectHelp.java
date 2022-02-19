package com.biscuit.commands.help;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class ProjectHelp extends UniversalHelp {

    @Override
    public void executeChild(V2_AsciiTable at) {
        // new code starts here
        JTable table = new JTable(new String[][]{
                {"show", "Show project information"},
                {"releases", "List all sprints"},
                {"sprints", "Create a new project"},
                {"user_stories", "List all user stories"},
                {"tasks", "list all tasks"},
                {"plan", "Show plan in short form as a tree"},
                {"plan details", "Show plan in details in a table"},
                {"backlog", "List user stories in the backlog"},
                {"add release", "Add new release"},
                {"add sprint", "Add new sprint"},
                {"add user_story", "Add new user story to the backlog"}
        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);


        // new code ends here


    }

}
