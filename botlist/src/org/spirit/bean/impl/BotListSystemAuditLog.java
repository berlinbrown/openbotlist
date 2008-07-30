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

public class BotListSystemAuditLog extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5867670280790453519L;
	private String applicationName;
	private String message;
	private String logLevel;
	private String messageId;
	private String sendTo;
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}


}
