/**
 * 
 */
package de.hszg.tinyblog.util;

import java.util.Comparator;

import de.hszg.tinyblog.persistence.model.Comment;

/**
 * This comparator compares the dates of the comments.
 * 
 * @author marlene
 * 
 */
public class CommentDateComparator implements Comparator<Comment> {

	@Override
	public int compare(final Comment c1, final Comment c2) {

		return c1.getPublishingDate().compareTo(c2.getPublishingDate());

	}

}
