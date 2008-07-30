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

public class BotListCatLinkGroups extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6509095735442509973L;
	private String categoryDescr;
	private String categoryColor;
	private String categoryName;
	private List terms;
	
	public String getCategoryColor() {
		return categoryColor;
	}
	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
	}
	public String getCategoryDescr() {
		return categoryDescr;
	}
	public void setCategoryDescr(String categoryDescr) {
		this.categoryDescr = categoryDescr;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List getTerms() {
		return terms;
	}
	public void setTerms(List terms) {
		this.terms = terms;
	}
	/**
	 * Use getCategoryTerm to also return the category name.
	 * 
	 * @return
	 */
	public String getCategoryTerm() {
		return categoryName;
	}	

}
