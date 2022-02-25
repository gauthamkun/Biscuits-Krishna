package com.biscuit.commands.help;

import com.biscuit.models.enums.Status;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class ReleaseHelp extends UniversalHelp {

    @Override
    public void executeChild() {
        JTable table = new JTable(new String[][]{
                {"show", "Show release information"},
                {"edit", "Edit release"},
                {"change_status_to", "Change status of release (use TAB to autocomplete)\n"},
                {"sprints", "List sprints planned in this release"},
                {"list sprints", "List sprints planned in this release\n" + "Optional: (filter) to filter out the results (ex. list sprints filter a_string)\n"},
                {"back", "Go back to previous view"},
                {"go_to sprint", "Go to a sprint view (followed by a sprint name)"}
        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);
    }
}
