package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import com.biscuit.commands.help.BacklogHelp;
import com.biscuit.commands.userStory.AddUserStoryToBacklog;
import com.biscuit.commands.epic.AddEpicToBacklog;
import com.biscuit.commands.userStory.ListUserStories;
import com.biscuit.factories.BacklogCompleterFactory;
import com.biscuit.models.Theme;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.Completer;

public class ThemeView extends View {

    Theme theme = null;


    public ThemeView(View previousView, Theme theme) {
        super(previousView, "theme");
        this.theme = theme;
    }


    @Override
    void addSpecificCompleters(List<Completer> completers) {

    }


    @Override
    boolean executeCommand(String[] words) throws IOException {
        if (words.length == 1) {
            return execute1Keyword(words);
        } else if (words.length == 2) {
            return execute2Keyword(words);
        } else if (words.length == 4) {
            return execute4Keyword(words);
        }

        return false;
    }


    private boolean execute4Keyword(String[] words) throws IOException {
        if (words[0].equals("list")) {
            if (words[1].equals("user_stories")) {
                if (words[2].equals("filter")) {
                    (new ListUserStories(theme, "Backlog (User Stories)", true, words[3], false, "")).execute();
                    return true;
                } else if (words[2].equals("sort")) {
                    (new ListUserStories(theme, "Backlog (User Stories)", false, "", true, words[3])).execute();
                    return true;
                }
            }
        }

        return false;
    }


    private boolean execute2Keyword(String[] words) throws IOException {
        if (words[0].equals("add")) {
            if (words[1].equals("user_story")) {
                (new AddUserStoryToBacklog(reader, this.theme.project)).execute();
                resetCompleters();

                return true;
            }
            else if (words[1].equals("Epic")) {
                (new AddEpicToBacklog(reader, this.theme.project)).execute();
                resetCompleters();

                return true;
            }
        } else if (words[0].equals("list")) {
            if (words[1].equals("user_stories")) {
                (new ListUserStories(theme, "Backlog (User Stories)")).execute();
                return true;
            }
        } else if (words[0].equals("go_to")) {
            if (UserStories.getAllNames(theme).contains(words[1])) {
                UserStory us = UserStories.find(theme, words[1]);
                if (us == null) {
                    return false;
                }

                us.project = theme.project;

                UserStroryView usv = new UserStroryView(this, us);
                usv.view();
                return true;
            }
        }

        return false;
    }


    private boolean execute1Keyword(String[] words) throws IOException {
        if (words[0].equals("user_stories")) {
            (new ListUserStories((List<UserStory>) theme, "Backlog (User Stories)")).execute();
            return true;
        } else if (words[0].equals("help")) {
            return (new BacklogHelp()).execute();
        }

        return false;
    }

}
