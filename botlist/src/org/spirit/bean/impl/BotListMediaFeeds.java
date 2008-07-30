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
public class BotListMediaFeeds extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5026789124795526420L;
	private Float rating;
	private String mediaDescr;
	private String author;
	private Long views;
	private String orginalImgurl;
	private Long validity;
	private Long ratingCount;
	private String mediaTitle;
	private String imageThumbnail;
	private String mediaType;
	private String keywords;
	private Long systemId;
	private Long processCount;
	private String mediaUrl;
	private String imageFilename;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImageFilename() {
		return imageFilename;
	}
	public void setImageFilename(String imageFilename) {
		this.imageFilename = imageFilename;
	}
	public String getImageThumbnail() {
		return imageThumbnail;
	}
	public void setImageThumbnail(String imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getMediaDescr() {
		return mediaDescr;
	}
	public void setMediaDescr(String mediaDescr) {
		this.mediaDescr = mediaDescr;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMediaUrl() {
		return mediaUrl;
	}
	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	public String getOrginalImgurl() {
		return orginalImgurl;
	}
	public void setOrginalImgurl(String orginalImgurl) {
		this.orginalImgurl = orginalImgurl;
	}
	public Long getProcessCount() {
		return processCount;
	}
	public void setProcessCount(Long processCount) {
		this.processCount = processCount;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public Long getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(Long ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getValidity() {
		return validity;
	}
	public void setValidity(Long validity) {
		this.validity = validity;
	}
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}

}
