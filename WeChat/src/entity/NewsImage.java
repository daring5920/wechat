/**
 * 
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daring
 *
 */
public class NewsImage extends Message{
	
	String ArticleCount;	//是	图文消息个数，限制为10条以内
	List<ArticlesMessage> Articles = new ArrayList<ArticlesMessage>();	//是	多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应

	
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public List<ArticlesMessage> getArticles() {
		return Articles;
	}
	public void setArticles(List<ArticlesMessage> articles) {
		Articles = articles;
	}



	
	

}
