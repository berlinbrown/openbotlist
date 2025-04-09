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
public class BotListCoreUsersValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());	
	public final static int MAX_PASSWORD_LEN = 30;
	
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
				"userEmail", "required", "* Please Enter a valid Email.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"userPassword", "required", "* Please Enter a valid Password.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"verifyPassword", "required", "* Please Enter a valid Password.");
		
		if ((form != null) && (form.getUserName() != null)) {
			// Check for invalid chars in the username:
			String orig = form.getUserName().trim();
			String checkAlphaNumeric = form.getUserName().replaceAll("[^\\s0-9a-zA-Z]", "").trim();
			if (orig.length() != checkAlphaNumeric.length()) {
				errors.reject("userName", "* Invalid User Name");
			}
		}
		
		if ((form != null) 
					&& (form.getVerifyPassword() != null) && (form.getUserPassword() != null)) {
				if (!form.getUserPassword().trim().equals(form.getVerifyPassword())) {
					errors.reject("userPassword", "* Your passwords do not match, please enter a valid Password");
				}
		}
		if (form != null) {
			if ((form.getUserPassword().length() < 6) || (form.getUserPassword().length() > MAX_PASSWORD_LEN)) {
				errors.reject("userPassword", "* Please enter a valid Password");
			}
		}			
	}

}
