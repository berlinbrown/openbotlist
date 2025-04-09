/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.bean.impl;

import java.io.Serializable;
import java.util.Calendar;

import org.spirit.bean.impl.base.BotListBeanBase;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListDeveloperUsers extends BotListBeanBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7818823035543044473L;
	private Long userId;
	private String description;
	private String enabled;
	private Calendar lastLoginFailure;
	private String keyId;
	private String userName;
	private Calendar lastLoginSuccess;
	private String lastApplication;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getLastApplication() {
		return lastApplication;
	}
	public void setLastApplication(String lastApplication) {
		this.lastApplication = lastApplication;
	}
	public Calendar getLastLoginFailure() {
		return lastLoginFailure;
	}
	public void setLastLoginFailure(Calendar lastLoginFailure) {
		this.lastLoginFailure = lastLoginFailure;
	}
	public Calendar getLastLoginSuccess() {
		return lastLoginSuccess;
	}
	public void setLastLoginSuccess(Calendar lastLoginSuccess) {
		this.lastLoginSuccess = lastLoginSuccess;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
