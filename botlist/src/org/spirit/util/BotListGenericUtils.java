/* 
 * BotListGenericUtils.java
 * Nov 16, 2007
 */
package org.spirit.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author bbrown
 */
public class BotListGenericUtils {
		
	public static final String STOP_WORDS [] = {
		"to",
		"the",
		"in",
		"of",
		"am",
		"add",
		"is",
		"for",
		"a",
		"on",
		"by",
		"for",
		"us",
		"we",
		"be",
		"going",
		"way",
		"from",
		"cool",
		"sometimes",
		"too",
		"man",
		"bad",
		"and",
		"new",
		"i",
		"with",
		"it",
		"all",
		"up",
		"at",
		"over",
		"says",
		"more",
		"2007",
		"your",
		"no",
		"call",
		"race"
	};
	public static final Map STOP_WORDS_MAP;
	static {
		STOP_WORDS_MAP = new HashMap();
		for (int ix = 0; ix < STOP_WORDS.length; ix++) {
			STOP_WORDS_MAP.put(STOP_WORDS[ix], "0");
		}
	}
	
	/** inner class to sort map **/
	private static final class ValueComparator implements Comparator {
		private Map data = null;
		public ValueComparator(Map _data) {
			super();
			this.data = _data;
		}
		public int compare(Object o1, Object o2) {
			Integer e1;
			Integer e2;
			e1 = (Integer) this.data.get(o1);
			e2 = (Integer) this.data.get(o2);			
			int res = e1.compareTo(e2);			
			return -(res == 0 ? 1 : res);
		}
	}
	
	public static final Map sortMapByValue(Map inputMap) {
		SortedMap sortedMap = new TreeMap(new BotListGenericUtils.ValueComparator(inputMap));		
		sortedMap.putAll(inputMap);		
		return sortedMap;
	}

	/**
	 * Return a list of key value (instances of map) pairs.
	 * @param inputMap
	 * @return
	 */
	public static final Set keyValueSet(final Map inputMap, final int maxnum) {
		Set set = inputMap.entrySet();
		Set newset = new LinkedHashSet();		
		int i = 0;
		for (Iterator it = set.iterator(); it.hasNext(); i++) {
			newset.add(it.next());
			if (i >= (maxnum - 1)) break;
		}
		return newset;
	}
	
	/**
	 * Simple Map Reduce; given a list of keywords, map the terms to a count of how
	 * many times the term occurs in the list.
	 *  
	 * @param allterms
	 * @return
	 */
	public static final Set mapReduce(final List allterms, final int maxnum) {
		Map map = new HashMap();
		for (Iterator x2it = allterms.iterator(); x2it.hasNext();) {
			final String term = (String) x2it.next();
			if (term.length() == 0) continue;
			Integer ct = (Integer) map.get(term);
			if (ct == null) {
				map.put(term, new Integer(0));
			} else {
				map.put(term, new Integer(ct.intValue() + 1));
			} // End of if - else			
		} // End of the for						
		Map sortedMap = BotListGenericUtils.sortMapByValue(map);	
		return BotListGenericUtils.keyValueSet(sortedMap, maxnum);
	}
}
