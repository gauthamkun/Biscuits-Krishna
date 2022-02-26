package com.biscuit.commands.epic;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Project;
import com.biscuit.models.Members;
import com.biscuit.models.Sprint;
import com.biscuit.models.services.DateService;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

public class ListMembers implements Command {

    Project project = null;
    String title = "";
    boolean isFilter = false;
    boolean isSort = false;
    static boolean isReverse = false;
    private String filterBy;
    private String sortBy;
    private static String lastSortBy = "";
    private Members members;


    public ListMembers(Project project, String title) {
        super();
        this.project = project;
        this.title = title;
    }


    public ListMembers(Project project, String title, boolean isFilter, String filterBy, boolean isSort, String sortBy) {
        super();
        this.project = project;
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


        List<Members> members = new ArrayList<>();

        if (project != null) {
            members.addAll((Collection<? extends Members>) project.members);
        }

        if (isSort) {
            doSort(members);
        }

        at.addRule();
        if (!this.title.isEmpty()) {
            at.addRow(null, null, this.title).setAlignment(new char[] { 'c', 'c', });
            at.addRule();
        }
        at.addRow("Name", "Role")
                .setAlignment(new char[] { 'l' ,'l' });

        if (members.size() == 0) {
            String message;
            if (!isFilter) {
                message = "There are no members!";
            } else {
                message = "No results";
            }
            at.addRule();
            at.addRow(null, null, null, null, null, null, message);
        } else {
            for (Members s : members) {
                at.addRule();

                at.addRow(s.title ,s.role).setAlignment(new char[] { 'l','c' });
            }
        }

        at.addRule();
        at.addRow(null, null, "Total: " + members.size());
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


    private void doSort(List<Members> members) {

        Comparator<Sprint> byName = (s1, s2) -> s1.name.compareTo(s2.name);
        Comparator<Sprint> byDescription = (s1, s2) -> s1.description.compareTo(s2.description);
        Comparator<Sprint> byState = (s1, s2) -> Integer.compare(s1.state.getValue(), s2.state.getValue());
        Comparator<Sprint> byStartDate = (s1, s2) -> s1.startDate.compareTo(s2.startDate);
        Comparator<Sprint> byDueDate = (s1, s2) -> s1.dueDate.compareTo(s2.dueDate);
        Comparator<Sprint> byAssignedEffort = (s1, s2) -> Integer.compare(s1.assignedEffort, s2.assignedEffort);
        Comparator<Sprint> byVelocity = (s1, s2) -> Integer.compare(s1.velocity, s2.velocity);
        Comparator<Sprint> byFiled = null;

        if (sortBy.equals(Sprint.fields[0])) {
            byFiled = byName;
        } else if (sortBy.equals(Sprint.fields[1])) {
            byFiled = byDescription;
        } else if (sortBy.equals(Sprint.fields[2])) {
            byFiled = byState;
        } else if (sortBy.equals(Sprint.fields[3])) {
            byFiled = byStartDate;
        } else if (sortBy.equals(Sprint.fields[4])) {
            byFiled = byDueDate;
        } else if (sortBy.equals(Sprint.fields[5])) {
            byFiled = byAssignedEffort;
        } else if (sortBy.equals(Sprint.fields[6])) {
            byFiled = byVelocity;
        } else {
            return;
        }



        if (sortBy.equals(lastSortBy)) {
            isReverse = !isReverse;
            if (isReverse) {

            }
        } else {
            lastSortBy = sortBy;
            isReverse = false;
        }

        members.clear();

    }



    private String colorize(String tableString) {
        for (String header : Sprint.fieldsAsHeader) {
            tableString = tableString.replaceFirst(header, ColorCodes.BLUE + header + ColorCodes.RESET);
        }

        return tableString;
    }

}
