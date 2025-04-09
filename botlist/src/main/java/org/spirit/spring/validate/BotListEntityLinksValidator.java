/**
 * Berlin Brown
 * Nov 18, 2006
 *
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for 
 * social bookmarking and collecting online news content for use on the web.  
 * Multiple web front-ends exist for Django, Rails, and J2EE.  
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *	    * Redistributions of source code must retain the above copyright notice, 
 *	    this list of conditions and the following disclaimer.
 *	    * Redistributions in binary form must reproduce the above copyright notice, 
 *	    this list of conditions and the following disclaimer in the documentation 
 *	    and/or other materials provided with the distribution.
 *	    * Neither the name of the Botnode.com (Berlin Brown) nor 
 *	    the names of its contributors may be used to endorse or promote 
 *	    products derived from this software without specific prior written permission.
 *	
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE --
 */
package org.spirit.spring.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.spirit.form.BotListEntityLinksForm;
import org.spirit.util.text.KeywordProcessor;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListEntityLinksValidator implements Validator {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	public boolean supports(Class post) {
		return BotListEntityLinksForm.class.isAssignableFrom(post);
	}	
	
	/**
	 * Check for a valid url entry.
	 */
	public static boolean isValidUrl(String url) {
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
				"mainUrl", "required", "* Please submit a URL or use 'self'.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,				
				"keywords", "required", "* Please enter keyword tags (space separated).");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,				
				"fullName", "required", "* Please enter a valid username (e.g botrover20).");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,				
				"userSolution", "required", "* Please add the following values.");	
		if (form != null) {
			if (!KeywordProcessor.validateFilterAlphaNumeric(form.getFullName())) {
				errors.reject("fullName", "* Please enter a valid username (for example: botrover20).");
			}
		}
		if ((form != null) && (form.getMainUrl() != null)) {			
			String url = form.getMainUrl();					
			// If url does not equal self and is not a valid url, flag an error
			if (!url.equalsIgnoreCase("self") && !this.isValidUrl(url)) {
				errors.reject("mainUrl", "* Please enter a valid url address (for example: http://www.url.com) or use 'self'");
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
