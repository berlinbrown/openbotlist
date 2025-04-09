##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListPostListingForm' unless defined? BotListPostListingForm
include_class 'org.spirit.bean.impl.BotListPostListing' unless defined? BotListPostListing
include_class 'org.spirit.bean.impl.BotListCityListing' unless defined? BotListCityListing
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager


include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class ViewListingController
		
  def initialize(controller)
    @controller = controller
    @daohelperpost = controller.postListingDao
    @log = LogFactory::getLog("org.jruby")
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
  
  # Generate the view
  def getModel(request)
    
    viewid = getViewLink(request)
    return nil if viewid.nil?    
    postbean = @daohelperpost.readPostListing(viewid.to_i())

    # Audit the request
    @controller.auditLogPage(request, "viewlisting.html")

    return postbean
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewListingController.new($controller)

## End of Script ##

