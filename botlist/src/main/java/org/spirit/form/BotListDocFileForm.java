/**
 * Berlin Brown
 * Nov 9, 2006
 */

package org.spirit.form;

import java.util.List;

import org.spirit.form.base.BotListBaseCalcVerify;
import org.spirit.form.base.BotListBaseForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * This is class is used by botverse.
 * 
 * @author Berlin Brown
 * 
 */

public class BotListDocFileForm 
			extends BotListBaseForm implements BotListBaseCalcVerify {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4187392456601625704L;
		private Long childId;
		private String message;
		private String fullName;
		private String title;
				
		private Long firstInput;
		private Long secondInput;
		private Long solution;
		private Long userSolution;
		private Long prevSolution;
		
		private List files;
		
		private MultipartFile uploadFilenameFirst;
		private MultipartFile uploadFilenameSecond;
		
		/**
		 * @return the uploadFilenameFirst
		 */
		public MultipartFile getUploadFilenameFirst() {
			return uploadFilenameFirst;
		}
		/**
		 * @param uploadFilenameFirst the uploadFilenameFirst to set
		 */
		public void setUploadFilenameFirst(MultipartFile uploadFilenameFirst) {
			this.uploadFilenameFirst = uploadFilenameFirst;
		}
		/**
		 * @return the uploadFilenameSecond
		 */
		public MultipartFile getUploadFilenameSecond() {
			return uploadFilenameSecond;
		}
		/**
		 * @param uploadFilenameSecond the uploadFilenameSecond to set
		 */
		public void setUploadFilenameSecond(MultipartFile uploadFilenameSecond) {
			this.uploadFilenameSecond = uploadFilenameSecond;
		}
		/**
		 * @return the childId
		 */
		public Long getChildId() {
			return childId;
		}
		/**
		 * @param childId the childId to set
		 */
		public void setChildId(Long childId) {
			this.childId = childId;
		}
		/**
		 * @return the fullName
		 */
		public String getFullName() {
			return fullName;
		}
		/**
		 * @param fullName the fullName to set
		 */
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}
		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}
		/**
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}
		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}
		/**
		 * @return the firstInput
		 */
		public Long getFirstInput() {
			return firstInput;
		}
		/**
		 * @param firstInput the firstInput to set
		 */
		public void setFirstInput(Long firstInput) {
			this.firstInput = firstInput;
		}
		/**
		 * @return the prevSolution
		 */
		public Long getPrevSolution() {
			return prevSolution;
		}
		/**
		 * @param prevSolution the prevSolution to set
		 */
		public void setPrevSolution(Long prevSolution) {
			this.prevSolution = prevSolution;
		}
		/**
		 * @return the secondInput
		 */
		public Long getSecondInput() {
			return secondInput;
		}
		/**
		 * @param secondInput the secondInput to set
		 */
		public void setSecondInput(Long secondInput) {
			this.secondInput = secondInput;
		}
		/**
		 * @return the solution
		 */
		public Long getSolution() {
			return solution;
		}
		/**
		 * @param solution the solution to set
		 */
		public void setSolution(Long solution) {
			this.solution = solution;
		}
		/**
		 * @return the userSolution
		 */
		public Long getUserSolution() {
			return userSolution;
		}
		/**
		 * @param userSolution the userSolution to set
		 */
		public void setUserSolution(Long userSolution) {
			this.userSolution = userSolution;
		}
		/**
		 * @return the files
		 */
		public List getFiles() {
			return files;
		}
		/**
		 * @param files the files to set
		 */
		public void setFiles(List files) {
			this.files = files;
		}


}
