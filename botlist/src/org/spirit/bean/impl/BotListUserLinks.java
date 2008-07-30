/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;
import java.util.List;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserLinks extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4310886479100609554L;
	
	private Long userId;
	private Long linkId;
	private List links;
	private BotListCoreUsers user;
	
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List getLinks() {
		return links;
	}
	public void setLinks(List links) {
		this.links = links;
	}
	public BotListCoreUsers getUser() {
		return user;
	}
	public void setUser(BotListCoreUsers user) {
		this.user = user;
	}


}
