/*
	Author: Hamad Al Marri;
 */

package com.biscuit.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import com.biscuit.commands.epic.*;
import com.biscuit.commands.help.ProjectHelp;
import com.biscuit.commands.planner.ShowPlan;
import com.biscuit.commands.planner.ShowPlanDetails;
import com.biscuit.commands.project.ShowProject;
import com.biscuit.commands.release.AddRelease;
import com.biscuit.commands.release.ListReleases;
import com.biscuit.commands.sprint.AddSprint;
import com.biscuit.commands.sprint.ListSprints;
import com.biscuit.commands.task.ListTasks;
import com.biscuit.commands.userStory.AddUserStoryToBacklog;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.ProjectCompleterFactory;
import com.biscuit.models.*;
import com.biscuit.models.services.Finder.Releases;
import com.biscuit.models.services.Finder.Sprints;

import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

import javax.swing.*;

public class ProjectView extends View implements ActionListener {

	Project project;


	JMenuBar mb  = new JMenuBar();
	JMenu menu = new JMenu("Project Menu");

	public ProjectView(View previousView, Project p) {
		super(previousView, p.name);
		this.project = p;

		JMenuItem info = new JMenuItem("info");
		info.addActionListener(this);

		JMenuItem backlog = new JMenuItem("backlog");
		backlog.addActionListener(this);

		JMenuItem releases = new JMenuItem("releases");
		releases.addActionListener(this);

		JMenuItem user_stories = new JMenuItem("user_stories");
		user_stories.addActionListener(this);

		JMenuItem plan = new JMenuItem("plan");
		plan.addActionListener(this);

		JMenuItem tasks = new JMenuItem("tasks");
		tasks.addActionListener(this);



		JMenuItem show = new JMenuItem("show");

		show.addActionListener(this);

		JMenuItem project_help = new JMenuItem("project_help");
		project_help.addActionListener(this);

		menu.add(info);
		menu.add(backlog);
		menu.add(releases);
		menu.add(user_stories);
		menu.add(plan);
		menu.add(tasks);
		menu.add(show);
		menu.add(project_help);



		View.panel.add(mb);

		View.panel.repaint();
		View.mainFrame.repaint();

		View.panel.setVisible(true);
		View.mainFrame.setVisible(true);

		View.console.add(new JLabel("Your are in the project view"));
		View.console.repaint();
		View.mainFrame.repaint();
		//View.mainFrame.pack();
		View.mainFrame.setVisible(true);
	}


	@Override
	void addSpecificCompleters(List<Completer> completers) {
		completers.addAll(ProjectCompleterFactory.getProjectCompleters(project));
	}


	@Override
	boolean executeCommand(String[] words) throws IOException {

		if (words.length == 1) {
			return execute1Keyword(words);
		} else if (words.length == 2) {
			return execute2Keywords(words);
		} else if (words.length == 3) {
			return execute3Keywords(words);
		} else if (words.length == 4) {
			return execute4Keywords(words);
		}
		return false;
	}


	private boolean execute4Keywords(String[] words) throws IOException {
		if (words[0].equals("show")) {
			if (words[1].equals("backlog")) {
				if (words[2].equals("filter")) {
					View.console.removeAll();
					(new ListUserStories(project.backlog, "", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListUserStories(project.backlog, "", false, "", true, words[3])).execute();
					return true;
				}
			}
			else if(words[1].equals("epic")) {
				if (words[2].equals("filter")) {
					(new ListUserStories(project.epic, "", true, words[3], false, "")).execute();
					return true;
				} else if (words[2].equals("sort")) {
					(new ListUserStories(project.epic, "", false, "", true, words[3])).execute();
					return true;
				}

			}
		}
		return false;
	}


	private boolean execute3Keywords(String[] words) {
		if (words[0].equals("go_to")) {
			if (words[1].equals("release")) {
				if (Releases.getAllNames(project).contains(words[2])) {
					Release r = Releases.find(project, words[2]);
					if (r == null) {
						return false;
					}

					// r.project = project;

					ReleaseView rv = new ReleaseView(this, r);
					rv.view();
					return true;
				}
			} else if (words[1].equals("sprint")) {
				Sprint s = null;
				if (Sprints.getAllNames(project).contains(words[2])) {
					s = Sprints.find(project, words[2]);
					if (s == null) {
						return false;
					}

					// s.project = project;

					SprintView sv = new SprintView(this, s);
					sv.view();
					return true;
				}


				// s.project = project;

				assert s != null;
				SprintView sv = new SprintView(this, s);
				sv.view();
				return true;
			}
		} else if (words[1].equals("user_story")) {
			if (UserStories.getAllNames(project).contains(words[2])) {
				UserStory us = UserStories.find(project, words[2]);
				if (us == null) {
					return false;
				}

				UserStroryView usv = new UserStroryView(this, us);
				usv.view();
				return true;
			}
		}


		return false;
	}


	private boolean execute2Keywords(String[] words) throws IOException {
		if (words[0].equals("add")) {
			if (words[1].equals("user_story")) {
				(new AddUserStoryToBacklog(reader, project)).execute();
				return true;
			} else if (words[1].equals("Epic")) {
				(new AddEpicToBacklog(reader, project)).execute();
				return true;
			} else if (words[1].equals("release")) {
				(new AddRelease(reader, project)).execute();
				resetCompleters();
				return true;
			} else if (words[1].equals("member")){
				(new AddMember(reader, project)).execute();
				resetCompleters();
				return true;
		    }else if (words[1].equals("sprint")) {
				(new AddSprint(reader, project)).execute();
				resetCompleters();

				return true;
			}else if (words[1].equals("wiki")) {
				(new AddWiki(reader, project)).execute();
				resetCompleters();

				return true;
			}

		} else if (words[0].equals("go_to")) {
			if (words[1].equals("backlog")) {
				this.project.backlog.project = this.project;
				BacklogView bv = new BacklogView(this, this.project.backlog);
				bv.view();
				return true;
			} else if (words[1].equals("releases")) {

				ReleasesView rsv = new ReleasesView(this, project);
				rsv.view();

				return true;

			}
			else if (words[1].equals("Epic")) {

				EpicView ev = new EpicView(this, this.project.epic);
				ev.view();

				return true;

			}else if (words[1].equals("sprints")) {

				SprintsView ssv = new SprintsView(this, project);
				ssv.view();

				return true;
			} else if (words[1].equals("planner")) {

				PlannerView pv = new PlannerView(this, project);
				pv.view();

				return true;
			}
		} else if (words[0].equals("show")) {
			if (words[1].equals("backlog")) {
				(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
				return true;
			}
		} else if (words[0].equals("list")) {
			if (words[1].equals("releases")) {
				(new ListReleases(project, "Releases")).execute();
				return true;
			} else if (words[1].equals("wiki")) {
				(new Listwiki(project, "wiki")).execute();
				return true;
			}
			else if (words[1].equals("user_stories")) {
				(new ListUserStories(UserStories.getAll(project), "All User Stories")).execute();
				return true;
			}

		} else if (words[0].equals("plan")) {
			if (words[1].equals("details")) {
				(new ShowPlanDetails(project)).execute();
				return true;
			}
		}

		return false;
	}


	private boolean execute1Keyword(String[] words) throws IOException {
		if (words[0].equals("info")) {
			View.console.removeAll();
			JLabel description = new JLabel(project.toString());
			View.console.add(description);
			View.console.repaint();
			View.mainFrame.repaint();
			//	View.mainFrame.pack();
			View.mainFrame.setVisible(true);
			//System.out.println("THIS IS THE PLACE" + project.toString());
			//reader.println(project.toString());
			return true;
		} else if (words[0].equals("backlog")) {
			View.console.removeAll();
			(new ListUserStories(project.backlog, "Backlog (User Stories)")).execute();
			return true;
		} else if (words[0].equals("releases")) {
			View.console.removeAll();
			(new ListReleases(project, "Releases")).execute();
			return true;
		} else if (words[0].equals("sprints")) {
			View.console.removeAll();

			for (Release r : project.releases) {
				if (!r.sprints.isEmpty()) {
					(new ListSprints(r, "Release: " + r.name + " -> Sprints")).execute();
				}
			}

			(new ListSprints(project, "Unplanned Sprints")).execute();
			return true;
		} else if (words[0].equals("user_stories")) {
			View.console.removeAll();
			(new ListUserStories(UserStories.getAll(project), "All User Stories")).execute();
			return true;
		} else if (words[0].equals("plan")) {
			View.console.removeAll();
			(new ShowPlan(project)).execute();
			return true;
		} else if (words[0].equals("tasks")) {
			View.console.removeAll();
			List<UserStory> userStories = UserStories.getAll(project);
			for (UserStory us : userStories) {
				if (!us.tasks.isEmpty()) {
					(new ListTasks(us, "User Story: " + us.title)).execute();
				}
			}
			return true;
		} else if (words[0].equals("show")) {
			View.console.removeAll();
			return (new ShowProject(project).execute());
		} else if (words[0].equals("project_help")) {
			View.console.removeAll();
			return (new ProjectHelp().execute());
		}

		return false;
	}

	public void actionPerformed(ActionEvent e){
		super.actionPerformed(e);
		String command = e.getActionCommand();
		String[] words = command.split(" ");
		try {
			executeCommand(words);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}