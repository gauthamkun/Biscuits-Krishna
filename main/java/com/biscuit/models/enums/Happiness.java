package com.biscuit.models.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Happiness {

	SAD(0), MAD(1), CONFUSED(2), HAPPY(3);

	private final int value;
	public static List<String> values = new ArrayList<>(
			Arrays.asList("sad", "mad", "confused", "happy"));


	private Happiness(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}
}
