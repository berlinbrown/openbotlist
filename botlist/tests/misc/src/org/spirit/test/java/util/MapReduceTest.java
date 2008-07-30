/* 
 * EntityLinkTest.java
 * Aug 19, 2007
 */
package org.spirit.test.java.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.spirit.util.BotListGenericUtils;

/**
 * @author bbrown
 */
public class MapReduceTest extends TestCase {
	
	protected void setUp() {	
	}
	protected void tearDown() {	
	}
	
	public void testMapReduce() {
		List allterms = new ArrayList();
		String key1 = "dog";
		String key2 = "to";
		
		allterms.add(key1);
		allterms.add("cat");
		allterms.add("chicken");
		allterms.add("dog");
		if (BotListGenericUtils.STOP_WORDS_MAP.get(key2) == null) {
			allterms.add(key2);
		}
		if (BotListGenericUtils.STOP_WORDS_MAP.get("doggie") == null) {
			allterms.add("doggie");
		}
		
		allterms.remove("dog");
		final Set set = BotListGenericUtils.mapReduce(allterms, 8);		
		System.out.println(set);				
	}

}
