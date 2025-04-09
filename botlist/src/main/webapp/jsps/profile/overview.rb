##
## Berlin Brown
## 4/10/2007
##

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.spring.validate.BotListGenericValidator' unless defined? BotListGenericValidator
include_class 'org.spirit.form.BotListUserLinksForm' unless defined? BotListUserLinksForm

class UserOverviewController
		
  def initialize(controller)
    @controller = controller
    @dao = @controller.userLinksDao
    @dao_users =  @controller.coreUsersDao
  end

  def getModel(request)
	
	coreUsername = request.getParameter("username")
	if coreUsername.nil? or coreUsername.empty?
		form = BotListUserLinksForm.new
		form.viewName = 'errorInvalidView'
		return form
	end
	
	coreUser = @dao_users.readUserGetLinks(coreUsername)	
	if coreUser.nil?
		form = BotListUserLinksForm.new
		form.viewName = 'errorInvalidView'
		return form
	end
	
	userLinks = coreUser.links	
	  
	# Set the generic validator
    @controller.setValidator(BotListGenericValidator.new)
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new
    map['userInfo'] = userInfo
    map['coreUser'] = coreUser
    map['linkListings'] = userLinks
    return map
  end
   
  def onSubmit(request, response, form, errors)
    return form
  end
end

UserOverviewController.new($controller)

## End of Script ##

