##
## Berlin Brown
## 11/4/2006
##

include_class 'org.spirit.util.BotListSessionManager'

class ListingsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.postListingDao
    @daohelpersects = @controller.postSectionsDao
  end    

  def getViewId(request)
    viewid = request.getParameter("viewid")    
    if viewid != nil      
      viewid.strip!
      # Quick sql injection protection
      invalid_sql = false
      if viewid.include? "'" or
          viewid.include? "\"" or
          viewid.include? "=" or
          viewid.include? ";" or
          viewid.include? "%22"
        invalid_sql = true
      end
    end
    
    bean = @daohelpersects.readSection(viewid)
    return bean
  end
  
  def getModel(request)

    sectionBean = getViewId(request)
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
      'listings' => postListings
    }
  end
 
  def onSubmit(request, response, form, errors)
    return form
  end
end

ListingsController.new($controller)

## End of Script ##

