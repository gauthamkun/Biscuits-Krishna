package com.biscuit.commands.epic;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Members;
import com.biscuit.models.Project;
import com.biscuit.models.enums.Roles;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;
import java.io.IOException;


public class AddMember implements Command{
    ConsoleReader reader;
    Project project;
    Members member = new Members();

    public AddMember(ConsoleReader reader, Project project) {
        super();
        this.reader = reader;
        this.project = project;
    }

    public boolean execute() throws IOException {
        String prompt = reader.getPrompt();
        member.project = project;
        setTitle();
        setRole();
        return false;
    }
        private void setRole() throws IOException {
            // List<String> businessValues = new ArrayList<String>();
            String line;
            Completer oldCompleter = (Completer) reader.getCompleters().toArray()[0];

            // for (BusinessValue bv : BusinessValue.values()) {
            // businessValues.add(bv.name().toLowerCase());
            // }

            Completer businessValuesCompleter = new ArgumentCompleter(new StringsCompleter(Roles.values), new NullCompleter());

            reader.removeCompleter(oldCompleter);
            reader.addCompleter(businessValuesCompleter);

            reader.setPrompt(ColorCodes.BLUE + "\nRole :\n" + ColorCodes.YELLOW + "(hit Tab to see valid values)\n" + ColorCodes.RESET);

            while ((line = reader.readLine()) != null) {
                line = line.trim().toUpperCase();

                try {
                    Members.role = Roles.valueOf(line);
                } catch (IllegalArgumentException e) {
                    System.out.println(ColorCodes.RED + "invalid value" + ColorCodes.RESET);
                    continue;
                }

                reader.removeCompleter(businessValuesCompleter);
                reader.addCompleter(oldCompleter);
                break;
            }

        }

    private void setTitle() throws IOException {
        reader.setPrompt(ColorCodes.BLUE + "title: " + ColorCodes.RESET);
        member.title = reader.readLine();
    }
}