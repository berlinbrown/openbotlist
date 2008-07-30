/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.spirit.form.BotListEntityLinksForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse (used for rover updates).
 * @author Berlin Brown
 *
 */
public class BotListEntityLinksRoverValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	public static final String ROVER_VERIFY = "672073419141141";
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListEntityLinksForm.class.isAssignableFrom(post);
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
		
		BotListEntityLinksForm form = (BotListEntityLinksForm) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"urlTitle", "required", "* Title Field is required.");				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"mainUrl", "required", "* Please submit a URL.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"keywords", "required", "* Please enter keyword tags (space separated).");
		
		if ((form != null) && (form.getMainUrl() != null)) {
			String url = form.getMainUrl();
			if (!this.isValidUrl(url)) {
				errors.reject("mainUrl", "* Please enter a valid url address (for example: http://www.url.com)");
			}
		}
		
		if ((form != null) 
					&& (form.getRoverVerify() != null)) {				
				if (!form.getRoverVerify().trim().equals(ROVER_VERIFY)) {
					errors.reject("userSolution", "* Please enter the correct verify value.");
				}
		}	
				
	}

}
