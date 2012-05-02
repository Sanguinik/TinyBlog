package de.hszg.tinyblog.util;

public class Validator {

	public static boolean checkNotNull(final Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null) {
				// TODO : Loggermeldung
				return false;
			}
		}
		return true;
	}

}
