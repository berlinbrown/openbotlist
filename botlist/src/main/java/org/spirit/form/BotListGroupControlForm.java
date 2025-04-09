/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListGroupControlForm extends BotListBaseForm {

		/**
	 * 
	 */
	private static final long serialVersionUID = 6668365748849957506L;
	private String groupUid;
	private String shortDescr;
	private String longDescr;
	private String groupName;
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the groupUid
	 */
	public String getGroupUid() {
		return groupUid;
	}
	/**
	 * @param groupUid the groupUid to set
	 */
	public void setGroupUid(String groupUid) {
		this.groupUid = groupUid;
	}
	/**
	 * @return the longDescr
	 */
	public String getLongDescr() {
		return longDescr;
	}
	/**
	 * @param longDescr the longDescr to set
	 */
	public void setLongDescr(String longDescr) {
		this.longDescr = longDescr;
	}
	/**
	 * @return the shortDescr
	 */
	public String getShortDescr() {
		return shortDescr;
	}
	/**
	 * @param shortDescr the shortDescr to set
	 */
	public void setShortDescr(String shortDescr) {
		this.shortDescr = shortDescr;
	}


}
