package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Issues;
import com.biscuit.models.Task;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.DateService;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListIssues implements Command {

	UserStory userStory = null;
	String title = "";
	boolean isFilter = false;
	boolean isSort = false;
	static boolean isReverse = false;
	private String filterBy;
	private String sortBy;
	private static String lastSortBy = "";


	public ListIssues(UserStory userStory, String title) {
		super();
		this.userStory = userStory;
		this.title = title;
	}


	public ListIssues(UserStory userStory, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
		super();
		this.userStory = userStory;
		this.title = title;
		this.isFilter = isFilter;
		this.filterBy = filterBy.toLowerCase();
		this.isSort = isSort;
		this.sortBy = sortBy.toLowerCase();
	}


	@Override
	public boolean execute() throws IOException {

		V2_AsciiTable at = new V2_AsciiTable();
		String tableString;

		List<Issues> issues = new ArrayList<>();
		issues.addAll(userStory.issues);

		if (isFilter) {
			doFilter(issues);
		}

		if (isSort) {
			doSort(issues);
		}

		at.addRule();
		if (!this.title.isEmpty()) {
			at.addRow(null, null, null, null, null, null, null, this.title).setAlignment(new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });
			at.addRule();
		}
		at.addRow("Title", "Description", "Team Member", "State", "Issue Priority", "Issue Severity", "Issue Type", "Initiated Date", "Estimated Time", "Due Date")
				.setAlignment(new char[] { 'l', 'l', 'l', 'c', 'c', 'c', 'c', 'c', 'c', 'c' });

		if (issues.size() == 0) {
			String message;
			if (!isFilter) {
				message = "There are no issues!";
			} else {
				message = "No results";
			}
			at.addRule();
			at.addRow(null, null, null, null, null, null, null, null, message);
		} else {
			for (Issues t : issues) {
				at.addRule();

				at.addRow(t.title, t.description, t.state, DateService.getDateAsString(t.initiatedDate), DateService.getDateAsString(t.dueDate),
						DateService.getDateAsString(t.dueDate), t.estimatedTime).setAlignment(new char[] { 'l', 'l', 'l', 'c', 'c', 'c', 'c', 'c' });
			} // for
		}

		at.addRule();
		at.addRow(null, null, null, null, null, null, null, null, null, "Total: " + issues.size());
		at.addRule();

		V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
		rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
		rend.setWidth(new WidthLongestLine());

		RenderedTable rt = rend.render(at);
		tableString = rt.toString();

		tableString = colorize(tableString);

		System.out.println();
		System.out.println(tableString);

		return false;
	}


	private void doSort(List<Issues> issues) {

		Comparator<Issues> byTitle = (t1, t2) -> t1.title.compareTo(t2.title);
		Comparator<Issues> byDescription = (t1, t2) -> t1.description.compareTo(t2.description);
		Comparator<Issues> byTeamMember = (t1, t2) -> t1.teamMember.compareTo(t2.teamMember);
		Comparator<Issues> byState = (t1, t2) -> Integer.compare(t1.state.getValue(), t2.state.getValue());
		Comparator<Issues> byInitiatedDate = (t1, t2) -> t1.initiatedDate.compareTo(t2.initiatedDate);
		Comparator<Issues> byDueDate = (t1, t2) -> t1.dueDate.compareTo(t2.dueDate);
		Comparator<Issues> byTime = (t1, t2) -> Float.compare(t1.estimatedTime, t2.estimatedTime);
		Comparator<Issues> byFiled = null;

		if (sortBy.equals(Issues.fields[0])) {
			byFiled = byTitle;
		} else if (sortBy.equals(Issues.fields[1])) {
			byFiled = byDescription;
		} else if (sortBy.equals(Issues.fields[2])) {
			byFiled = byTeamMember;
		} else if (sortBy.equals(Issues.fields[3])) {
			byFiled = byState;
		} else if (sortBy.equals(Task.fields[7])) {
			byFiled = byInitiatedDate;
		} else if (sortBy.equals(Task.fields[9])) {
			byFiled = byDueDate;
		} else if (sortBy.equals(Task.fields[8])) {
			byFiled = byTime;
		} else {
			return;
		}

		List<Issues> sorted = issues.stream().sorted(byFiled).collect(Collectors.toList());

		if (sortBy.equals(lastSortBy)) {
			isReverse = !isReverse;
			if (isReverse) {
				Collections.reverse(sorted);
			}
		} else {
			lastSortBy = sortBy;
			isReverse = false;
		}

		issues.clear();
		issues.addAll(sorted);
	}


	private void doFilter(List<Issues> issues) {
		List<Issues> filtered = issues.stream()
				.filter(us -> us.title.toLowerCase().contains(filterBy) || us.description.toLowerCase().contains(filterBy)
						|| us.teamMember.toLowerCase().contains(filterBy) ||us.state.toString().toLowerCase().contains(filterBy) || String.valueOf(us.estimatedTime).contains(filterBy)
						|| DateService.getDateAsString(us.initiatedDate).toLowerCase().contains(filterBy)
						|| DateService.getDateAsString(us.dueDate).toLowerCase().contains(filterBy))
				.collect(Collectors.toList());
		issues.clear();
		issues.addAll(filtered);
	}


	private String colorize(String tableString) {
		tableString = tableString.replaceFirst("Title", ColorCodes.BLUE + "Title" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Description", ColorCodes.BLUE + "Description" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Team Member", ColorCodes.BLUE + "Description" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("State", ColorCodes.BLUE + "State" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Issue Priority", ColorCodes.BLUE + "Issue Priority" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Issue Severity", ColorCodes.BLUE + "Issue Severity" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Issue Type", ColorCodes.BLUE + "Issue Type" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Initiated Date", ColorCodes.BLUE + "Initiated Date" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Estimated Time", ColorCodes.BLUE + "Estimated Time" + ColorCodes.RESET);
		tableString = tableString.replaceFirst("Due Date", ColorCodes.BLUE + "Due Date" + ColorCodes.RESET);

		return tableString;
		// return tableString.replaceAll("MUST_HAVE", ColorCodes.YELLOW +
		// "MUST_HAVE" + ColorCodes.RESET);
	}

}
