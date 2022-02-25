package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class TaskHelp extends UniversalHelp {


    public void executeChild() {


        JTable table = new JTable(new String[][]{

                {"show", "Show task information"},
                {"edit", "Edit task"},
                {"change_status_to", "Change status of task (use TAB to autocomplete)\n" + "Status: "},
                {"back", "Go back to previous view"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);


    }

}
