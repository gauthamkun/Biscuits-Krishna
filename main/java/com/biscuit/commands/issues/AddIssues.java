package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Issues;
import com.biscuit.models.Project;
import com.biscuit.models.UserStory;
import com.biscuit.models.enums.IssuePriority;
import com.biscuit.models.enums.IssueSeverity;
import com.biscuit.models.enums.IssueType;
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
		setTitle();
		setDescription(description);
		setIssuePriority();
		setIssueSeverity();
		setIssueType();
		setTime();
		issues.state = Status.OPEN;
		issues.initiatedDate = new Date();
		issues.dueDate = new Date(0);

		reader.setPrompt(prompt);

		userStory.issues.add(issues);
		project.save();

		reader.println();
		reader.println(ColorCodes.GREEN + "Issue \"" + issues.title + "\" has been added!" + ColorCodes.RESET);

		return false;
	}
	private void setTime() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer timeCompleter = new ArgumentCompleter(new StringsCompleter("1", "1.5", "2", "2.25", "3"), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(timeCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nestimated time (in hours):\n" + ColorCodes.YELLOW + "(hit Tab to see an example)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim();

			try {
				issues.estimatedTime = Float.valueOf(line);
				break;
			} catch (NumberFormatException e) {
				System.out.println(ColorCodes.RED + "invalid value: must be a float value!" + ColorCodes.RESET);
			}
		}

		reader.removeCompleter(timeCompleter);
		reader.addCompleter(oldCompleter);
	}


	private void setDescription(StringBuilder description) throws IOException {
		String line;
		reader.setPrompt(ColorCodes.BLUE + "\ndescription:\n" + ColorCodes.YELLOW + "(\\q to end writing)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		issues.description = description.toString();
	}


	private void setTitle() throws IOException {
		reader.setPrompt(ColorCodes.BLUE + "title: " + ColorCodes.RESET);
		issues.title = reader.readLine();
	}

	private void setIssuePriority() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];


		Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(IssuePriority.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(businessValuesCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nPriority value:\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim().toUpperCase();

			try {
				issues.issuePriority = IssuePriority.valueOf(line);
			} catch (IllegalArgumentException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(businessValuesCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}
	}

		private void setIssueSeverity() throws IOException {
			String line;
			Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];


			Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(IssueSeverity.values), new NullCompleter());

			reader.removeCompleter(oldCompleter);
			reader.addCompleter(businessValuesCompleter);

			reader.setPrompt(ColorCodes.BLUE + "\nSeverity value:\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n" + ColorCodes.RESET);

			while ((line = reader.readLine()) != null) {
				line = line.trim().toUpperCase();

				try {
					issues.issueSeverity= IssueSeverity.valueOf(line);
				} catch (IllegalArgumentException e) {
					System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
					continue;
				}

				reader.removeCompleter(businessValuesCompleter);
				reader.addCompleter(oldCompleter);
				break;
			}

	}

	private void setIssueType() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];


		Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(IssueType.values), new NullCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(businessValuesCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\nIssue Type value:\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n" + ColorCodes.RESET);

		while ((line = reader.readLine()) != null) {
			line = line.trim().toUpperCase();

			try {
				issues.issueType= IssueType.valueOf(line);
			} catch (IllegalArgumentException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(businessValuesCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}

	}


}

