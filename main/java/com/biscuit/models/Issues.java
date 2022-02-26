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
	public IssuePriority issuePriority;
	public IssueSeverity issueSeverity;
	public IssueType issueType;
	public Date initiatedDate = null;
	public Date dueDate = null;
	public float estimatedTime;

	public static String[] fields;

	List<Bug> bugs;
	List<Test> tests;

	static {
		fields = new String[8];
		fields[0] = "title";
		fields[1] = "description";
		fields[2] = "issue_priority";
		fields[3] = "issue_severity";
		fields[4] = "issue_type";
		fields[5] = "initiated_date";
		fields[6] = "estimatedTime";
		fields[7] = "due_date";
	}


	public void save() {
		project.save();
	}
}
