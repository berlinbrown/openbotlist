##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListPostListingForm'
include_class 'org.spirit.bean.impl.BotListPostListing'
include_class 'org.spirit.bean.impl.BotListCityListing'
include_class 'org.spirit.util.BotListSessionManager'


include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

class ContactEmailController
		
  def initialize(controller)
    @controller = controller
    @daohelperpost = controller.postListingDao
    @log = LogFactory::getLog("org.jruby")
  end    
  
  #
  # Generate the view
  def getModel(request)
   
    viewid = request.getParameter("id")
    uniqueId = request.getParameter("uniqueId")

    if viewid != nil
      
      viewid.strip!
      invalid_sql = false
      if viewid.include? "'" or
          viewid.include? "\"" or
          viewid.include? "=" or
          viewid.include? ";" or
          viewid.include? "%22"
        invalid_sql = true
      end
    end   
    
    cur_session = request.getSession(false)
    if not cur_session
      return {
        'viewName' => 'errorInvalidView'
      }
    end
    
    sessUniqueId = cur_session.getAttribute(BotListSessionManager::POST_UNIQUE_ID)
    invalidPage = true
    if sessUniqueId
      if sessUniqueId == uniqueId
        invalidPage = false
      end      
    else
      invalidPage = false
    end
    
    if not invalidPage
      #response.sendRedirect("/spring/errorInvalidPage.html")
      return {
        'viewName' => 'errorInvalidView'
      }
    end

    postbean = @daohelperpost.readPostListing(viewid.to_i())
    return postbean
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ContactEmailController.new($controller)

## End of Script ##

