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

public class BotListAdminMainBannerForm extends BotListBaseForm {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String headline;
		private String section;
		private String enabled;
		
		public String getEnabled() {
			return enabled;
		}
		public void setEnabled(String enabled) {
			this.enabled = enabled;
		}
		public String getHeadline() {
			return headline;
		}
		public void setHeadline(String headline) {
			this.headline = headline;
		}
		public String getSection() {
			return section;
		}
		public void setSection(String section) {
			this.section = section;
		}


}
