/**
 * Berlin Brown
 * Nov 9, 2006
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for 
 * social bookmarking and collecting online news content for use on the web.  
 * Multiple web front-ends exist for Django, Rails, and J2EE.  
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *	    * Redistributions of source code must retain the above copyright notice, 
 *	    this list of conditions and the following disclaimer.
 *	    * Redistributions in binary form must reproduce the above copyright notice, 
 *	    this list of conditions and the following disclaimer in the documentation 
 *	    and/or other materials provided with the distribution.
 *	    * Neither the name of the Newspiritcompany.com (Berlin Brown) nor 
 *	    the names of its contributors may be used to endorse or promote 
 *	    products derived from this software without specific prior written permission.
 *	
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE --
 */
package org.spirit.apps.foaf;

import java.io.Serializable;
import java.util.Date;

import org.spirit.bean.impl.base.BotListEntity;

/**
 * This is class is used by botverse.
 * 
 * Fields included from the Entity base class.
 * -------------------------- 
 *  String generatedUniqueId;    
 *	Calendar createdOn;
 *	Calendar updatedOn;
 *	Long id;	
 *	String mainUrl;
 *	String keywords;
 *	String urlDescription;
 *	String urlTitle;	
 *
 * @author Berlin Brown
 * 
 */
public class BotListEntityTypeFoaf extends BotListEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800491702537719907L;	                            	        
	
	private String foafInterestDescr;
	private Long rating;
	private Long views;
	private Long userId;
	private String fullName;
	private String foafMbox;
	
	private Date dateOfBirth;
	private String friendSetUid;
	private String foafPageDocUrl;
	private String foafImg;
	private Long processCount;
	private String nickname;
	private String foafName;
	
	private Long requestTime;
	private String httpStatusCode;
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("#BotListEntityTypeFoaf: mainUrl=" + this.getMainUrl() + " nickname=" + this.getNickname() + " title=" + this.getUrlTitle());
		return buf.toString();
	}
	
	public String getFoafInterestDescr() {
		return foafInterestDescr;
	}
	public void setFoafInterestDescr(String foafInterestDescr) {
		this.foafInterestDescr = foafInterestDescr;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFoafMbox() {
		return foafMbox;
	}
	public void setFoafMbox(String foafMbox) {
		this.foafMbox = foafMbox;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFriendSetUid() {
		return friendSetUid;
	}
	public void setFriendSetUid(String friendSetUid) {
		this.friendSetUid = friendSetUid;
	}
	public String getFoafPageDocUrl() {
		return foafPageDocUrl;
	}
	public void setFoafPageDocUrl(String foafPageDocUrl) {
		this.foafPageDocUrl = foafPageDocUrl;
	}
	public String getFoafImg() {
		return foafImg;
	}
	public void setFoafImg(String foafImg) {
		this.foafImg = foafImg;
	}
	public Long getProcessCount() {
		return processCount;
	}
	public void setProcessCount(Long processCount) {
		this.processCount = processCount;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFoafName() {
		return foafName;
	}
	public void setFoafName(String foafName) {
		this.foafName = foafName;
	}
	public Long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}
	public String getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	

}
