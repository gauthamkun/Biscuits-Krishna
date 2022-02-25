package com.biscuit.commands.help;
import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import javax.swing.*;
import java.awt.*;

public class SprintsHelp extends UniversalHelp {

    public void executeChild() {

        JTable table = new JTable(new String[][]{

                {"sprints", "List all sprints"},
                {"back", "Go back to previous view (Project)"},
                {"go_to sprint", "Go to a sprint view (followed by a sprint name)"},
                {"add sprint", "Add new sprint"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        View.console.add(new JScrollPane(table), BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);

    }

}
