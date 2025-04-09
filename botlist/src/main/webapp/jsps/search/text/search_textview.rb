##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListPostListingForm'
include_class 'org.spirit.form.BotListGenericPagingForm'
include_class 'org.spirit.bean.impl.BotListPostListing'
include_class 'org.spirit.bean.impl.BotListCityListing'

include_class 'org.spirit.util.BotListSessionManager'
include_class 'org.spirit.util.BotListCookieManager'
include_class 'org.spirit.util.BotListConsts'

include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

class ViewListingController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
  end      

  # Generate the view
  def getModel(request)
        
    searchTerm = ""

    cur_session = request.getSession(false)
    searchTermForm = cur_session.getAttribute(BotListSessionManager::CURRENT_SEARCH_TERM)
    searchTerm = searchTermForm.searchEntry

    query = "from org.spirit.bean.impl.BotListEntityLinks as links where (links.urlTitle like '%#{searchTerm}%') order by links.id desc"
    postListings = @daohelper.pageEntityLinks(query, 0, 40)    
    totalLinks = @daohelper.linkCount    
    totalPages = totalLinks / BotListConsts::MAX_RESULTS_PAGE

    return {
      'listings' => postListings
    }

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

