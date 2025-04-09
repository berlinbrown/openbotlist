/**
 * Berlin Brown
 * Dec 25, 2006
 */
package org.spirit.util;

import javax.servlet.http.HttpServletRequest;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public interface BotListFileUploadType {
	
	/**
	 * @return the maxMemorySize
	 */
	public int getMaxMemorySize();
	
	/**
	 * @param maxMemorySize the maxMemorySize to set
	 */
	public void setMaxMemorySize(int maxMemorySize);

	/**
	 * @return the uploadDir
	 */
	public String getUploadDir();

	/**
	 * @param uploadDir the uploadDir to set
	 */
	public void setUploadDir(String uploadDir);
	
	/**
	 * @return the maxFileSize
	 */
	public int getMaxFileSize();

	/**
	 * @param maxFileSize the maxFileSize to set
	 */
	public void setMaxFileSize(int maxFileSize);

	/**
	 * @return the fileUploadEnabled
	 */
	public boolean isFileUploadEnabled();

	/**
	 * @param fileUploadEnabled the fileUploadEnabled to set
	 */
	public void setFileUploadEnabled(boolean fileUploadEnabled);
	
	public int uploadFiles(HttpServletRequest request) throws Exception;
}
