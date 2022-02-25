package com.biscuit.commands.help;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class ReleasesHelp extends UniversalHelp {
    public void executeChild() {
        JTable table = new JTable(new String[][]{
                {"releases", "List all releases"},
                {"back", "Go back to previous view (Project)"},
                {"go_to release", "Go to a release view (followed by a release name)"},
                {"add release", "Add new release"}
        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);
    }
}
