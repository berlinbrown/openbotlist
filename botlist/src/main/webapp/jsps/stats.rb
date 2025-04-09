##
## Berlin Brown
## 11/4/2006
##
## stats.rb
##
include_class 'org.spirit.form.BotListPostListingForm' unless defined? BotListPostListingForm
include_class 'org.spirit.form.BotListVisitLogStatsForm' unless defined? BotListVisitLogStatsForm
 
include_class 'org.spirit.bean.impl.BotListPostListing' unless defined? BotListPostListing
include_class 'org.spirit.bean.impl.BotListCityListing' unless defined? BotListCityListing
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

include_class('java.util.Calendar') { 'JCalendar' } unless defined? JCalendar

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class ViewStatsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.userVisitLogDao
    @dao_coreusers = @controller.coreUsersDao
    @dao_coresettings = @controller.coreSettings
  end    

  #
  # Generate Stats for the current week
  def weekStats()
    # Get the first day
    curCal = JCalendar::getInstance()
    curCal.add(JCalendar::DATE, -7)
    weekStatsCount = @daohelper.getVisitAuditDate(curCal)
    
    # Extract visit stats for the last 7 days
    i = -6
    statMap = {}
    statMapDates = {}
    while i <= 0
      curCal = JCalendar::getInstance()
      curCal.add(JCalendar::DATE, i)
      curStat = @daohelper.getVisitAuditOnDate(curCal)      
      strId = "stats#{i + 6}"
      statMap[strId] = curStat
      statMapDates[strId] = curCal
      i += 1
    end

    stats = BotListVisitLogStatsForm.new
    stats.weekVisits = weekStatsCount
    stats.weekStats = statMap
    stats.weekStatsDates = statMapDates
    return stats
  end

  #
  # Generate the view
  def getModel(request)      
    return { }    
  end
  
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewStatsController.new($controller)

## End of Script ##

