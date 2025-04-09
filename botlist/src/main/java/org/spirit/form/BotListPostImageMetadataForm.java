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

public class BotListPostImageMetadataForm extends BotListBaseForm {

		/**
	 * 
	 */
	private static final long serialVersionUID = -1714480262995977202L;
		private String imageOriginalname;
		private Long adlistId;
		private Long imageFilesize;
		private String imageFilename;		
		
		/**
		 * @return the adlistId
		 */
		public Long getAdlistId() {
			return adlistId;
		}
		/**
		 * @param adlistId the adlistId to set
		 */
		public void setAdlistId(Long adlistId) {
			this.adlistId = adlistId;
		}
		
		/**
		 * @return the imageFilename
		 */
		public String getImageFilename() {
			return imageFilename;
		}
		/**
		 * @param imageFilename the imageFilename to set
		 */
		public void setImageFilename(String imageFilename) {
			this.imageFilename = imageFilename;
		}
		/**
		 * @return the imageFilesize
		 */
		public Long getImageFilesize() {
			return imageFilesize;
		}
		/**
		 * @param imageFilesize the imageFilesize to set
		 */
		public void setImageFilesize(Long imageFilesize) {
			this.imageFilesize = imageFilesize;
		}
		/**
		 * @return the imageOriginalname
		 */
		public String getImageOriginalname() {
			return imageOriginalname;
		}
		/**
		 * @param imageOriginalname the imageOriginalname to set
		 */
		public void setImageOriginalname(String imageOriginalname) {
			this.imageOriginalname = imageOriginalname;
		}
		
}
