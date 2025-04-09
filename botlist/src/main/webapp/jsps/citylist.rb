##
## Berlin Brown
## 7/27/2007

include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager
include_class 'org.spirit.spring.validate.BotListGenericValidator' unless defined? BotListGenericValidator
include_class 'org.spirit.form.ext.BotListMapEntityLink' unless defined? BotListMapEntityLink
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class BotListCityController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.cityListingDao
  end    
  
  # Generate the view
  def getModel(request)

    # Audit the request
    @controller.auditLogPage(request, "citylist.html")    
    
    # Safe session create, botverse is a core application,
    # Create the session if not available
    BotListSessionManager.safeCreateSession(request)
    
    # Set the default generic validator    
    @controller.setValidator(BotListGenericValidator.new)
    # Note, when using the page-size and offset parameter, usually
    # you will want to keep the page sizes the same, eg a value of 15 in order to
    # print the correct results.
    postListings = @daohelper.findCityListingsType('MAJOR', 0, 40)
    postListingsMinorOne = @daohelper.findCityListingsType('MINOR', 0, 15)
    postListingsMinorTwo = @daohelper.findCityListingsType('MINOR', 1, 15)
                		    
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new    
    map['citylistings'] = postListings
    map['cityListingsMinor'] = postListingsMinorOne
    map['cityListingsMinorTwo'] = postListingsMinorTwo
    map['userInfo'] = userInfo
    return map
  end
    
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)    
    return form
  end
end

BotListCityController.new($controller)

## End of Script ##

