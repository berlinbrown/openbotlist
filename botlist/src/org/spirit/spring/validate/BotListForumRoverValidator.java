/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spirit.form.BotListUserCommentsForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListForumRoverValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	public static final String ROVER_VERIFY = "672073419141141";
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListUserCommentsForm.class.isAssignableFrom(post);
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
		
		if ((form != null) 
				&& (form.getRoverVerify() != null)) {				
			if (!form.getRoverVerify().trim().equals(ROVER_VERIFY)) {
				errors.reject("userSolution", "* Please enter the correct verify value.");
			}
		}
				
	}

}
