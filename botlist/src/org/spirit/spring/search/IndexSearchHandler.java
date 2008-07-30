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

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.FilterIndexReader;
import org.apache.lucene.index.IndexReader;
import org.spirit.dao.BotListCoreSettings;
import org.spirit.dao.BotListEntityLinksDAO;
import org.spirit.spring.errors.InvalidBusinessObjectException;
import org.springframework.context.ApplicationContext;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public abstract class IndexSearchHandler {
	
	/**
	 * Postfix for example, fuzzy query search modifier.
	 */
	public static final String DEFAULT_SEARCH_POSTFIX = "~0.43";
	
	public static final int DEFAULT_HITS_PER_PAGE = 40;
	
	public static final String USER_NAME = "full_name";
	public static final String KEYWORDS = "keywords";
	public static final String URL_TITLE = "url_title";
	public static final String IDENTITY = "id";
		
			
	private BotListCoreSettings settings;
	private BotListEntityLinksDAO entityLinksDao;
	
	private String indexDir;
	private String globalIndexDir;
	private String normsField;	
	
	private String searchPostfix = DEFAULT_SEARCH_POSTFIX;
	
	private int hitsPerPage = DEFAULT_HITS_PER_PAGE;
	private String [] searchFields = { URL_TITLE, USER_NAME, KEYWORDS };
	
	public abstract List search(String queryLine) throws Exception;
	
	public IndexReader getNormsReader(String curIndexDir) throws IOException {
		IndexReader reader = IndexReader.open(curIndexDir);
		if (normsField != null)			
			reader = new OneNormsReader(reader, normsField);
		return reader;
	}
	
	/**
	 * @return the settings
	 */
	public BotListCoreSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(BotListCoreSettings settings) {
		this.settings = settings;
	}

	/**
	 * Set the core settings object from the application context.  Also, set the
	 * DAO entity link object.  Set the indexing directory.
	 * 
	 * @param ac
	 */
	public void setSettings(ApplicationContext ac) {
		if (ac != null) {
			BotListCoreSettings s = (BotListCoreSettings) ac.getBean("coreSettings");			
			if (s != null) {
				this.setSettings(s);
				this.indexDir = s.getSearchIndexDir();
				this.globalIndexDir = s.getGlobalIndexDir();
			} else {
				throw new InvalidBusinessObjectException("ERR: could not create search handler, invalid core settings.");
			}
			entityLinksDao = (BotListEntityLinksDAO) ac.getBean("entityLinksDaoBean");
			if (entityLinksDao != null) {
				this.setEntityLinksDao(entityLinksDao);
			} else {
				throw new InvalidBusinessObjectException("ERR: could not create search handler, entity link DAO bean.");
			}
		}
	}
	
	private static class OneNormsReader extends FilterIndexReader {
		private String field;
		public OneNormsReader(IndexReader in, String field) {
			super(in);
			this.field = field;
		}
		public byte[] norms(String field) throws IOException {
			return in.norms(this.field);
		}
	}

	/**
	 * @return the hitsPerPage
	 */
	public int getHitsPerPage() {
		return hitsPerPage;
	}

	/**
	 * @param hitsPerPage the hitsPerPage to set
	 */
	public void setHitsPerPage(int hitsPerPage) {
		this.hitsPerPage = hitsPerPage;
	}

	/**
	 * @return the entityLinksDao
	 */
	public BotListEntityLinksDAO getEntityLinksDao() {
		return entityLinksDao;
	}

	/**
	 * @param entityLinksDao the entityLinksDao to set
	 */
	public void setEntityLinksDao(BotListEntityLinksDAO entityLinksDao) {
		this.entityLinksDao = entityLinksDao;
	}

	/**
	 * @return the searchFields
	 */
	public String[] getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchFields the searchFields to set
	 */
	public void setSearchFields(String[] searchFields) {
		this.searchFields = searchFields;
	}

	/**
	 * @return the globalIndexDir
	 */
	public String getGlobalIndexDir() {
		return globalIndexDir;
	}

	/**
	 * @param globalIndexDir the globalIndexDir to set
	 */
	public void setGlobalIndexDir(String globalIndexDir) {
		this.globalIndexDir = globalIndexDir;
	}

	/**
	 * @return the indexDir
	 */
	public String getIndexDir() {
		return indexDir;
	}

	/**
	 * @param indexDir the indexDir to set
	 */
	public void setIndexDir(String indexDir) {
		this.indexDir = indexDir;
	}

	/**
	 * @return the normsField
	 */
	public String getNormsField() {
		return normsField;
	}

	/**
	 * @param normsField the normsField to set
	 */
	public void setNormsField(String normsField) {
		this.normsField = normsField;
	}

	/**
	 * @return the searchPostfix
	 */
	public String getSearchPostfix() {
		return searchPostfix;
	}

	/**
	 * @param searchPostfix the searchPostfix to set
	 */
	public void setSearchPostfix(String searchPostfix) {
		this.searchPostfix = searchPostfix;
	}

}
