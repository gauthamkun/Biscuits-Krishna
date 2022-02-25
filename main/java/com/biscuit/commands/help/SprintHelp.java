package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class SprintHelp extends UniversalHelp {

    public void executeChild() {
        System.out.println("Helper is called");

        JTable table = new JTable(new String[][]{

                {"show", "Show sprint information"},
                {"edit", "Edit sprint"},
                {"change_status_to", "Change status of sprint (use TAB to autocomplete)\n" + "Status: "},
                {"user_stories", "List user stories planned in this sprint"},
                {"List user stories planned in this sprint" ,"Optional: (filter) to filter out the results (ex. list user_stories filter a_string)\n"},
                {"back", "Go back to previous view"},
                {"go_to user_story", "Go to a user story view (followed by a user story name)"},
                {"add user_story", "Add new user story to this sprint"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);

    }
}
