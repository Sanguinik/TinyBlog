package de.hszg.tinyblog.view;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.hszg.tinyblog.persistence.model.Comment;
import de.hszg.tinyblog.persistence.model.User;

public class CommentBeanTest {

	private CommentBean commentBean;
	private static final String NAME = "Bob";
	private static final String CONTENT = "My Comment";
	private static final String EMAIL = "email@example.org";
	private User user = new User(NAME, "secret", EMAIL);
	private FullArticleBean fullArticleBean = new FullArticleBean();
	private AuthenticationContextBean authenticationContextBean = new AuthenticationContextBean();
	private Set<Comment> comments = new HashSet<Comment>();

	@Before
	public void setUp() {
		commentBean = new CommentBean();
	}

	@Test
	public void testGetName() {
		commentBean.setName(NAME);
		assertEquals(NAME, commentBean.getName());
	}

	@Test
	public void testGetContent() {
		commentBean.setContent(CONTENT);
		assertEquals(CONTENT, commentBean.getContent());
	}

	@Test
	public void testGetComments() {
		commentBean.setComments(comments);
		assertEquals(comments, commentBean.getComments());
	}

	@Test
	public void testGetEmail() {
		commentBean.setEmail(EMAIL);
		assertEquals(EMAIL, commentBean.getEmail());
	}

	@Test
	public void testGetUser() {
		commentBean.setUser(user);
		assertEquals(user.getName(), commentBean.getUser().getName());
		assertEquals(user.getEmail(), commentBean.getUser().getEmail());
	}

	@Test
	public void testGetFullArticleBean() {
		commentBean.setFullArticleBean(fullArticleBean);
		assertEquals(fullArticleBean, commentBean.getFullArticleBean());

	}

	@Test
	public void testGetAuthenticationContextBean() {
		commentBean.setAuthenticationContextBean(authenticationContextBean);
		assertEquals(authenticationContextBean,
				commentBean.getAuthenticationContextBean());
	}

}
