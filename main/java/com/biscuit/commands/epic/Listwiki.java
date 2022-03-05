package com.biscuit.commands.epic;

import com.biscuit.models.Project;
import com.biscuit.models.Wiki;
import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Listwiki {
    Project project = null;
    Wiki wiki =null;
    String title = "";

    public Listwiki(Project project, String title) {
        super();
        this.project = project;
        this.title = title;
    }

    public Listwiki(Wiki wiki, String title) {
        super();
        this.wiki = wiki;
        this.title = title;
    }

    public boolean execute() throws IOException {

        V2_AsciiTable at = new V2_AsciiTable();
        String tableString;

        List<Wiki> wiki = new ArrayList<>();

        if (project != null) {
            wiki.addAll(project.wiki);
        }

        at.addRule();
        if (!this.title.isEmpty()) {
            at.addRow(null, this.title).setAlignment(new char[] { 'c', 'c' });
            at.addRule();
        }
        at.addRow("Name", "Description")
                .setAlignment(new char[] { 'l', 'l'});

        if (wiki.size() == 0) {
            at.addRule();
            at.addRow( null, null);
        } else {
            for (Wiki w : wiki) {
                at.addRule();

                at.addRow(w.title, w.description).setAlignment(new char[] { 'l', 'l' });
            } // for
        }

        at.addRule();
        at.addRow(null, "Total: " + wiki.size());
        at.addRule();

        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
        rend.setWidth(new WidthLongestLine());

        RenderedTable rt = rend.render(at);
        tableString = rt.toString();


        System.out.println();
        System.out.println(tableString);

        return false;
    }


}
