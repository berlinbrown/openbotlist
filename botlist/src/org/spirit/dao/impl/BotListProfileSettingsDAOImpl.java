/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListProfileSettings;
import org.spirit.dao.BotListProfileSettingsDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown 
 */
public class BotListProfileSettingsDAOImpl extends HibernateDaoSupport implements BotListProfileSettingsDAO {
	
	public void createSettings(BotListProfileSettings settings) {
		getHibernateTemplate().saveOrUpdate(settings);
	}
	
	public BotListProfileSettings readProfile(final int id) throws DataAccessException {			
		return (BotListProfileSettings) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("from org.spirit.bean.impl.BotListProfileSettings profile where profile.userId = :userId");
							query.setInteger("userId", id);
						return query.uniqueResult();
					}
				});		
	}
	
}
