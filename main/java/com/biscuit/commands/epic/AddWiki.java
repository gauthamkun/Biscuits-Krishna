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
        StringBuilder description = new StringBuilder();
        String prompt = reader.getPrompt();
        wiki.project = project;
        setTitle();
        setDescription(description);
        reader.setPrompt(prompt);
        project.save();
        project.addWiki(wiki);
        reader.println();
        reader.println(ColorCodes.GREEN + "Wiki \"" +Wiki.title + "\" has been added to the project!\"" + ColorCodes.RESET);
        return false;

    }

    public static Object setTitle() throws IOException {
        reader.setPrompt(ColorCodes.BLUE + "Wiki Title: " + ColorCodes.RESET);
        wiki.title = reader.readLine();
        return null;
    }

    private static void setDescription(StringBuilder description) throws IOException {
        String line;
        reader.setPrompt(ColorCodes.BLUE + "\ndescription:\n" + ColorCodes.YELLOW + "(\\q to end writing)\n" + ColorCodes.RESET);

        while ((line = reader.readLine()) != null) {
            if (line.equals("\\q")) {
                break;
            }
            description.append(line).append("\n");
            reader.setPrompt("");
        }
        wiki.description = description.toString();
    }

}
