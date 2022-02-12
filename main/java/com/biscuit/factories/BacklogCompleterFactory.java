package com.biscuit.factories;

import java.util.ArrayList;
import java.util.List;

import com.biscuit.models.Backlog;
import com.biscuit.models.UserStory;
import com.biscuit.models.services.Finder.UserStories;

import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.NullCompleter;
import jline.console.completer.StringsCompleter;

public class BacklogCompleterFactory {

	public static List<Completer> getBacklogCompleters(Backlog backlog) {
		List<Completer> completers = new ArrayList<Completer>();

		// TODO: backlog commands

		completers.add(new ArgumentCompleter(new StringsCompleter("user_stories", "back"), new NullCompleter()));

		completers.add(
				new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("user_stories"),
						new StringsCompleter("filter"), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("list"), new StringsCompleter("user_stories"),
				new StringsCompleter("sort"),
				new StringsCompleter(UserStory.fields), new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("add"), new StringsCompleter("user_story"),
				new NullCompleter()));

		completers.add(new ArgumentCompleter(new StringsCompleter("go_to"),
				new StringsCompleter(UserStories.getAllNames(backlog)), new NullCompleter()));

		return completers;
	}

}
