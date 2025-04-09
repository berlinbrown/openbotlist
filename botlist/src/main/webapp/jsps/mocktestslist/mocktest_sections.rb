##
## Berlin Brown
## 11/4/2006

include_class 'org.spirit.util.BotListSessionManager'

class MockSectionController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.cityListingDao
    @daohelpersects = @controller.postSectionsDao
  end    
  
  def getModel(request)

    city = request.getParameter("city")    
    if city != nil
      
      city.strip!
      # Quick sql injection protection
      invalid_sql = false
      if city.include? "'" or
          city.include? "\"" or
          city.include? "=" or
          city.include? ";" or
          city.include? "%22"
        invalid_sql = true
      end
      
      # Audit the request
      @controller.auditLogPage(request, "sections.html")

      citybean = @daohelper.readCityListing(city.to_i())
    end

    # 
    # Save the session parameter
    cur_session = request.getSession(false)
    if (cur_session == nil)
      cur_session = request.getSession()
    end

    cur_session.setAttribute(BotListSessionManager::CURRENT_CITY_SETTING, citybean)

    # Query the sections
    sections = @daohelpersects.listSections("from org.spirit.bean.impl.BotListPostSections")
    
    return {
      'city' => citybean,
      'sections' => sections
    }
  end
 
  
  def onSubmit(request, response, form, errors)
    return form
  end
end

MockSectionController.new($controller)

## End of Script ##

