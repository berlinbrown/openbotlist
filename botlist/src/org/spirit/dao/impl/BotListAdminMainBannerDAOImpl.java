/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListAdminMainBanner;
import org.spirit.dao.BotListAdminMainBannerDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListAdminMainBannerDAOImpl extends HibernateDaoSupport implements BotListAdminMainBannerDAO {
	
	/**
	 * @see org.spirit.dao.BotListUserLinkDAO#createLink(org.spirit.bean.impl.BotListUserLink)
	 */
	public void createBanner(BotListAdminMainBanner banner) {
		getHibernateTemplate().save(banner);
	}
	
	public BotListAdminMainBanner readBanner(final String section) throws DataAccessException {		
		return (BotListAdminMainBanner) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						//Query query = session.createQuery("from org.spirit.bean.impl.BotListAdminMainBanner banner where banner.section = :section and banner.enabled = 'Y'");
						Query query = session.createQuery("from org.spirit.bean.impl.BotListAdminMainBanner banner order by banner.id desc");
						//query.setString("section", section);
						query.setMaxResults(1);
						return query.uniqueResult();
					}
				});		
	}	
}
