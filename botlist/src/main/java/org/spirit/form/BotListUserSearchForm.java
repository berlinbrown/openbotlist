/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.List;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserSearchForm extends BotListBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 52860635664779701L;
	
	private String searchEntry;
	
	private List listings;

	/**
	 * @return the listings
	 */
	public List getListings() {
		return listings;
	}

	/**
	 * @param listings the listings to set
	 */
	public void setListings(List listings) {
		this.listings = listings;
	}

	/**
	 * @return the searchEntry
	 */
	public String getSearchEntry() {
		return searchEntry;
	}

	/**
	 * @param searchEntry the searchEntry to set
	 */
	public void setSearchEntry(String searchEntry) {
		this.searchEntry = searchEntry;
	}

}
