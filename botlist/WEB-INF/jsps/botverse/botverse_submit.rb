##
## Berlin Brown
## 11/4/2006
##
## botverse_submit.rb
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

include_class 'org.spirit.form.BotListEntityLinksForm' unless defined? BotListEntityLinksForm
include_class 'org.spirit.bean.impl.BotListEntityLinks' unless defined? BotListEntityLinks
include_class 'org.spirit.bean.impl.BotListUserLinks' unless defined? BotListUserLinks
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification
include_class 'org.spirit.spring.validate.BotListEntityLinksValidator' unless defined? BotListEntityLinksValidator
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class LinkListingController
      
  def initialize(controller)
    @controller = controller
    @daohelperlink = controller.entityLinksDao
    @log = LogFactory::getLog("org.jruby")
  end
  
  # Generate the view
  def getModel(request)
    linkListing = BotListEntityLinksForm.new()
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
    @controller.setValidator(BotListEntityLinksValidator.new)
    return linkListing
  end
    
  # Transform the form data to the
  # data object.
  def transformFormData(request, form)
    listing = BotListEntityLinks.new
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
  
  # Based on the entity link content, also create a
  # user link record.
  def createUserLinkData(entityLink)
    userLink = BotListUserLinks.new
    if !entityLink.userId.nil?
        userLink.userId = entityLink.userId
        userLink.linkId = entityLink.get_id
        return userLink
    end
    return nil
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
      form.viewName = "botverse/botverse_submit"
      return form
    end
    link = transformFormData(request, form)    
    
    # Get the bean from the DB as opposed to off the session table
    begin
      sessionFactory = @daohelperlink.getSessionFactory()
      hbm_session = sessionFactory.openSession()
      tx = hbm_session.beginTransaction()
      hbm_session.save(link)
      tx.commit()
      
      # If the link name is "self", for example just a "forum" style post.
      # Update the link with the permalink      
      if link.mainUrl.downcase == "self"
        # build the new url
        serverName = request.getServerName
        serverPort = request.getServerPort
        portStr = ""
        portStr = ":#{serverPort}" if serverPort != 80
        link.mainUrl = "http://#{serverName}#{portStr}/botlist/spring/botverse/linkviewcomments.html?viewid=#{link.get_id}"
        link.urlTitle = "#{link.urlTitle} [id:#{link.get_id}]"
        # Update self link reference
        hbm_session.clear
        tx = hbm_session.beginTransaction()
        hbm_session.update(link)
        tx.commit()        
      end
      
      # If link saved, also create the user link if available.
      userLink = createUserLinkData(link)
      if !userLink.nil?
        hbm_session.clear
        tx = hbm_session.beginTransaction()
        hbm_session.save(userLink)
        tx.commit()
      end      
    rescue Exception => e
      @log.error(e)
      tx.rollback
      raise e.message
    ensure      
      hbm_session.close()
    end
    # Navigate to the confirmation page
    form.viewName = "botverse/botverse_confirm"
    return form
  end
end

LinkListingController.new($controller)

## End of Script ##

