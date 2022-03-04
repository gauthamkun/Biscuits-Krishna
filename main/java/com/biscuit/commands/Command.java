package com.biscuit.commands;

import java.io.IOException;

public interface Command {

	static boolean execute() throws IOException;
}
