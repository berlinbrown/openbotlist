##
## Berlin Brown
## 11/4/2006

include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.spirit.spring.validate.BotListGenericValidator' unless defined? BotListGenericValidator

include_class 'org.spirit.form.ext.BotListMapEntityLink' unless defined? BotListMapEntityLink
include_class 'org.spirit.spring.search.EntityLinkSearchHandler' unless defined? EntityLinkSearchHandler
include_class 'org.spirit.spring.search.GlobalSearchHandler'  unless defined? GlobalSearchHandler

class SearchPipesController

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

    @controller.auditLogPage(request, "search.html?query=#{queryLine}")
    @controller.setValidator(BotListGenericValidator.new)
    
    queryLine = KeywordProcessor::filterAlphaNumeric(queryLine)
    searchHandler = GlobalSearchHandler.new    
    searchHandler.setSettings(@controller.getApplicationContext())
    searchHandler.searchPostfix = ""
    searchResults = searchHandler.search(queryLine)
    
    map = BotListMapEntityLink.new
    map['listings'] = searchResults  
    return map
  end
  
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
    return form
  end
end

SearchPipesController.new($controller)
## End of Script ##