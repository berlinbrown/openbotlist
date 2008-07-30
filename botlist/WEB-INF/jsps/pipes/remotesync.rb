##
## Berlin Brown
## 8/4/2007
##
## remote sync.rb
##

 require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')

include_class 'org.spirit.util.BotListUniqueId' unless defined? BotListUniqueId
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

class RemoteSyncController
		
  def initialize(controller)
    @controller = controller    
  end    
  
  #
  # Generate the view
  def getModel(request)
    
    @controller.auditLogPage(request, "remotesync.html")
    key1 = BotListUniqueId::getUniqueId
    key2 = BotListUniqueId::getUniqueId
    requestKey = "#{key1}#{key2}"
    
	# Safe session create, botverse is a core application,
    # Create the session if not available
    cur_session = BotListSessionManager.safeCreateSession(request)
    cur_session.setAttribute(BotListWebCore::SESSION_REMOTE_SYNC_KEY, requestKey)
    
    return {
      'requestKey' => requestKey
    }
  end
    
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

RemoteSyncController.new($controller)

## End of Script ##

