##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

require 'java'
include Java

import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

import org.spirit.form.BotListPostListingForm unless defined? BotListPostListingForm
import org.spirit.form.BotListGenericPagingForm unless defined? BotListGenericPagingForm
import org.spirit.bean.impl.BotListPostListing unless defined? BotListPostListing
import org.spirit.bean.impl.BotListCityListing unless defined? BotListCityListing

import org.spirit.util.BotListSessionManager unless defined? BotListSessionManager
import org.spirit.util.BotListCookieManager unless defined? BotListCookieManager
import org.spirit.util.BotListConsts unless defined? BotListConsts
import org.spirit.contract.BotListContractManager unless defined? BotListContractManager
 
import org.apache.commons.logging.Log unless defined? Log
import org.apache.commons.logging.LogFactory unless defined? LogFactory

JCalendar = java.util.Calendar unless defined? JCalendar

class ViewListingController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
  end    

  # get page offset
  def getPageOffset(request)
    page = request.getParameter("offsetpage")
    if page != nil
      return page.to_i()
    end
  end
  
  # Generate the view
  def getModel(request)    
    mostrecent_mode = false
    filterset = request.getParameter("filterset")
    nextPage = getPageOffset(request)
    if not nextPage
      nextPage = 0
    end    
    if filterset == "mostrecent"
      mostrecent_mode = true
    end

    # Audit the request
    @controller.auditLogPage(request, "botverse_calendar.html")

    # For the calendar view, get stats for the last 7 days
    i = -6
    statMap = BotListMapEntityLink.new
    statMapDates = BotListMapEntityLink.new
    while i <= 0
      curCal = JCalendar::getInstance()
      curCal.add(JCalendar::DATE, i)      
      linksOnDate = @daohelper.readListingOnDate(curCal)      
      strId = "stats#{i + 6}"
      statMap[strId] = linksOnDate
      statMapDates[strId] = curCal
      i += 1
    end    
    userInfo = BotListContractManager::getUserInfo(request)
    map = BotListMapEntityLink.new
    map['userInfo'] = userInfo
    map['linksOnDates'] = statMap
    map['linksDates'] = statMapDates
    map['filterset'] = filterset
    return map
  end
 
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewListingController.new($controller)

## End of Script ##

