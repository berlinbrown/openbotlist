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

public class BotListDocFileMetadataForm extends BotListBaseForm {

		private Long docFilesize;
		private String docOriginalname;
		private Long documentId;
		private String docFilename;
		/**
		 * @return the docFilename
		 */
		public String getDocFilename() {
			return docFilename;
		}
		/**
		 * @param docFilename the docFilename to set
		 */
		public void setDocFilename(String docFilename) {
			this.docFilename = docFilename;
		}
		/**
		 * @return the docFilesize
		 */
		public Long getDocFilesize() {
			return docFilesize;
		}
		/**
		 * @param docFilesize the docFilesize to set
		 */
		public void setDocFilesize(Long docFilesize) {
			this.docFilesize = docFilesize;
		}
		/**
		 * @return the docOriginalname
		 */
		public String getDocOriginalname() {
			return docOriginalname;
		}
		/**
		 * @param docOriginalname the docOriginalname to set
		 */
		public void setDocOriginalname(String docOriginalname) {
			this.docOriginalname = docOriginalname;
		}
		/**
		 * @return the documentId
		 */
		public Long getDocumentId() {
			return documentId;
		}
		/**
		 * @param documentId the documentId to set
		 */
		public void setDocumentId(Long documentId) {
			this.documentId = documentId;
		}


}
