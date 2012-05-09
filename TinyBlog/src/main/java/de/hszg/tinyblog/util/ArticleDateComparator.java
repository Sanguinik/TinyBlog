package de.hszg.tinyblog.util;

import java.util.Comparator;

import de.hszg.tinyblog.persistence.model.Article;

public class ArticleDateComparator implements Comparator<Article> {

	@Override
	public int compare(final Article a1, final Article a2) {
		if (a1.getPublishingDate() == null && a2.getPublishingDate() == null) {
			return 0;
		}
		if (a1.getPublishingDate() == null) {
			return 1;
		}
		if (a2.getPublishingDate() == null) {
			return -1;
		}
		return a1.getPublishingDate().compareTo(a2.getPublishingDate());
	}

}
