/* 
 * Created on Nov 6, 2006
 * 
 */
package org.spirit.dao.impl;

import org.spirit.bean.impl.BotListUserLink;
import org.spirit.dao.BotListUserLinkDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * @author AP417
 *
 */
public class BotListUserLinkDAOImpl extends HibernateDaoSupport 
			implements BotListUserLinkDAO {

	/**
	 * @see org.spirit.dao.BotListUserLinkDAO#createLink(org.spirit.bean.impl.BotListUserLink)
	 */
	public void createLink(BotListUserLink link) {
		getHibernateTemplate().save(link);
	}

}
