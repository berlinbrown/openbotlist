/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Jan 9, 2007
 */
package org.spirit.form;

import java.util.Map;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListVisitLogStatsForm  extends BotListBaseForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5392393572244933059L;
	
	private Long weekVisits;
	private Map weekStats;
	private Map weekStatsDates;
	
	/**
	 * @return the weekStatsDates
	 */
	public Map getWeekStatsDates() {
		return weekStatsDates;
	}

	/**
	 * @param weekStatsDates the weekStatsDates to set
	 */
	public void setWeekStatsDates(Map weekStatsDates) {
		this.weekStatsDates = weekStatsDates;
	}

	/**
	 * @return the weekStats
	 */
	public Map getWeekStats() {
		return weekStats;
	}

	/**
	 * @param weekStats the weekStats to set
	 */
	public void setWeekStats(Map weekStats) {
		this.weekStats = weekStats;
	}

	/**
	 * @return the weekVisits
	 */
	public Long getWeekVisits() {
		return weekVisits;
	}

	/**
	 * @param weekVisits the weekVisits to set
	 */
	public void setWeekVisits(Long weekVisits) {
		this.weekVisits = weekVisits;
	}

}
