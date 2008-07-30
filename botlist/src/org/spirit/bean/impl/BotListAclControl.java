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

public class BotListAclControl extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2431087416714266287L;
	private String controlName;
	private String shortDescr;
	private String longDescr;
	private String controlUid;
	/**
	 * @return the controlName
	 */
	public String getControlName() {
		return controlName;
	}
	/**
	 * @param controlName the controlName to set
	 */
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	/**
	 * @return the controlUid
	 */
	public String getControlUid() {
		return controlUid;
	}
	/**
	 * @param controlUid the controlUid to set
	 */
	public void setControlUid(String controlUid) {
		this.controlUid = controlUid;
	}
	/**
	 * @return the longDescr
	 */
	public String getLongDescr() {
		return longDescr;
	}
	/**
	 * @param longDescr the longDescr to set
	 */
	public void setLongDescr(String longDescr) {
		this.longDescr = longDescr;
	}
	/**
	 * @return the shortDescr
	 */
	public String getShortDescr() {
		return shortDescr;
	}
	/**
	 * @param shortDescr the shortDescr to set
	 */
	public void setShortDescr(String shortDescr) {
		this.shortDescr = shortDescr;
	}


}
