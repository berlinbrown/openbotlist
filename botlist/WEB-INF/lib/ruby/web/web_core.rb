##
## Berlin Brown
## 04/07/2007
##
## '../../WEB-INF/lib/ruby/web', 'web_core'
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

require 'java'

include_class 'org.spirit.util.BotListConsts' unless defined? BotListConsts
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.spirit.util.BotListCookieManager' unless defined? BotListCookieManager

include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification

module BotListWebCore
  
  # Bot IDENTIFY
  BOT_IDENTIFY_ROVER = "botrover99"
  
  # Request parameters ( use BotListWebCore::REQUEST_X to get value )
  # Ruby and System Constants
  REQUEST_GET_CUR_ACTION = "curaction"
  REQUEST_GET_ACTION_ID = "actionid"
  REQUEST_GET_VIEW_ID = "viewid"

  # Cookie manager values
  COOKIE_USERNAME = "botlist_username"
  COOKIE_EMAIL = "botlist_email"
  
  # Spam verification numbers
  MAX_RAND_FIRST = 30
  MAX_RAND_SECOND = 10
  
  # Session remote sync keys
  SESSION_REMOTE_SYNC_KEY = "session.remote_sync.key"

  # Get a request parameter, also check if the 
  # value is stored in session.
  def BotListWebCore.safeGetParameter(request, requestParam)
    if !request.nil?
      r_val = request.getParameter(requestParam)
      newval = KeywordProcessor::filterAlphaNumeric(r_val)
      return newval
    end
  end
  
  # Check if the user is logged in, using the cookie manager
  def BotListWebCore.userLoggedIn?(request)
    if !request.nil?
        userInfo = BotListContractManager::getUserInfo(request)
        if !userInfo.nil?
            return true
        end
    end
    return false
  end
  
  # Generate the calculator verification, input and solution
  def BotListWebCore.generateCalcVerification()
    calc = BotListCalculatorVerification.new
    calc.firstInput = rand MAX_RAND_FIRST
    calc.secondInput = rand MAX_RAND_SECOND
    calc.solution = calc.firstInput + calc.secondInput
    return calc
  end

  # Check for empty value from form members
  def BotListWebCore.valueEmpty?(member_value)
    res = (not (!member_value.nil? and !member_value.empty?))
    return res
  end
  
  # Ruby oriented cookie manager class for handling file persistent
  # cookies.
  class WebCookieManager    
    attr_accessor :username, :email
    def initialize(request)
      @username = nil
      @email = nil
      @login_status = nil
      self.loadDefaultValues(request)
    end
    
    # Load the core cookie values, eg: username
    def loadDefaultValues(request)
      cookieUsername = BotListCookieManager::getCookieParam(request, COOKIE_USERNAME)
      @username = cookieUsername if !cookieUsername.nil? and !cookieUsername.empty?

      cookieEmail = BotListCookieManager::getCookieParam(request, COOKIE_EMAIL)
      @email = cookieEmail if !cookieEmail.nil? and !cookieEmail.empty?
    end
    
    # If cookie is not set, persist the cookie.
    # If a cookie username exists and is different from the form object
    # reset the cookie.
    # (note: will update form object if cookie value available)
    # Return the username.
    def getCreateUsername(form, response)
      formUsername = form.fullName || ''
      if formUsername.empty?
        form.fullName = @username
      elsif @username != formUsername
        BotListCookieManager::setCookieParam(response, COOKIE_USERNAME, formUsername) if response
        @username = formUsername
      end
      @username
    end

    def getCreateEmail(form, response)
      formEmail = form.email || ''
      if formEmail.empty?
        form.email = @email
      elsif @email != formEmail
        BotListCookieManager::setCookieParam(response, COOKIE_EMAIL, formEmail) if response
        @email = formEmail
      end
      @email
    end

    # End of Class
  end

  # End of Module
end

