package de.hszg.tinyblog.util;

import java.util.Comparator;

import de.hszg.tinyblog.persistence.model.Article;

/**
 * This comparator compares the dates of two articles.
 * 
 * @author marlene
 * 
 */

public class ArticleDateComparator implements Comparator<Article> {

	@Override
	public int compare(final Article a1, final Article a2) {

		return a1.getPublishingDate().compareTo(a2.getPublishingDate());
	}

}
