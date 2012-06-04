package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.User;

public class CreateArticleBeanTest {

	private CreateArticleBean cab;
	private static final String TITLE = "Title";
	private static final String CONTENT = "Content";
	private User user = new User("Bob", "secret", "email@example.org");

	@Before
	public void setUp() {
		cab = new CreateArticleBean();
	}

	@Test
	public void testGetTitle() {
		cab.setTitle(TITLE);
		assertEquals(TITLE, cab.getTitle());
	}

	@Test
	public void testGetContent() {
		cab.setContent(CONTENT);
		assertEquals(CONTENT, cab.getContent());
	}

	@Test
	public void testGetUser() {
		cab.setUser(user);
		assertEquals(user.getName(), cab.getUser().getName());
		assertEquals(user.getEmail(), cab.getUser().getEmail());
		assertEquals(user.getPassword(), cab.getUser().getPassword());
	}

}
