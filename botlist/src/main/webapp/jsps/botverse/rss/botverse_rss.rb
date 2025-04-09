##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
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
include Java

import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

BotListPostListingForm = org.spirit.form.BotListPostListingForm unless defined? BotListPostListingForm
BotListPostListing = org.spirit.bean.impl.BotListPostListing unless defined? BotListPostListing
BotListCityListing = org.spirit.bean.impl.BotListCityListing unless defined? BotListCityListing
BotListSessionManager = org.spirit.util.BotListSessionManager unless defined? BotListSessionManager

Log = org.apache.commons.logging.Log unless defined? Log
LogFactory = org.apache.commons.logging.LogFactory unless defined? LogFactory

class ViewListingControllerRSS		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
  end    
   
  # Generate the view
  def getModel(request)
    
    # Audit the request
    @controller.auditLogPage(request, "botverse_rss.html")
        
    query = "from org.spirit.bean.impl.BotListEntityLinks as links order by links.id desc"
    postListings = @daohelper.pageEntityLinks(query, 0, 24)
    map = BotListMapEntityLink.new
    map['listings'] = postListings
    return map
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewListingControllerRSS.new($controller)

## End of Script ##

