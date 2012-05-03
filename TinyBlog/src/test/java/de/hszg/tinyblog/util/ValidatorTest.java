package de.hszg.tinyblog.util;

import static de.hszg.tinyblog.util.Validator.checkNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidatorTest {

	private String stringToTest = "I am not null";
	private String stringToTest2 = "I am not null, too";
	private String nullString = null;

	@Test
	public void testCheckNull() {
		assertTrue(checkNotNull());

		assertTrue(checkNotNull(stringToTest));

		assertTrue(checkNotNull(stringToTest, stringToTest2));

	}

	@Test
	public void testCheckNullFalse() {
		assertFalse(checkNotNull(nullString));

		assertFalse(checkNotNull(stringToTest, nullString));

		assertFalse(checkNotNull(nullString, stringToTest, stringToTest2));

	}

}
