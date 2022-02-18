/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.DashboardHelp;
import com.biscuit.commands.project.AddProject;
import com.biscuit.commands.project.EditProject;
import com.biscuit.commands.project.RemoveProject;
import com.biscuit.factories.DashboardCompleterFactory;
import com.biscuit.models.Project;
import com.biscuit.models.services.Finder.Projects;

import jline.console.completer.Completer;

import javax.swing.*;

public class DashboardView extends View implements ActionListener {
	String dest;
	JTextField destination  = new JTextField(20);
	Project p;
	private boolean flag;

	public DashboardView() {
		super(null, "Dashboard");
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(DashboardCompleterFactory.getDashboardCompleters());
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {
  // System.out.println("The len is " + words.length + " and the word is "  + words[0]) ;
		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			boolean result =  execute2Keyword(words);
			System.out.println("result is " + result);
			words = new String[words.length];
			return result;
		} else if (words.length == 3) {
			return execute3Keyword(words);
		}

		return false;
	}


	private boolean execute3Keyword(String[] words) throws IOException {
		if (words[0].equals("go_to")) {
			// "project#1", "users", "contacts", "groups"

			if (words[1].equals("project")) {
				// check if project name
				Project p = Projects.getProject(words[2]);
				//System.out.println("reached here at this point");
				if (p != null) {
					ProjectView pv = new ProjectView(this, p);
					pv.view();
					return true;
				}
				return false;
			}
		} else if (words[1].equals("project")) {
			if (words[0].equals("edit")) {
				Project p = Projects.getProject(words[2]);
				if (p != null) {
					(new EditProject(reader, p)).execute();
					resetCompleters();
					return true;
				}
				return false;
			} else if (words[0].equals("remove")) {
				Project p = Projects.getProject(words[2]);
				if (p != null) {
					(new RemoveProject(reader, p)).execute();
					resetCompleters();
					return true;
				}
				return false;
			}
		}

		return false;
	}


	private boolean execute2Keyword(String[] words) throws IOException {
		if (words[0].equals("go_to")) {
			// "project#1", "users", "contacts", "groups"

			// check if project name

			View.panel.add(destination);
			View.panel.repaint();
			View.mainFrame.repaint();
		//	View.mainFrame.pack();
			View.mainFrame.setVisible(true);
			destination.addActionListener(this);


//

		//	System.out.println("reached here");


			try {
				Thread.sleep(10000) ;
				if (p != null) {
					ProjectView pv = new ProjectView(this, p);
					//System.out.println("Project view loading");
					return true;

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			return false;


		} else if (words[0].equals("list")) {
			// projects
			// "filter", "sort"
		} else if (words[1].equals("project")) {
			if (words[0].equals("add")) {
				(new AddProject(reader)).execute();
				//resetCompleters();
				return true;
			}
		}
		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		System.out.println("Is a single word");
		if (words[0].equals("summary")) {
		} else if (words[0].equals("projects")) {
		} else if (words[0].equals("alerts")) {
		} else if (words[0].equals("check_alert")) {
		} else if (words[0].equals("search")) {
		} else if (words[0].equals("help")) {
			return (new DashboardHelp().execute());
		}

		return false;
	}

	public void actionPerformed(ActionEvent e) {



		new Thread(() -> {

			super.actionPerformed(e);
			if (!e.getSource().getClass().toString().equals("class javax.swing.JButton")) {
				//System.out.println("The command is  " + e.getActionCommand());
				p = Projects.getProject(e.getActionCommand());
				if (p != null) {
					//System.out.println("project exists");
				}
				else {
					View.console.add(new JLabel("Project does not exist!!"));
					View.console.repaint();
					View.console.setVisible(true);
					View.mainFrame.repaint();
					View.mainFrame.pack();
					View.mainFrame.setVisible(true);
				}
			}

		}).start();

	}
}
