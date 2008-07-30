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

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListChildListLinks extends BotListBeanBase 
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320299522272098455L;
		private String keywords;
		private Long linkId;
		private String mainUrl;
		private String urlTitle;
		
		/**
		 * @return the keywords
		 */
		public String getKeywords() {
			return keywords;
		}
		/**
		 * @param keywords the keywords to set
		 */
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		/**
		 * @return the linkId
		 */
		public Long getLinkId() {
			return linkId;
		}
		/**
		 * @param linkId the linkId to set
		 */
		public void setLinkId(Long linkId) {
			this.linkId = linkId;
		}
		/**
		 * @return the mainUrl
		 */
		public String getMainUrl() {
			return mainUrl;
		}
		/**
		 * @param mainUrl the mainUrl to set
		 */
		public void setMainUrl(String mainUrl) {
			this.mainUrl = mainUrl;
		}
		/**
		 * @return the urlTitle
		 */
		public String getUrlTitle() {
			return urlTitle;
		}
		/**
		 * @param urlTitle the urlTitle to set
		 */
		public void setUrlTitle(String urlTitle) {
			this.urlTitle = urlTitle;
		}


}
