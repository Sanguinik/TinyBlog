package de.hszg.tinyblog.util;

public class StringCreatorTestHelper {

	public String longStringGenerator() {
		String temp = " ";
		for (int i = 0; i < 1000; i++) {
			temp = temp + i;
		}
		return temp;
	}

}
