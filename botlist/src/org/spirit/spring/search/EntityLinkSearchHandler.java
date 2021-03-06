/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * May 13, 2007
 *
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
 *	    * Neither the name of the Botnode.com (Berlin Brown) nor 
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
package org.spirit.spring.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.spirit.bean.impl.BotListEntityLinks;
import org.spirit.spring.errors.InvalidBusinessObjectException;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class EntityLinkSearchHandler extends IndexSearchHandler {
	
	public List searchScore(String queryLine) throws Exception {
		return this.search(queryLine, null);
	}
	
	/**
	 * Default search, sort by score and date
	 */
	public List search(String queryLine) throws Exception {
		Sort sort = new Sort();		
		SortField fields [] = {
			SortField.FIELD_SCORE,
			new SortField("yyyymmdd", SortField.STRING, true)
		};
		sort.setSort(fields);
		return this.search(queryLine, sort);
	}
	
	public List search(String queryLine, Sort sort) throws Exception {

		List res = new ArrayList();
		IndexReader reader = this.getNormsReader(this.getIndexDir());			
		if (this.getEntityLinksDao() == null) {
			throw new InvalidBusinessObjectException("Invalid Search Handler (error with entity link DAO bean.");
		}
		Searcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer();

		String [] fields = this.getSearchFields();
		MultiFieldQueryParser parser = new MultiFieldQueryParser( fields, analyzer);
		String finalQuery = queryLine + this.getSearchPostfix();
		Query query = parser.parse(finalQuery);
		
		Hits hits = null;
		if (sort != null)
			hits = searcher.search(query, sort);
		else
			hits = searcher.search(query);			
		
		//for (int start = 0; start < hits.length(); start += this.hitsPerPage) {
		int start = 0;
		int end = Math.min(hits.length(), start + this.getHitsPerPage());
		for (int i = start; i < end; i++) {
			Document doc = hits.doc(i);
			//String userName = doc.get(USER_NAME);
			//String title = doc.get(URL_TITLE);	         		    	 	        	
			String id = doc.get(IDENTITY);
			String searchScore = "" + hits.score(i);
			BotListEntityLinks link = this.getEntityLinksDao().readLinkListing(id);
			link.setSearchScore(searchScore);
			res.add(link);
		}	     
		return res;
	}	
}
