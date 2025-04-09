/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListPostImageMetadata extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -190232106398749924L;

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
