##
## Berlin Brown
## 9/4/2007
##

include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
include_class 'org.spirit.bean.impl.BotListCatLinkGroups' unless defined? BotListCatLinkGroups

class BotGroupsController
		
  def initialize(controller)
    @controller = controller    
    @daohelper = @controller.catLinkGroupsDao
  end    
  
  #
  # Generate the view
  def getModel(request)
        
    catType = request.getParameter("categorytype")    
    catType = KeywordProcessor.filterAlphaNumeric(catType)
        
    # If there is no category type, display the groups
    isTypeEmpty = (catType.nil? or catType.empty? or (catType.length == 0))    
        
    @controller.auditLogPage(request, "bot_groups.html")
    
    grp = BotListCatLinkGroups.new
    grp.categoryName = catType
    
    terms = nil
    if isTypeEmpty
	  terms = @daohelper.readGroups
    else
	  groupTerms = @daohelper.readGroup(grp.categoryName)    
	  terms = groupTerms.terms if !groupTerms.nil?
    end    
    return {
      'terms' => terms
    }
  end
      
  def onSubmit(request, response, form, errors)      
    return form
  end
end

BotGroupsController.new($controller)

## End of Script ##
