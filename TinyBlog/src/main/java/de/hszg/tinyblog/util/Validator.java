package de.hszg.tinyblog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This validator is responsible for several validations of the application.
 * 
 * @author marlene
 * 
 */

public final class Validator {

	private static Logger logger = LoggerFactory.getLogger(Validator.class);

	private Validator() {

	}

	/**
	 * This method can have one or more parameters. It is used for proving if
	 * the given parameters are null.
	 * 
	 * @param objects
	 *            The objects which should be checked.
	 * @return true, if no object is null and false if at least one object of
	 *         the given ones is null.
	 */
	public static boolean checkNotNull(final Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null) {
				logger.error("Parameter is null at position: " + i);
				return false;
			}
		}
		return true;
	}

}
