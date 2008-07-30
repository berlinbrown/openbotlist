##
## Berlin Brown
## 8/20/2007
##
## Add topic is only available for logged in users.

require 'java'

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')

include_class 'org.spirit.spring.validate.BotListAddTopicValidator' unless defined? BotListAddTopicValidator
include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager
include_class 'org.spirit.form.BotListSearchQueryFiltersForm' unless defined? BotListSearchQueryFiltersForm
include_class 'org.spirit.bean.impl.BotListSearchQueryFilters' unless defined? BotListSearchQueryFilters

class AddTopicController
		
  def initialize(controller)
	@log = LogFactory::getLog("org.jruby")
    @controller = controller
    @daohelper = @controller.searchQueryFiltersDao
  end    
  
  def getModel(request)
    topicForm = BotListSearchQueryFiltersForm.new
    topicForm.viewName = nil
    
    if !BotListWebCore.userLoggedIn?(request)
		topicForm.viewName = "errorInvalidView"
		return topicForm
	end
    
    queryLine = request.getParameter("query")
    topicForm.searchTerm = queryLine
    
	# Audit the request
	@controller.auditLogPage(request, "addtopic.html")
    
    # Also set the validator
    @controller.setValidator(BotListAddTopicValidator.new)        
    return topicForm
  end

  def onSubmit(request, response, form, errors)
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "search/addtopic"
      return form
    end
    
    # Transform the business object from the form.
	topic = BotListSearchQueryFilters.new
    topic.rating = 0; topic.views = 0
	topic.userId = 1; topic.userName = "blah"
	topic.description  = form.description
	topic.searchTerm = form.searchTerm	
    # Save hot topic record    
    begin
      sessionFactory = @daohelper.getSessionFactory()
      hbm_session = sessionFactory.openSession()
      tx = hbm_session.beginTransaction()
      hbm_session.save(topic)
      tx.commit()
    rescue Exception => e
      @log.error(e)
      raise e.message
    ensure
      hbm_session.close()
    end
	# Navigate to the confirmation page
    form.viewName = "botverse/botverse_confirm"
    return form
    # End of the method
  end
  # End of class
end

AddTopicController.new($controller)

## End of Script ##

