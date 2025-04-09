/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.spirit.bean.impl.BotListUserVisitLog;
import org.spirit.dao.BotListUserVisitLogDAO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserVisitLogDAOImpl extends HibernateDaoSupport 
			implements BotListUserVisitLogDAO {
	
	private Log log = LogFactory.getLog(getClass());	
	/**
	 * @see org.spirit.dao.BotListUserVisitAuditDAO#createVisitAudit(org.spirit.bean.impl.BotListUserVisitAudit)
	 */
	public void createVisitLog(BotListUserVisitLog link) {		
		getHibernateTemplate().save(link);		
	}	
	/**
	 * Get the last total visitors given a particular date.
	 */
	public long getVisitAuditDate(final Calendar calendar) {			
			
		// Or we could simply do:
		// ( (Integer) session.iterate("select count(*) from ....").next() ).intValue();		
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("select count(id) from org.spirit.bean.impl.BotListUserVisitLog log where log.createdOn > :lastWeek");
						query.setCalendar("lastWeek", calendar); 
						return query.uniqueResult();
					}
				});
		return count.longValue();
	}
	
	/**
	 * Get the last total visitors given a particular date.
	 */
	public long getVisitAuditOnDate(final Calendar calendar) {			
		// Or we could simply do:
		// ( (Integer) session.iterate("select count(*) from ....").next() ).intValue();		
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException {
						Query query = 
							session.createQuery("select count(id) from org.spirit.bean.impl.BotListUserVisitLog log where date(log.createdOn) = date(:curDate)");
						query.setCalendar("curDate", calendar); 
						return query.uniqueResult();
					}
				});
		return count.longValue();
	}
	
	
	/**
	 * Get the total users visited.
	 */	
	public long getVisitAuditCount() {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(id) from org.spirit.bean.impl.BotListUserVisitLog");
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
	public Calendar getFirstVisit() {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select min(log.createdOn) from org.spirit.bean.impl.BotListUserVisitLog log");
		if (list == null)
			return null;		
		if (list.get(0) instanceof java.util.Calendar) {
			return ((Calendar) list.get(0));
		} else {
			return null;
		}		
	}
	
	public long getUniqueVisitAuditCount() {
		ArrayList list = (ArrayList) getHibernateTemplate().find("select count(distinct remoteHost) from org.spirit.bean.impl.BotListUserVisitLog");
		if (list == null)
			return -1;		
		if (list.get(0) instanceof java.lang.Integer) {
			return ((Integer) list.get(0)).longValue();
		} else {
			return -1;
		}
	}
	
}
