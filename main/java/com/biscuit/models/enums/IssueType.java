package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum IssueType {

	BUG(0), IMPEDIMENT(1), ENHANCEMENT(2);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("bug", "impediment", "enhancement"));


	private IssueType(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
