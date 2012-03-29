package de.hszg.tinyblog.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import de.hszg.tinyblog.model.Article;
import de.hszg.tinyblog.model.User;

@ManagedBean
public class ArticleBean {
	

	private List<Article> articles = new ArrayList<Article>();

	public ArticleBean(){
		User user = new User("Marlene","Geheim","marlene@example.org");
		Article article1 = new Article("title1","Bla bla bla", user);
		Article article2 = new Article("title2","Bla bli blubb", user);
		Article article3 = new Article("title3","La le lu", user);
	
		articles.add(article1);
		articles.add(article2);
		articles.add(article3);
	}
	
	public List<Article> getArticles() {
		return articles;
	}
	
	public String showFullArticle(){
		
		return "fullArticle";
	}
	
	
}
