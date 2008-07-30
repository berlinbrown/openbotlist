/* 
 *** Notice Update: 8/14/2007
 *** Copyright 2007 Berlin Brown
 *** Copyright 2006-2007 Newspiritcompany.com
 *** 
 *** This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
 *** otherwise stated, use or distribution of this program 
 *** for commercial purpose is prohibited.
 *** 
 *** See LICENSE.BOTLIST for more information.
 ***
 *** The SOFTWARE PRODUCT and CODE are protected by copyright and 
 *** other intellectual property laws and treaties. 
 ***  
 *** Unless required by applicable law or agreed to in writing, software
 *** distributed  under the  License is distributed on an "AS IS" BASIS,
 *** WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 *** implied.
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

public class BotListLinkGroups extends BotListBeanBase 
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2884784057427513464L;
	private String generatedId;	
	private String groupName;
	private List links;
	
	/**
	 * @return the generatedId
	 */
	public String getGeneratedId() {
		return generatedId;
	}
	/**
	 * @param generatedId the generatedId to set
	 */
	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the links
	 */
	public List getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(List links) {
		this.links = links;
	}


}
