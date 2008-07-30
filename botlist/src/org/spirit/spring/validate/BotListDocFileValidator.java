/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.spirit.form.BotListDocFileForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListDocFileValidator implements Validator {
	
	//private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListDocFileForm.class.isAssignableFrom(post);
	}
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		BotListDocFileForm form = (BotListDocFileForm) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"title", "required", "* Title Field is required.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"userSolution", "required", "* Please add the following values.");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"uploadFilenameFirst", "required", "* Document Text File Upload is Required.");
		
		if (form.getUploadFilenameFirst() != null) {
			String filename = form.getUploadFilenameFirst().getOriginalFilename();
			if (filename == null || filename.length() == 0) {
				errors.reject("uploadFilenameFirst", "* Document Text File Upload is Required.");
			}
		}
		
		if ((form != null) 
					&& (form.getUserSolution() != null)
					&& (form.getUserSolution() != null)) {
			
			if (form.getPrevSolution().intValue() != form.getUserSolution().intValue()) {
				errors.reject("userSolution", "* Please add the following values.");
			}
		}		
	}

}
