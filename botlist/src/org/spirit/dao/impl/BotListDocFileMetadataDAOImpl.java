/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import org.spirit.bean.impl.BotListDocFileMetadata;
import org.spirit.dao.BotListDocFileMetadataDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListDocFileMetadataDAOImpl extends HibernateDaoSupport implements BotListDocFileMetadataDAO {

	/**
	 * @see org.spirit.dao.BotListUserLinkDAO#createLink(org.spirit.bean.impl.BotListUserLink)
	 */
	public void createDocFile(BotListDocFileMetadata metadata) {
		getHibernateTemplate().save(metadata);
	}
	
}
