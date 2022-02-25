package com.biscuit.commands.help;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class BacklogHelp extends UniversalHelp {

	@Override
	public void executeChild() {
		JTable table = new JTable(new String[][]{
				{"user_stories", "List all user stories"},
				{"List user_stories", "List all user stories\\n\" + \"Optional: (filter) to filter out the results"},
				{"sprints", "Create a new project"},
				{"back","Go back to previous view (Project)"},
				{"go_to user_story","Go to a user story view (followed by a user story name)"},
				{"add user_story","Add new user story to the backlog"}
		}, new String[]{"Name of the command", "Description of the command"});
		table.setBackground(Color.black);
		table.setForeground(Color.yellow);
		View.console.add(new JScrollPane(table), BorderLayout.CENTER);
		View.console.repaint();
		View.mainFrame.repaint();
		View.mainFrame.setVisible(true);
	}

}
