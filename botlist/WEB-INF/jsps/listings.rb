##
## Berlin Brown
## 11/4/2006
##

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

class ListingsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.postListingDao
    @daohelpersects = @controller.postSectionsDao
  end    
  
  # Get a view instance of the link the user clicked on
  def getViewLink(request)
    viewid = request.getParameter("viewid")
    if !viewid.nil? 
      newviewid = KeywordProcessor.filterAlphaNumeric(viewid)
    end
    return nil if newviewid.nil?
    # Use alpha-numeric viewid
    newviewid
  end
  
  def getViewId(request)
    viewid = getViewLink(request)
    return nil if viewid.nil?    
    bean = @daohelpersects.readSection(viewid)
    
    # Set the session object
    cur_session = request.getSession(false)
    cur_session = BotListSessionManager::safeCreateSession(request) if cur_session.nil?
    cur_session.setAttribute(BotListSessionManager::CURRENT_SECTION_SETTING, bean)    
    return bean
  end
  
  def getModel(request)
    
    sectionBean = getViewId(request)
    if not sectionBean   
      return {
        'viewName' => 'errorInvalidView'
      }
    end

    sectId = sectionBean.get_id

    #
    # Get the session object
    cur_session = request.getSession(false)
    citybean = cur_session.getAttribute(BotListSessionManager::CURRENT_CITY_SETTING)
    cityId = citybean.get_id
    query = "from org.spirit.bean.impl.BotListPostListing as posts where posts.cityId = #{cityId} and posts.sectionId = '#{sectId}' order by posts.id desc"
    postListings = @daohelper.listPostListings(query)
    
    # Audit the request
    @controller.auditLogPage(request, "listings.html")

    return {
      'listings' => postListings,
      'city' => citybean,
      'section' => sectionBean
    }
  end
 
  
  def onSubmit(request, response, form, errors)
    return form
  end
end

ListingsController.new($controller)

## End of Script ##

