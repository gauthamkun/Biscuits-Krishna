package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Issues;
import com.biscuit.models.Project;
import com.biscuit.models.Task;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.Happiness;
import com.biscuit.models.enums.Status;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

import java.io.IOException;
import java.util.Date;

public class AddIssues implements Command {

	ConsoleReader reader;
	Project project;
	UserStory userStory;
	Issues issues = new Issues();


	public AddIssues(ConsoleReader reader, Project project, UserStory userStory) {
		super();
		this.reader = reader;
		this.project = project;
		this.userStory = userStory;
	}


	public boolean execute() throws IOException {
		StringBuilder description = new StringBuilder();
		String prompt = reader.getPrompt();

		issues.project = project;
		//setTitle();
		//setDescription(description);
		//setBusinessValue();
		//setTime();
		issues.initiatedDate = new Date();
		issues.dueDate = new Date(0);

		reader.setPrompt(prompt);

		//userStory.tasks.add(issues);
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Issue \"" + issues.title + "\" has been added!" + ColorCodes.RESET);

		return false;
	}




}
