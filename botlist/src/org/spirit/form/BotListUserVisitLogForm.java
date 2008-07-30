/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.Calendar;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListUserVisitLogForm extends BotListBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3245320685719651499L;
	private String requestPage;
	private String host;
	private Calendar createdOn;
	private String referer;
	private String userAgent;
	private String remoteHost;

	private String requestUri;
	/**
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}	
	/**
	 * @return the referer
	 */
	public String getReferer() {
		return referer;
	}
	/**
	 * @param referer the referer to set
	 */
	public void setReferer(String referer) {
		this.referer = referer;
	}
	/**
	 * @return the remoteHost
	 */
	public String getRemoteHost() {
		return remoteHost;
	}
	/**
	 * @param remoteHost the remoteHost to set
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	/**
	 * @return the requestPage
	 */
	public String getRequestPage() {
		return requestPage;
	}
	/**
	 * @param requestPage the requestPage to set
	 */
	public void setRequestPage(String requestPage) {
		this.requestPage = requestPage;
	}
	/**
	 * @return the requestUri
	 */
	public String getRequestUri() {
		return requestUri;
	}
	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}
	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}


}
