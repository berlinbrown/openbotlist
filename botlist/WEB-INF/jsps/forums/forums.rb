##
## Berlin Brown
## 11/4/2006
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

BotListMapEntityLink = org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink


BotListSessionManager = org.spirit.util.BotListSessionManager
BotListContractManager = org.spirit.contract.BotListContractManager

class ListingsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.forumGroupDao
    @dao_entitylinks = @controller.entityLinksDao
  end    
  
  def getModel(request)
           
    query = "from org.spirit.bean.impl.BotListForumGroup as forums"
    forumListings = @daohelper.listForums(query)    
    forumCountMap = {}
    forumListings.each do |n|
      commentsCount = @dao_entitylinks.getLinkCommentCountByForum(n.get_id)
      fname = n.forumName
      forumCountMap[fname] = commentsCount
    end

    # Audit the request
    @controller.auditLogPage(request, "forums.html")   
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new
    map['listings'] = forumListings
    map['userInfo'] = userInfo
    map['forumCountMap'] = forumCountMap
    return map
  end
  
  def onSubmit(request, response, form, errors)
    return form
  end
end

ListingsController.new($controller)

## End of Script ##

