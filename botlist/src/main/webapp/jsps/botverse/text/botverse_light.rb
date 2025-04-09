########################################
## Berlin Brown
## 11/4/2006
## Updated: 4/15/2008
##
## create_listing.rb
########################################

require 'java'
include Java

import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

class ViewLightController		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
  end    
  
  def getModel(request)
    
    # Audit the request
    @controller.auditLogPage(request, "botverse_light.html")        
    query = "from org.spirit.bean.impl.BotListEntityLinks as links order by links.id desc"
    links = @daohelper.pageEntityLinks(query, 0, 40)
    map = BotListMapEntityLink.new
    map['listings'] = links
    return map
  end
   
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewLightController.new($controller)

## End of Script ##
