##
## Berlin Brown
## 4/10/2007
##

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.spring.validate.BotListGenericValidator' unless defined? BotListGenericValidator
include_class 'org.spirit.bean.impl.BotListProfileSettings' unless defined? BotListProfileSettings

class UserController
		
  def initialize(controller)
    @controller = controller
    @dao_settings = @controller.profileSettingsDao
  end    

  def getModel(request)
    cur_session = request.getSession(false)
    coreUserInfo = cur_session.getAttribute(BotListSessionManager::USER_INFO_OBJECT)
    if coreUserInfo.nil?
      raise "Invalid User Info"
    end
    coreUserInfo.viewName = "profile/settings"
    @controller.setValidator(BotListGenericValidator.new)    
    return coreUserInfo
  end
   
  def onSubmit(request, response, form, errors)
    
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "profile/settings"
      return form
    end
    
    settings = BotListProfileSettings.new
    settings.linkColor = form.linkColor
    settings.id = form.profileId
    settings.userId = form.get_id
    @dao_settings.createSettings(settings)
    return form
  end
end

UserController.new($controller)

## End of Script ##

