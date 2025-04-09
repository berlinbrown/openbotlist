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

public class BotListActiveMediaFeedsForm extends BotListBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1092471052803418255L;
	private String displayType;
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}


}
