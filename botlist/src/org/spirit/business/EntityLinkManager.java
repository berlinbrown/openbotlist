/* 
 * EnityLinkManager.java
 * Nov 16, 2007
 */
package org.spirit.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.spirit.bean.impl.BotListEntityLinks;
import org.spirit.dao.BotListEntityLinksDAO;
import org.spirit.util.BotListGenericUtils;

/**
 * @author bbrown
 */
public class EntityLinkManager {
	
	/**
	 * Return all links found in the last 24 hours.	 
	 */
	public static List readLinksForDay(final BotListEntityLinksDAO dao) {
		// Return current time.
		Calendar curCal = Calendar.getInstance();		
		curCal.add(Calendar.DATE, -2);
		return dao.readListingUpToCurrentDate(curCal);
	}
	
	/**
	 * Map and Reduce all entity link keywords.
	 */
	public static Set mapReduceLinkKeywords(final BotListEntityLinksDAO dao) {
		List allterms = new ArrayList();
		List list = readLinksForDay(dao);
		for (Iterator it = list.iterator(); it.hasNext();) {
			BotListEntityLinks link = (BotListEntityLinks) it.next();
			final String keywords = link.getKeywords();
			if (keywords != null) {
				final String[] words = keywords.split(" ");
				if (words != null) {
					for (int i = 0; i < words.length; i++) {						
						// Also check for stop words
						if ((BotListGenericUtils.STOP_WORDS_MAP.get(words[i]) == null) 
							&& (words[i].length() > 1)) {
							allterms.add(words[i]);
						} // End of if - stop word check
					}
				} // End of if - words null
			} // End of if
		} // End of For	
		return BotListGenericUtils.mapReduce(allterms, 8);
	}
}
