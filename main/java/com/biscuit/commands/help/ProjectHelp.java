package com.biscuit.commands.help;

import com.biscuit.views.View;
import de.vandermeer.asciitable.v2.V2_AsciiTable;

import javax.swing.*;
import java.awt.*;

public class ProjectHelp extends UniversalHelp {

	@Override
	public void executeChild(V2_AsciiTable at) {



		// new code starts here

		View.console.setLayout(new GridLayout(25,2));

		JLabel label1= new JLabel("                          Project Commands\n                          " );
		JLabel label2= new JLabel("\"show\", \"Show project information\n" );
		JLabel label3= new JLabel("\"releases\", \"List all releases\n" );
		JLabel label4= new JLabel("\"sprints\", \"List all sprints\n" );
		JLabel label5= new JLabel("user_stories\", \"List all user stories\n" );
		JLabel label6= new JLabel("remove project    Remove or delete project (followed by a project name)\n" );
		JLabel label7= new JLabel("user_stories\", \"List all user stories\n" );
		JLabel label8= new JLabel("tasks\", \"List all tasks\n" );
		JLabel label9= new JLabel("plan\", \"Show plan in short form as a tree\n" );
		JLabel label10= new JLabel("plan details\", \"Show plan in details in a table\n" );
		JLabel label11= new JLabel("backlog\", \"List user stories in the backlog\n" );
		JLabel label21= new JLabel("add release\", \"Add new release\n" );
		JLabel label22= new JLabel("add sprint\", \"Add new sprint\n" );
		JLabel label23= new JLabel("add user_story\", \"Add new user story to the backlog\n" );


		View.console.add(label1);
		View.console.add(label2);
		View.console.add(label3);
		View.console.add(label4);
		View.console.add(label5);
		View.console.add(label6);
		View.console.add(label7);
		View.console.add(label8);
		View.console.add(label9);
		View.console.add(label10);
		View.console.add(label11);
		View.console.add(label21);
		View.console.add(label22);
		View.console.add(label23);


		View.console.repaint();
		View.mainFrame.repaint();
		View.mainFrame.setVisible(true);

		// new code ends here


	}

}
