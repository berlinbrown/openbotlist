/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListDocFile;
import org.spirit.dao.BotListDocFileDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListDocFileDAOImpl extends HibernateDaoSupport implements BotListDocFileDAO {

	/**
	 * @see org.spirit.dao.BotListUserLinkDAO#createLink(org.spirit.bean.impl.BotListUserLink)
	 */
	public void createDocFile(BotListDocFile file) {
		getHibernateTemplate().save(file);
	}
	
	
	/**
	 * @see org.spirit.dao.BotListEntityLinksDAO#listEntityLinks(java.lang.String)
	 */
	public List listFiles() {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						String querystr = "from org.spirit.bean.impl.BotListDocFile file order by file.id";
						Query query = session.createQuery(querystr);

						// Set the maximum results
						query.setMaxResults(100);
						return query.list();
					}
				});		
	} 
	
	public List listFilesHistory() {
		return getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						String querystr = "from org.spirit.bean.impl.BotListDocFile file order by file.id desc";
						Query query = session.createQuery(querystr);

						// Set the maximum results
						query.setMaxResults(8);
						return query.list();
					}
				});		
	} 
	
}
