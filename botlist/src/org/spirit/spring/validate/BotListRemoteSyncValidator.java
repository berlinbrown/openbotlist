/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spirit.form.BotListEntityLinksRemoteSyncForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse (used for rover updates).
 * @author Berlin Brown
 *
 */
public class BotListRemoteSyncValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return  BotListEntityLinksRemoteSyncForm.class.isAssignableFrom(post);
	}	
		
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"developerKey", "required", "* Invalid Remote Send.");				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"remoteData", "required", "* Invalid Remote Send.");				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"remoteSyncKey", "required", "* Invalid Remote Send.");						
	}

}
