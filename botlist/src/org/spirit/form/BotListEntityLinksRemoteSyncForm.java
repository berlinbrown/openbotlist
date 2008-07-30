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
 */
public class BotListEntityLinksRemoteSyncForm extends BotListBaseForm {
	
	private static final long serialVersionUID = 8658902729040659129L;
	
	private String developerKey;
	private String remoteData; 
	private String remoteSyncKey;
	private String responseMessage;
	
	public String getRemoteData() {
		return remoteData;
	}
	public void setRemoteData(String remoteData) {
		this.remoteData = remoteData;
	}
	public String getRemoteSyncKey() {
		return remoteSyncKey;
	}
	public void setRemoteSyncKey(String remoteSyncKey) {
		this.remoteSyncKey = remoteSyncKey;
	}
	public String getDeveloperKey() {
		return developerKey;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
			
}
