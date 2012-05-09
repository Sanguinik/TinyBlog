/**
 * 
 */
package de.hszg.tinyblog.util;

import java.util.Comparator;

import de.hszg.tinyblog.persistence.model.Comment;

/**
 * @author marlene
 * 
 */
public class CommentDateComparator implements Comparator<Comment> {

	@Override
	public int compare(final Comment c1, final Comment c2) {

		if (c1.getPublishingDate() == null && c2.getPublishingDate() == null) {
			return 0;
		}
		if (c1.getPublishingDate() == null) {
			return 1;
		}
		if (c2.getPublishingDate() == null) {
			return -1;
		}
		return c1.getPublishingDate().compareTo(c2.getPublishingDate());

	}

}
