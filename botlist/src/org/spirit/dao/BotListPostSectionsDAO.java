/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao;

import java.util.List;

import org.spirit.bean.impl.BotListCityListing;


/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public interface BotListPostSectionsDAO {

	public List listSections(final String queryStr, final BotListCityListing city);
}
