/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.spirit.form.BotListUserCommentsForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListTopicValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListUserCommentsForm.class.isAssignableFrom(post);
	}	
	
	/**
	 * Check for a valid url entry.
	 */
	private boolean isValidUrl(String url) {
		String[] schemes = { "http","https" };
		UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);		    
		return urlValidator.isValid(url);
	}
	
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		BotListUserCommentsForm form = (BotListUserCommentsForm) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"message", "required", "* Message is required.");						
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"fullName", "required", "* A name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"subject", "required", "* A topic subject is required.");
		
		if ((form != null) 
					&& (form.getUserSolution() != null)
					&& (form.getUserSolution() != null)) {
				
				if (form.getPrevSolution().intValue() != form.getUserSolution().intValue()) {
					errors.reject("userSolution", "* Please add the following values.");
				}
		}	
				
	}

}
