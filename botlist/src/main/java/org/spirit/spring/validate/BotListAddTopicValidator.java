/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.spirit.form.BotListSearchQueryFiltersForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown 
 */
public class BotListAddTopicValidator implements Validator {
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListSearchQueryFiltersForm.class.isAssignableFrom(post);
	}	
		
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		
		BotListSearchQueryFiltersForm form = (BotListSearchQueryFiltersForm) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"description", "required", "* Description is required.");
	
	}

}
