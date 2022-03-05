/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {

	public String name;
	public String description;
	public Backlog backlog = new Backlog();
	public Epic epic = new Epic();
	public List<Release> releases = new ArrayList<>();
	public List<Sprint> sprints = new ArrayList<>();
	public List<Wiki> wiki = new ArrayList<>();
    public Members members;


    public void save() {
		ModelHelper.save(this, name);
	}


	public void delete() {
		ModelHelper.delete(name);
	}


	static public Project load(String name) {
		return ModelHelper.loadProject(name);
	}


	public void updateChildrenReferences() {

		this.backlog.project = this;
		this.epic.project = this;

		for (Release r : releases) {
			r.project = this;
			updateSprintReferences(r.sprints);
		}

		updateSprintReferences(sprints);
		updateMembers((List<Members>) members);
		updateUserStoryReferences(backlog.userStories);
		updateUserStoryReferences(epic.userStories);
	}


	private void updateSprintReferences(List<Sprint> sprints) {
		for (Sprint s : sprints) {
			s.project = this;
			updateUserStoryReferences(s.userStories);
		}
	}
	private void updateMembers(List<Members> members) {
		for (Members s : members) {
			s.project = this;
		}
	}


	private void updateUserStoryReferences(List<UserStory> userStories) {
		for (UserStory us : userStories) {
			us.project = this;

			for (Task t : us.tasks) {
				t.project = this;
			}
		}
	}


	public void addRelease(Release r) {
		releases.add(r);
	}


	public void addSprint(Sprint s) {
		sprints.add(s);
	}


	@Override
	public String toString() {
		return "project name: " + name + "\n\ndescription:-\n" + description;
	}

}
