##
## Berlin Brown
##

include_class 'org.spirit.bean.impl.BotListAdminMainBanner'
include_class 'org.spirit.spring.validate.BotListAdminBannerValidator'
include_class 'org.spirit.form.BotListAdminMainBannerForm'

include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

class BotverseAdminController
  
  def initialize(controller)
    @controller = controller
    @dao = controller.adminMainBannerDao
    @log = LogFactory::getLog("org.jruby")
  end
  
  # Generate the view
  def getModel(request)
    bannerForm = BotListAdminMainBannerForm.new
    bannerForm.viewName = nil
    # Also set the validator
    @controller.setValidator(BotListAdminBannerValidator.new)
    return bannerForm
  end
        
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
      
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "botverse_editheadline"
      return form
    end
    
    # tranform form to business object
    banner = BotListAdminMainBanner.new
    banner.headline = form.headline
    banner.section = "botverse"
    banner.bannerEnabled = true
    @dao.createBanner(banner)
    
    form.viewName = "newadmin/admin_confirm"
    return form
  end
end

BotverseAdminController.new($controller)

## End of Script ##

