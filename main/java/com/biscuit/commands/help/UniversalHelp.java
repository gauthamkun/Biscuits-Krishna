package com.biscuit.commands.help;
import java.awt.*;
import java.io.IOException;
import com.biscuit.commands.Command;
import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import javax.swing.*;

public class UniversalHelp implements Command {

    @Override
    public boolean execute() throws IOException {
        executeChild();
        JTable table = new JTable(new String[][]{
                {"go_to", "Go to a project and open the project view (followed by a project name"},
                {"go_to project", "Similar to 'go_to', it goes to a project and open the project view (followed by a project name"},
                {"add project", "Create a new project  "},
                {"edit project", "Edit project (followed by a project name)"},
                {"remove project", "Remove or delete project (followed by a project name)"},
                {"clear", "Clear the terminal screen "},
                {"exit", "Exit/terminate the program  "},
                {"dashboard", "Go to dashboard "},
                {"go_to dashboard", "Go to dashboard"},
                {"help", "Show help"}

        }, new String[]{"Name of the command", "Description of the command"});
        table.setBackground(Color.black);
        table.setForeground(Color.yellow);
        JScrollPane scroller = new JScrollPane(table);
        View.console.add(scroller, BorderLayout.CENTER);
        View.console.repaint();
        View.mainFrame.repaint();
        View.mainFrame.setVisible(true);

        return true;
    }

    protected void executeChild() {
    }

}
