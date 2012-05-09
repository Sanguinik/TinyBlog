package de.hszg.tinyblog.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Comment;

public class CommentDateComparatorTest {

	@Test
	public void test() {

		List<Comment> comments = new ArrayList<Comment>();
		Comparator<Comment> comp = new CommentDateComparator();

		Comment comment1 = new Comment("1 Homer", "simpson@example.com", "Doh!");

		sleepForASecond();

		Comment comment2 = new Comment("2 Bart", "bart@example.com", "Hi");

		sleepForASecond();

		Comment comment3 = new Comment("3 Lisa", "saxophonelisa@example.com",
				"hello!");

		sleepForASecond();

		Comment comment4 = new Comment("4 Marge", "marge@example.com", "Hrrmmm");

		comments.add(comment1);
		comments.add(comment2);
		comments.add(comment3);
		comments.add(comment4);

		Collections.sort(comments, comp);
		assertEquals(comment1, comments.get(0));
		assertEquals(comment2, comments.get(1));
		assertEquals(comment3, comments.get(2));
		assertEquals(comment4, comments.get(3));

	}

	private void sleepForASecond() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
