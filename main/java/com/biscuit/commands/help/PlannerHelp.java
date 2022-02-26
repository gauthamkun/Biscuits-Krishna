package com.biscuit.commands.help;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class PlannerHelp extends UniversalHelp {

    @Override
    public void executeChild() {


        JTable table = new JTable(new String[][]{

                {"releases", "List all releases"},
                {"Sprints", "List all sprints"},
                {"user_stories", "List all user stories"},
                {"backlog", "List all user stories in a backlog"},
                {"plan", "Show plan in short form as a tree"},
                {"plan details", "Show plan in details in a table"},
                {"backlog", "List user stories in the backlog"},
                {"add release", "Add new release"},
                {"add sprint", "Add new sprint"},
                {"add user_story", "Add new user story to the backlog"},
                {"show releases", "Similar to releases, list all releases"},
                {"show sprints", "Similar to sprints, list all sprints"},
                {"show user_stories", "Similar to user_stories, list all user stories"},
                {"show backlog", "Similar to backlog, list user stories in the backlog"},
                {"show plan", "Similar to plan, show plan in short form as a tree"},
                {"show plan details", "Similar to plan details, show plan in details in a table"},
                {"move user_story# to sprint#", "Move (or plan) user story to sprint where user_story# is a user story name and sprint# is a sprint name\n"},
                {"unplan sprint#", "Unplan sprint where sprint# is a sprint name\n" + "use TAB to autocomplete sprint names\n"},
                {"unplan user_story#", "Unplan user story and move it back to the backlog where user_story# is a user story name\n" + "use TAB to autocomplete user story names\n"},
                {"unplan all", "Unplan all user stories and sprints"},
                {"back", "Go back to previous view (Project)"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);


    }

}
