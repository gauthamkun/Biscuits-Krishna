package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Issues;
import com.biscuit.models.Task;
import com.biscuit.models.services.DateService;

import java.io.IOException;

public class ShowIssues implements Command {

	Issues t = null;


	public ShowIssues(Issues t) {
		super();
		this.t = t;
	}


	@Override
	public boolean execute() throws IOException {

		System.out.println(ColorCodes.BLUE + "title: " + ColorCodes.RESET + t.title);
		System.out.println(ColorCodes.BLUE + "description: ");
		System.out.println(ColorCodes.RESET + t.description);
		System.out.println(ColorCodes.BLUE + "team member: ");
		System.out.println(ColorCodes.RESET + t.teamMember);
		System.out.println(ColorCodes.BLUE + "initiated date: " + ColorCodes.RESET + DateService.getDateAsString(t.initiatedDate));
		System.out.println(ColorCodes.BLUE + "due date: " + ColorCodes.RESET + DateService.getDateAsString(t.dueDate));
		System.out.println(ColorCodes.BLUE + "estimated time: " + ColorCodes.RESET + t.estimatedTime);
		System.out.println(ColorCodes.BLUE + "issue severity: " + ColorCodes.RESET + t.issueSeverity);
		System.out.println(ColorCodes.BLUE + "issue priority: " + ColorCodes.RESET + t.issuePriority);
		System.out.println(ColorCodes.BLUE + "issue type: " + ColorCodes.RESET + t.issueType);

		System.out.println();

		return true;
	}

}
