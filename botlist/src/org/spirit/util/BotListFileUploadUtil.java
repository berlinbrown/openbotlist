/**
 * Berlin Brown
 * Dec 24, 2006
 */
package org.spirit.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListFileUploadUtil implements BotListFileUploadType {

	private String uploadDir;
	
	private int maxFileSize;
	
	private int maxMemorySize;
	
	private boolean fileUploadEnabled;
	
	/**
	 * @return the maxMemorySize
	 */
	public int getMaxMemorySize() {
		return maxMemorySize;
	}

	/**
	 * @param maxMemorySize the maxMemorySize to set
	 */
	public void setMaxMemorySize(int maxMemorySize) {
		this.maxMemorySize = maxMemorySize;
	}

	/**
	 * @return the uploadDir
	 */
	public String getUploadDir() {
		return uploadDir;
	}

	/**
	 * @param uploadDir the uploadDir to set
	 */
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	private void processUploadedFile(FileItem item) throws Exception {
		String fieldName = item.getFieldName();
	    String fileName = item.getName();
	    String contentType = item.getContentType();
	    boolean isInMemory = item.isInMemory();
	    long sizeInBytes = item.getSize();
		
	    if (this.isFileUploadEnabled()) {
	    	File uploadedFile = new File(this.getUploadDir() + File.pathSeparator + fileName);
	        item.write(uploadedFile);
	    }
	}
	
	public int uploadFiles(HttpServletRequest request)
				throws Exception {
		
		int numberFilesUploaded = 0;
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Set factory constraints
		// Maximum size that will be stored in memory
		// For example, 4096
		factory.setSizeThreshold(this.getMaxMemorySize());
		factory.setRepository(new File(this.getUploadDir()));
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint (600*1024) = 600k
		upload.setSizeMax(this.getMaxFileSize());
		
		// Parse the reuqest
		List items = upload.parseRequest(request);
		
		// Process the uploaded items
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();

		    if (item.isFormField()) {
		        //processFormField(item);
		    } else {
		        processUploadedFile(item);
		        numberFilesUploaded++;
		    }
		    // Although this storage will be deleted automatically 
		    // when the FileItem instance is garbage collected, this method can be used to ensure that this is done at an earlier time, thus preserving system resources.
		    item.delete();
		}
		
		return numberFilesUploaded;
		
	}

	/**
	 * @return the maxFileSize
	 */
	public int getMaxFileSize() {
		return maxFileSize;
	}

	/**
	 * @param maxFileSize the maxFileSize to set
	 */
	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	/**
	 * @return the fileUploadEnabled
	 */
	public boolean isFileUploadEnabled() {
		return fileUploadEnabled;
	}

	/**
	 * @param fileUploadEnabled the fileUploadEnabled to set
	 */
	public void setFileUploadEnabled(boolean fileUploadEnabled) {
		this.fileUploadEnabled = fileUploadEnabled;
	}
}
