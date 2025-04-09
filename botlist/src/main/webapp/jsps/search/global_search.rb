##
## Berlin Brown
## 11/4/2006
## botverse.rb
## botverse - view link listings
##


include_class 'org.spirit.form.BotListPostListingForm'

include_class 'org.spirit.contract.BotListContractManager'
include_class 'org.spirit.util.BotListSessionManager'
include_class 'org.spirit.util.BotListCookieManager'
include_class 'org.spirit.util.BotListConsts'
include_class 'org.spirit.util.text.KeywordProcessor'
include_class 'org.spirit.spring.validate.BotListGenericValidator'

include_class 'org.spirit.form.ext.BotListMapEntityLink'
include_class 'org.spirit.spring.search.GlobalSearchHandler'

include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

class BotverseController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
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
      linkForm.viewName = "search/invalidsearch"
      return linkForm
    end
    
    @controller.auditLogPage(request, "global_search.html?query=#{queryLine}")
    @controller.setValidator(BotListGenericValidator.new)
    
    queryLine = KeywordProcessor::filterAlphaNumeric(queryLine)
    searchHandler = GlobalSearchHandler.new
    searchHandler.setSettings(@controller.getApplicationContext())
    searchHandler.searchPostfix = ""
    searchResults = searchHandler.search(queryLine)

    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new
    map['listings'] = searchResults
    map['userInfo'] = userInfo
    map['searchTerm'] = queryLine
    return map
  end
  
  def onSubmit(request, response, form, errors)
    return form
  end
end

BotverseController.new($controller)

## End of Script ##

