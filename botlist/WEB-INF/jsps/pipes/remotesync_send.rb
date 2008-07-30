##
## Berlin Brown
## 8/4/2007
##
## remote sync.rb
##

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'entity_links')

include_class 'org.spirit.util.BotListUniqueId' unless defined? BotListUniqueId
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.form.BotListEntityLinksRemoteSyncForm' unless defined? BotListEntityLinksRemoteSyncForm
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

include_class 'org.spirit.spring.validate.BotListRemoteSyncValidator' unless defined? BotListRemoteSyncValidator


class RemoteSyncController
  MAX_DATA_LEN = 20 * 1024 unless defined? MAX_DATA_LEN
		
  def initialize(controller)
    @controller = controller
    @daohelperlink = controller.entityLinksDao
    @log = LogFactory::getLog("org.jruby")
  end    
    
  # Generate the view
  def getModel(request)
    linkListingRemote = BotListEntityLinksRemoteSyncForm.new
    linkListingRemote.viewName = nil 
    @controller.auditLogPage(request, "remotesync_send.html")
        
	# Safe session get, get the previous remote sync key
    cur_session = BotListSessionManager.safeCheckSession(request)
    if cur_session.nil?
	  linkListingRemote.viewName = "errorInvalidView"
	  return linkListingRemote
    end
    # Verify against the remote sync key
    requestKey = cur_session.getAttribute(BotListWebCore::SESSION_REMOTE_SYNC_KEY)
    if requestKey.nil?
	  linkListingRemote.viewName = "errorInvalidView"
	  return linkListingRemote
    end    
    @controller.setValidator(BotListRemoteSyncValidator.new)    
	return linkListingRemote
  end
    
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
	 # Check the errors first.
    if (errors.getErrorCount() > 0)
      # Revert back to the entry screen
      form.viewName = "pipes/remote_sync"
      return form
    end
    
    cur_session = BotListSessionManager.safeCheckSession(request)
    requestKey = cur_session.getAttribute(BotListWebCore::SESSION_REMOTE_SYNC_KEY)
    # Compare incoming request key
    if requestKey != form.remoteSyncKey
	  raise "Invalid Remote Sync"
    end
    
    # Run through the basic validation (fail on error)
    # Max length of data is 20k
    if form.remoteData.length > MAX_DATA_LEN
	  raise "Invalid Remote Sync"
    end
      
    entityLinkGroup = BotListLinksManager::EntityLinkGroup.new
    entityLinkGroup.daohelper = @daohelperlink
    entityLinkGroup.remote_content = form.remoteData
    entityLinkGroup.parse
    passed = entityLinkGroup.createGroupLinks

	# build response for server output
    passed_ct = passed if !passed.nil?	
	form.responseMessage = "passed #{passed_ct}"
	# Navigate to the confirmation page
    form.viewName = "pipes/sync_confirm"    
    return form
  end
end

RemoteSyncController.new($controller)

## End of Script ##

