/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spirit.form.BotListPostListingForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListPostContactValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListPostListingForm.class.isAssignableFrom(post);
	}
	
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		BotListPostListingForm form = (BotListPostListingForm) obj;		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
					"userSolution", "required", "* Please add the following values.");		
		if ((form != null) 
					&& (form.getUserSolution() != null)
					&& (form.getUserSolution() != null)) {
			
			if (form.getPrevSolution().intValue() != form.getUserSolution().intValue()) {
				errors.reject("userSolution", "* Please add the following values.");
			}
		}		
	}

}
