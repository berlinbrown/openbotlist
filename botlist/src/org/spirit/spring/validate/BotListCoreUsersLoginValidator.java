/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spirit.form.BotListCoreUsersForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse (used for rover updates).
 * @author Berlin Brown
 *
 */
public class BotListCoreUsersLoginValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListCoreUsersForm.class.isAssignableFrom(post);
	}	
		
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		BotListCoreUsersForm form = (BotListCoreUsersForm) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"userName", "required", "* Please Enter a valid User Name.");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"userPassword", "required", "* Please Enter a valid Password.");
											
	}

}
