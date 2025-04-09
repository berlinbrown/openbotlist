##
## Berlin Brown
## 11/4/2006
##

require 'java'
include Java

BotListMapEntityLink = org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

BotListSessionManager = org.spirit.util.BotListSessionManager
BotListContractManager = org.spirit.contract.BotListContractManager

class ViewCommentsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.userCommentsDao
  end    
  
  # Get a view instance of the link the user clicked on
  def getViewLink(request)
    viewid = request.getParameter("viewid")
    if !viewid.nil? 
      newviewid = KeywordProcessor.filterAlphaNumeric(viewid)
    end
    return nil if newviewid.nil?
    newviewid.to_i()
  end

  def getModel(request)
    
    commentId = getViewLink(request)
    comment = @daohelper.readComment(commentId)
    userInfo = BotListContractManager::getUserInfo(request)

    map = BotListMapEntityLink.new
    map['listing'] = comment
    map['userInfo'] = userInfo
    return map
  end
   
  def onSubmit(request, response, form, errors)
    return form
  end
end

ViewCommentsController.new($controller)

## End of Script ##

