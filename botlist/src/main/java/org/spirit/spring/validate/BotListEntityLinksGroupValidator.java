/**
 * Berlin Brown
 * Nov 18, 2006
 */
package org.spirit.spring.validate;

import org.spirit.form.BotListEntityLinksForm;
import org.springframework.validation.Errors;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListEntityLinksGroupValidator extends BotListEntityLinksValidator {
	
	public static final String INFO_FILTER = "en.wikipedia.org";
	public static final String MEDIA_FILTER = "youtube";
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object obj, Errors errors) {
		super.validate(obj, errors);
		
		BotListEntityLinksForm form = (BotListEntityLinksForm) obj;
		if (form != null) {
			if (form.getGeneratedId() != null) {
				if (form.getGeneratedId().indexOf("media") != -1) {
					if (form.getMainUrl().indexOf(MEDIA_FILTER) == -1) {
						errors.reject("mainUrl", "* Please enter a valid url address (for example: http://" + MEDIA_FILTER + ")");
					}
				} else if (form.getGeneratedId().indexOf("info") != -1) {
					if (form.getMainUrl().indexOf(INFO_FILTER) == -1) {
						errors.reject("mainUrl", "* Please enter a valid url address (for example: http://" + INFO_FILTER + ")");
					}
				}
			}
		} // End of if - form not null
	}

}
