##
## Berlin Brown
## 4/10/2007
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
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/actions', 'core_users')
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'users')

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.form.BotListCoreUsersForm' unless defined? BotListCoreUsersForm
include_class 'org.spirit.spring.validate.BotListCoreUsersLoginValidator' unless defined? BotListCoreUsersLoginValidator

class UserController
		
  def initialize(controller)
    @controller = controller
    @dao_user = @controller.coreUsersDao
    @dao_profile = @controller.profileSettingsDao
  end    
  
  def getModel(request)
    coreUserForm = BotListCoreUsersForm.new
    coreUserForm.viewName = nil
    # Also set the validator
    @controller.setValidator(BotListCoreUsersLoginValidator.new)
    return coreUserForm 
  end
   
  def onSubmit(request, response, form, errors)

    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "profile/login"
      return form
    end
    
    core_users = CoreUsersManager::CoreUsers.new
    begin
      core_users.authenticateUser(@dao_user, form)
    rescue Exception => e
      # On error return to login page
      err_msg = e.message
      errors.reject("userName", "Invalid Login: #{err_msg}")
      form.viewName = "profile/login"
      return form
    end

    # Commit the registered user to session
    web_users = CoreUsersWeb::CoreUsers.new
    web_users.request, web_users.response = request, response
    web_users.core_users = core_users
    web_users.marshallData(@dao_profile, core_users.user)

    form.viewName = "profile/loginconfirm"    
    if web_users.user.nil?
      raise "Invalid Core User Object, could not find user"
    end
    web_users.user.viewName = form.viewName
    return web_users.user
  end
end

UserController.new($controller)

## End of Script ##

