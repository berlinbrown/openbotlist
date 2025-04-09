/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.EmailValidator;
import org.apache.commons.validator.UrlValidator;
import org.spirit.form.BotListPostListingForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListPostListingValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListPostListingForm.class.isAssignableFrom(post);
	}

	/**
	 * Check for a valid email entry.
	 */
	private boolean isValidEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
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
		
		BotListPostListingForm form = (BotListPostListingForm) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"title", "required", "* Title Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"message", "required", "* Message Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"email", "required", "* Email Field is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"userSolution", "required", "* Please add the following values.");		
				
		if ((form != null) && (form.getMainUrl() != null)) {
			if (form.getMainUrl().length() != 0) {
				String url = form.getMainUrl();
				if (!this.isValidUrl(url)) {
					errors.reject("mainUrl", "* Please enter a valid url address (for example: http://www.url.com)");
				}
			}
		}
		if ((form != null) && (form.getEmail() != null)) {
			String email = form.getEmail();
			if (!this.isValidEmail(email)) {
				errors.reject("email", "* Please enter a valid email address (for example: user@yourdomain.com)");
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
