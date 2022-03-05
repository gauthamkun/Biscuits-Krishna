package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.factories.DateCompleter;
import com.biscuit.models.Issues;
import com.biscuit.models.enums.Status;
import com.biscuit.models.services.DateService;
import jline.console.ConsoleReader;
import jline.console.completer.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditIssue implements Command {

	ConsoleReader reader = null;
	Issues t = new Issues();


	public EditIssue(ConsoleReader reader, Issues t) {
		super();
		this.reader = reader;
		this.t = t;
	}


	public boolean execute() throws IOException {
		String prompt = reader.getPrompt();

		setTitle();
		setDescription();
		setInitiatedDate();
		setDueDate();
		setTime();

		reader.setPrompt(prompt);

		t.save();

		return true;
	}


	private void setTime() throws IOException {

		String prompt = ColorCodes.BLUE + "points:" + ColorCodes.YELLOW + "(hit Tab to see an example) "
				+ ColorCodes.RESET;
		String preload = String.valueOf(t.estimatedTime);
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];
		Completer pointsCompleter = new ArgumentCompleter(new StringsCompleter("1", "1.5", "2", "2.25", "3"),
				new NullCompleter());
		reader.removeCompleter(oldCompleter);
		reader.addCompleter(pointsCompleter);

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			line = line.trim();

			try {
				t.estimatedTime = Float.valueOf(line);
				break;
			} catch (NumberFormatException e) {
				System.out.println(ColorCodes.RED + "invalid value: must be a float value!" + ColorCodes.RESET);
			}
		}

		reader.removeCompleter(pointsCompleter);
		reader.addCompleter(oldCompleter);
	}


	private void setDueDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ndue date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(t.dueDate) + "\n");

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			} else if (words[0].equals("unset")) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				t.dueDate = new Date(0);
				break;
			}

			try {
				int month = DateCompleter.months.indexOf(words[0]);
				int day = Integer.parseInt(words[1]);
				int year = Integer.parseInt(words[2]);

				Calendar cal = new GregorianCalendar();
				cal.clear();
				cal.set(year, month, 1);

				if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					throw new NullPointerException();
				}

				cal.set(year, month, day);

				if (DateService.isSet(t.dueDate) && cal.getTime().compareTo(t.dueDate) <= 0) {
					System.out.println(ColorCodes.RED + "due date must be after planned date" + ColorCodes.RESET);
					continue;
				}

				t.dueDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}

	}


	private void setInitiatedDate() throws IOException {
		String line;
		Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

		Completer dateCompleter = new AggregateCompleter(DateCompleter.getDateCompleter());

		reader.removeCompleter(oldCompleter);
		reader.addCompleter(dateCompleter);

		reader.setPrompt(ColorCodes.BLUE + "\ninitiated date:\n" + ColorCodes.YELLOW
				+ "(hit Tab to see examples)\n(optional: leave it blank for unchange, or unset to unset)\n"
				+ ColorCodes.RESET + "current value: " + DateService.getDateAsString(t.initiatedDate) + "\n");

		while ((line = reader.readLine()) != null) {
			line = line.trim();
			String words[] = line.split("\\s+");

			if (line.isEmpty()) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				break;
			} else if (words[0].equals("unset")) {
				reader.removeCompleter(dateCompleter);
				reader.addCompleter(oldCompleter);
				t.initiatedDate = new Date(0);
				break;
			}

			try {
				int month = DateCompleter.months.indexOf(words[0]);
				int day = Integer.parseInt(words[1]);
				int year = Integer.parseInt(words[2]);

				Calendar cal = new GregorianCalendar();
				cal.clear();
				cal.set(year, month, 1);

				if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					throw new NullPointerException();
				}

				cal.set(year, month, day);

				t.initiatedDate = cal.getTime();

			} catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
				System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
				continue;
			}

			reader.removeCompleter(dateCompleter);
			reader.addCompleter(oldCompleter);
			break;
		}

	}



	private void setDescription() throws IOException {
		StringBuilder description = new StringBuilder();
		String line;
		String prompt = ColorCodes.BLUE + "description: " + ColorCodes.YELLOW + "(\\q to end writing) "
				+ ColorCodes.RESET;
		String preload = t.description.replace("\n", "<newline>").replace("!", "<exclamation-mark>");

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		while ((line = reader.readLine()) != null) {
			if (line.equals("\\q")) {
				break;
			}
			description.append(line).append("\n");
			reader.setPrompt("");
		}

		t.description = description.toString().replace("<newline>", "\n").replace("<exclamation-mark>", "!");
	}


	private void setTitle() throws IOException {
		String prompt = ColorCodes.BLUE + "title: " + ColorCodes.RESET;
		String preload = t.title;

		reader.resetPromptLine(prompt, preload, 0);
		reader.print("\r");

		t.title = reader.readLine();
	}

}
