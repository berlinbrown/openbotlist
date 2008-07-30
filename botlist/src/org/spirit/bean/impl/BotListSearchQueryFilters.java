/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.spirit.bean.impl.base.BotListBeanBase;
import org.spirit.servlet.bean.BotListConcatValue;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListSearchQueryFilters extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2732718650854212662L;

	private Long rating;

	private Long userId;

	private String description;

	private Long views;

	private String searchTerm;

	private String userName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getSearchTerm() {
		return searchTerm;
	}
	public String getSearchTermEncoded() {		
		try {
			return URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {		
		}
		return searchTerm;
	}
	public String getSearchTermShorten() {
		return BotListConcatValue.getMaxWord(this.searchTerm, new Integer(22));		
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getViews() {
		return views;
	}

	public void setViews(Long views) {
		this.views = views;
	}

}
