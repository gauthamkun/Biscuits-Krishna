package com.biscuit.commands.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Dashboard;

import com.biscuit.views.View;
import jline.console.ConsoleReader;

import javax.swing.*;

public class AddProject implements Command, ActionListener {

	Dashboard dashboard = Dashboard.getInstance();
	Project project = new Project();
	ConsoleReader reader = null;
	JTextField nameP  = new JTextField("Name");
    JTextArea area  = new JTextArea(20,20);
    JButton button  = new JButton("Submit");
	public AddProject(ConsoleReader reader) {
		super();
		this.reader = reader;
	}


	public boolean execute() throws IOException{

		//StringBuilder description = new StringBuilder();
		String line;
		String prompt = reader.getPrompt();

		project.backlog.project = project;

		//reader.setPrompt(ColorCodes.BLUE + "project name: " + ColorCodes.RESET);
		//project.name = reader.readLine();
		//reader.setPrompt(ColorCodes.BLUE + "\ndescription: " + ColorCodes.YELLOW + "\n(\\q to end writing)\n" + ColorCodes.RESET);

//		while ((line = reader.readLine()) != null) {
//			if (line.equals("\\q")) {
//				break;
//			}
//			description.append(line).append("\n");
//			reader.setPrompt("");
//		}

		View.console.add(new JLabel("Enter the project name: "));
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.setVisible(true);
		View.mainFrame.setVisible(true);


		View.console.add(nameP);
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.setVisible(true);
		nameP.addActionListener(this);
		View.mainFrame.setVisible(true);

		//project.description = description.toString();

	//	reader.setPrompt(prompt);

	//	dashboard.addProject(project);

	//	dashboard.save();
//		project.save();

//		reader.println();
//		reader.println(ColorCodes.GREEN + "Project \"" + project.name + "\" has been added!" + ColorCodes.RESET);

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	if(e.getSource().getClass().toString().equals("class javax.swing.JTextField")){
		project.name = e.getActionCommand();
		View.console.removeAll();
		View.console.repaint();
		View.mainFrame.repaint();


		View.console.add(new JLabel("Enter the project description: "));
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.setVisible(true);
		View.mainFrame.setVisible(true);

		View.console.add(area);
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.setVisible(true);
		View.mainFrame.setVisible(true);

		button.addActionListener(this);
		View.console.add(button);
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.setVisible(true);
		View.mainFrame.setVisible(true);
		//System.out.println(e.getActionCommand());
	}
	else{
		project.description =  new String(area.getText());
		dashboard.addProject(project);
		dashboard.save();
		project.save();
		View.console.removeAll();
		View.console.repaint();
		View.mainFrame.repaint();
		View.console.add(new JLabel("Project " + project.name + " has been added!"));
		View.console.repaint();
		View.mainFrame.repaint();
	}
	}
}
