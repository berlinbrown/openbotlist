##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

require 'java'
include Java
import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

class PipeControllerText
  MAX_PIPE_LINKS = 50
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao
  end    
  
  # Generate the view
  def getModel(request)
    
    @controller.auditLogPage(request, "botverse_pipes.html")
        
    query = "from org.spirit.bean.impl.BotListEntityLinks as links order by links.id desc"
    postListings = @daohelper.pageEntityLinks(query, 0, MAX_PIPE_LINKS)
    map = BotListMapEntityLink.new
    map['listings'] = postListings    
    return map
  end
    
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

PipeControllerText.new($controller)

## End of Script ##

