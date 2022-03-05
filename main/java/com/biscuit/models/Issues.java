/*
	Author: Hamad Al Marri;
 */

package com.biscuit.models;

import com.biscuit.models.enums.*;

import java.util.Date;
import java.util.List;

public class Issues {

	public transient Project project;

	public String title;
	public String description;
	public String teamMember;
	public IssuePriority issuePriority;
	public IssueSeverity issueSeverity;
	public IssueType issueType;
	public Status state;
	public Date initiatedDate = null;
	public Date dueDate = null;
	public float estimatedTime;

	public static String[] fields;

	List<Bug> bugs;
	List<Test> tests;

	static {
		fields = new String[10];
		fields[0] = "title";
		fields[1] = "description";
		fields[2] = "team_member";
		fields[3] = "state";
		fields[4] = "issue_priority";
		fields[5] = "issue_severity";
		fields[6] = "issue_type";
		fields[7] = "initiated_date";
		fields[8] = "estimatedTime";
		fields[9] = "due_date";
	}


	public void save() {
		project.save();
	}
}
