/**
 * Berlin Brown
 * Nov 15, 2006
 */
package org.spirit.dao.base;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public abstract class BotListDAOBase {
	
	private String findQuery = null;	
		
	/**
	 * @return the findFileQuery
	 */
	public String getFindQuery() {
		if (findQuery == null) {
			return getDefaultFindQuery();
		}
		return findQuery;
	}

	/**
	 * @param findFileQuery the findFileQuery to set
	 */
	public void setFindFileQuery(String findFileQuery) {
		this.findQuery = findFileQuery;
	}
	
	/**
	 * Default Query.	 
	 */
	public abstract String getDefaultFindQuery();
}
