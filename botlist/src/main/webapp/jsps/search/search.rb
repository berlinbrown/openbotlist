##
## Berlin Brown
## 11/4/2006
## botverse.rb
## botverse - view link listings
##

require "cgi"
include_class 'org.spirit.form.BotListPostListingForm' unless defined? BotListPostListingForm

include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.BotListCookieManager' unless defined? BotListCookieManager
include_class 'org.spirit.util.BotListConsts' unless defined? BotListConsts
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.spirit.spring.validate.BotListGenericValidator' unless defined? BotListGenericValidator
 
include_class 'org.spirit.form.ext.BotListMapEntityLink' unless defined? BotListMapEntityLink
include_class 'org.spirit.spring.search.EntityLinkSearchHandler' unless defined? EntityLinkSearchHandler

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class BotverseController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
    @dao_banner = @controller.adminMainBannerDao
  end
  
  # Generate the view
  def getModel(request)

    actionCheck = request.getParameter("querymode")
    queryLine = request.getParameter("query")    
    if actionCheck != "enabled"      
      raise "Invalid Query"
    end

    if queryLine.nil? or queryLine.length == 0
      linkForm = BotListMapEntityLink.new
      # leave method when query is empty
      userInfo = BotListContractManager::getUserInfo(request)
      map = BotListMapEntityLink.new
      map['userInfo'] = userInfo
      return map
    end

    @controller.auditLogPage(request, "search.html?query=#{queryLine}")
    @controller.setValidator(BotListGenericValidator.new)
    
    queryLine = KeywordProcessor::filterAlphaNumeric(queryLine)
    searchHandler = EntityLinkSearchHandler.new
    searchHandler.setSettings(@controller.getApplicationContext())
    searchResults = searchHandler.search(queryLine)
    
     # Extract the banner headline to display
    banner = @dao_banner.readBanner('botverse')
    headline = banner.headline if !banner.nil?
    
    queryLineEscape = CGI.escape(queryLine)    
    queryLineEscape.tr!(" ","+") if queryLineEscape
    
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new
    map['listings'] = searchResults
    map['userInfo'] = userInfo
    map['searchTerm'] = queryLine
    map['headline'] = headline
    map['query'] = queryLineEscape
    return map
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
    return form
  end
end

BotverseController.new($controller)

## End of Script ##

