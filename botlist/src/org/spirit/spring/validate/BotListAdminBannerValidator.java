/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.spirit.form.BotListAdminMainBannerForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse (used for rover updates).
 * @author Berlin Brown
 *
 */
public class BotListAdminBannerValidator implements Validator {
				
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListAdminMainBannerForm.class.isAssignableFrom(post);
	}	
		
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
					
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"headline", "required", "* Headline is required.");			
						
	}

}
