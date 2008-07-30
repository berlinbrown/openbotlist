##################################################
### Notice Update: 8/14/2007
### -------------------------- COPYRIGHT_AND_LICENSE --
### Botlist contains an open source suite of software applications for 
### social bookmarking and collecting online news content for use on the web.  
### Multiple web front-ends exist for Django, Rails, and J2EE.  
### Users and remote agents are allowed to submit interesting articles.
###
### Copyright 2007 Berlin Brown
### Copyright 2006-2007 Newspiritcompany.com
### 
### This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
### otherwise stated, use or distribution of this program 
### for commercial purpose is prohibited.
### 
### See LICENSE.BOTLIST for more information.
###
### The SOFTWARE PRODUCT and CODE are protected by copyright and 
### other intellectual property laws and treaties. 
###  
### Unless required by applicable law or agreed to in writing, software
### distributed  under the  License is distributed on an "AS IS" BASIS,
### WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
### implied.
##################################################

### Created: 3/10/2008
### foaf.rb
require 'java'
include Java

import org.spirit.form.BotListPostListingForm unless defined? BotListPostListingForm
import org.spirit.form.BotListGenericPagingForm unless defined? BotListGenericPagingForm
import org.spirit.bean.impl.BotListPostListing unless defined? BotListPostListing
import org.spirit.bean.impl.BotListCityListing unless defined? BotListCityListing

import org.spirit.contract.BotListContractManager unless defined? BotListContractManager
import org.spirit.util.BotListSessionManager unless defined? BotListSessionManager
import org.spirit.util.BotListCookieManager unless defined? BotListCookieManager
import org.spirit.util.BotListConsts unless defined? BotListConsts
import org.spirit.util.text.KeywordProcessor unless defined? KeywordProcessor
import org.spirit.spring.validate.BotListGenericValidator unless defined? BotListGenericValidator

import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

import org.apache.commons.logging.Log unless defined? Log
import org.apache.commons.logging.LogFactory unless defined? LogFactory
import java.util.Calendar { 'JCalendar' } unless defined? JCalendar

class BotverseController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityTypeFoafDao
    @dao_banner = @controller.adminMainBannerDao
    @dao_settings = @controller.coreSettings
    @dao_active_feeds = @controller.activeMediaFeedsDao
  end
  
  # Generate the view
  def getModel(request)

    # Audit the request
    @controller.auditLogPage(request, "foaf.html")
    
    # Safe session create, botverse is a core apxplication,
    # Create the session if not available
    BotListSessionManager.safeCreateSession(request)
    
    nextPage = 0
    links = @daohelper.pageEntityLinks(nextPage, BotListConsts::MAX_RESULTS_PAGE)
    
    # Extract the banner headline to display
    banner = @dao_banner.readBanner('botverse')
    
    # Set the default generic validator    
    @controller.setValidator(BotListGenericValidator.new)
        
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new    
    map['headline'] = banner.headline if !banner.nil?
    map['userInfo'] = userInfo
    map['listings'] = links
    return map
  end    
  def onSubmit(request, response, form, errors)
    return form
  end 
end

BotverseController.new($controller)

## End of Script ##

