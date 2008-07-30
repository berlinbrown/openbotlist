##
## Berlin Brown
## 4/10/2007
##

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/actions', 'core_users')
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'users')

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.form.BotListCoreUsersForm' unless defined? BotListCoreUsersForm
include_class 'org.spirit.spring.validate.BotListCoreUsersValidator' unless defined? BotListCoreUsersValidator

class UserController
		
  def initialize(controller)
    @controller = controller
    @dao_user = @controller.coreUsersDao
    @dao_profile = @controller.profileSettingsDao
  end    
  
  def getModel(request)
    coreUserForm = BotListCoreUsersForm.new
    coreUserForm.viewName = nil

    # Also set the validator
    @controller.setValidator(BotListCoreUsersValidator.new)
    return coreUserForm
  end
   
  def onSubmit(request, response, form, errors)

    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "profile/register"
      return form
    end
    
    core_users = CoreUsersManager::CoreUsers.new
    core_users.unmarshallFormData(form)
    core_users.createUser(@dao_user)

    # Commit the registered user to session
    web_users = CoreUsersWeb::CoreUsers.new
    web_users.request, web_users.response = request, response
    web_users.core_users = core_users
    web_users.marshallData(@dao_profile, core_users.user)

    form.viewName = "profile/registerconfirm"
    web_users.user.viewName = form.viewName
    return web_users.user
  end
end

UserController.new($controller)

## End of Script ##
