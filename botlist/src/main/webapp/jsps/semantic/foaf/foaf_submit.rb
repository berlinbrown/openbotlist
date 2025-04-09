##
## Berlin Brown
## 3/12/2008
##
## foaf_submit.rb
##
# -------------------------- COPYRIGHT_AND_LICENSE --
# Botlist contains an open source suite of software applications for 
# social bookmarking and collecting online news content for use on the web.  
# Multiple web front-ends exist for Django, Rails, and J2EE.  
# Users and remote agents are allowed to submit interesting articles.
#
# Copyright (c) 2007, Botnode.com (Berlin Brown)
# http://www.opensource.org/licenses/bsd-license.php
#
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification, 
# are permitted provided that the following conditions are met:
#
#	    * Redistributions of source code must retain the above copyright notice, 
#	    this list of conditions and the following disclaimer.
#	    * Redistributions in binary form must reproduce the above copyright notice, 
#	    this list of conditions and the following disclaimer in the documentation 
#	    and/or other materials provided with the distribution.
#	    * Neither the name of the Botnode.com (Berlin Brown) nor 
#	    the names of its contributors may be used to endorse or promote 
#	    products derived from this software without specific prior written permission.
#	
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
# LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
# A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
# -------------------------- END_COPYRIGHT_AND_LICENSE --
#

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web/foaf', 'edit_foaf')

include_class 'org.spirit.apps.foaf.BotListEntityTypeFoafForm' unless defined? BotListEntityTypeFoafForm
include_class 'org.spirit.apps.foaf.BotListEntityTypeFoaf' unless defined? BotListEntityTypeFoaf
include_class 'org.spirit.spring.validate.BotListEntityTypeFoafValidator' unless defined? BotListEntityTypeFoafValidator
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class FoafController      
  def initialize(controller)
    @controller = controller
    @daohelper = controller.entityTypeFoafDao
    @log = LogFactory::getLog("org.jruby")
  end  
  # Generate the view
  def getModel(request)
    linkListing = BotListEntityTypeFoafForm.new()
    linkListing.viewName = nil

    # Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateUsername(linkListing, nil)

    # Calculator Verification
    calc = BotListWebCore::generateCalcVerification()
    linkListing.firstInput = calc.firstInput
    linkListing.secondInput = calc.secondInput
    linkListing.solution = calc.solution

    cur_session = request.getSession(false)
    if cur_session.nil?
        linkListing.viewName = "errorInvalidView"
        return linkListing
    end
    
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if !prev_calc.nil?
      linkListing.prevSolution = prev_calc.solution
    end    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListEntityTypeFoafValidator.new)
    return linkListing
  end
    
  # Transform the form data to the
  # data object.
  def transformFormData(request, form)
    listing = BotListEntityTypeFoaf.new
    # Filter the title of non-ascii characters (TODO)
    listing.urlTitle = KeywordProcessor::filterNonAscii(form.urlTitle)
    listing.rating = 0
    listing.fullName = form.fullName
        
    # If the user is logged in, get the user id.
    userIdLoggedIn = BotListSessionManager.getIdUserLoggedIn(request)
    listing.userId = userIdLoggedIn if (!userIdLoggedIn.nil?)
    
    if form.mainUrl
      listing.mainUrl = form.mainUrl
    end
    if form.keywords
      keywords = KeywordProcessor::createKeywords(form.keywords)
      listing.keywords = keywords
    end
    return listing
  end  
      
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)

    # Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateUsername(form, response)

    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "semantic/foaf/foaf_submit"
      return form
    end
    link = transformFormData(request, form)    
    
    # Get the bean from the DB as opposed to off the session table
    begin
      url = link.mainUrl
      puts "Submitting foaf = #{url}"
      foaf = BotListFoaf::FoafHandler.new(@daohelper, @log, url)
      foaf.createFoaf
      puts foaf.last_result
    rescue Exception => e
      puts "ERROR foaf submit #{e}"
      @log.error(e)
      raise e.message
    ensure
    end
    # Navigate to the confirmation page
    form.viewName = "semantic/foaf/foaf_confirm"
    return form
  end
end

FoafController.new($controller)

## End of Script ##

