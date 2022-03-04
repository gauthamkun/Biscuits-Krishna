package com.biscuit.commands.epic;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Members;
import com.biscuit.models.Wiki;
import com.biscuit.models.Project;
import jline.console.ConsoleReader;

import java.io.IOException;

public class AddWiki implements Command {
    static ConsoleReader reader;
    static Project project;
    static Wiki wiki = new Wiki();

    public AddWiki(ConsoleReader reader, Project project) {
        super();
        this.reader = reader;
        this.project = project;
    }
    public boolean execute() throws IOException {
        String prompt = reader.getPrompt();
        wiki.project = project;
        setTitle();

        project.save();

        reader.println();
        reader.println(ColorCodes.GREEN + "Member \"" + Members.title + "\" has been added to the project as\"" + Members.role.name() +"\"!\"" + ColorCodes.RESET);
        return false;
    }

    public static Object setTitle() throws IOException {
        reader.setPrompt(ColorCodes.BLUE + "Member Name: " + ColorCodes.RESET);
        wiki.title = reader.readLine();
        return null;
    }

}
