package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class UserStoryHelp extends UniversalHelp {

    public void executeChild() {

        JTable table = new JTable(new String[][]{

                {"show", "Show user story information"},
                {"edit", "Edit user story"},
                {"change_status_to", "Change status of user story (use TAB to autocomplete)\n" + "Status: "},
                {"tasks", "List tasks planned to this user story"},
                {"back", "Go back to previous view"},
                {"go_to task", "Go to a task view (followed by a task name)"},
                {"add task", "Add new task to this user story"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);

    }

}
