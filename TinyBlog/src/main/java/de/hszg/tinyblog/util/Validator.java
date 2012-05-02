package de.hszg.tinyblog.util;

public final class Validator {

	private Validator() {

	}

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
