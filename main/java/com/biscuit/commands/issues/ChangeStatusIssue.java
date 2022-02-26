package com.biscuit.commands.issues;

import com.biscuit.ColorCodes;
import com.biscuit.commands.Command;
import com.biscuit.models.Issues;
import com.biscuit.models.Task;
import com.biscuit.models.enums.Status;

import java.io.IOException;

public class ChangeStatusIssue implements Command {

	Issues t = null;
	Status state = null;


	public ChangeStatusIssue(Issues t, Status state) {
		super();
		this.t = t;
		this.state = state;
	}


	@Override
	public boolean execute() throws IOException {

		Status oldState = t.state;

		t.state = state;

		t.save();

		System.out.println(ColorCodes.GREEN + "State of issue " + t.title + " has been changed from " + oldState + " to "
				+ t.state + ColorCodes.RED);

		return true;
	}

}
