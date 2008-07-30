##
## Berlin Brown
## 4/10/2007
##

include_class 'org.spirit.util.BotListSessionManager'

class UserController
		
  def initialize(controller)
    @controller = controller
  end    
  
  def getModel(request)
    cur_session = request.getSession(false)
    coreUserInfo = cur_session.getAttribute(BotListSessionManager::USER_INFO_OBJECT)
    
    # Invalid the session
    cur_session.invalidate()
    cur_session = nil
        
    coreUserInfo.viewName = "profile/logout"
    return coreUserInfo
  end
   
  def onSubmit(request, response, form, errors)
    return form
  end
end

UserController.new($controller)

## End of Script ##

