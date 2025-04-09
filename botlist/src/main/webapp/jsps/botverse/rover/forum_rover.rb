##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListUserCommentsForm' unless defined? BotListUserCommentsForm

include_class 'org.spirit.bean.impl.BotListUserComments' unless defined? BotListUserComments
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification

include_class 'org.spirit.spring.validate.BotListForumRoverValidator' unless defined? BotListForumRoverValidator

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class ForumRoverController
		
  def initialize(controller)
    @controller = controller
    @daohelperlink = controller.entityLinksDao
    @log = LogFactory::getLog("org.jruby")
  end


  # Generate the view
  def getModel(request)    
    userComment = BotListUserCommentsForm.new()
    userComment.viewName = nil
        
    # Also set the ROVER validator
    @controller.setValidator(BotListForumRoverValidator.new)    
    return userComment
  end
   
  # Transform the form data to the
  # data object.
  def transformFormData(form)
    comment = BotListUserComments.new
    comment.message = form.message
    comment.fullName = form.fullName
    comment.forumId = 1
    if form.subject
      comment.subject = form.subject
    else
      comment.subject = "no subject for forum topic"
    end
    if form.keywords
      comment.keywords = form.keywords
    end
    return comment
  end
        
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)       

    if (errors.getErrorCount() > 0) || (form.roverVerify != BotListForumRoverValidator::ROVER_VERIFY)
      # Revert back to the entry screen
      form.viewName = "botverse/rover/forum_rover"
      return form
    end
    comment = transformFormData(form)

    # Get the bean from the DB as opposed to off the session table
    begin
      sessionFactory = @daohelperlink.getSessionFactory()
      hbm_session = sessionFactory.openSession()
      tx = hbm_session.beginTransaction()
      hbm_session.save(comment)
      tx.commit()
    rescue Exception => e
      @log.error(e)
      raise e.message
    ensure
      hbm_session.close()
    end

    form.viewName = "botverse/rover/rover_confirm"
    return form
  end
end

ForumRoverController.new($controller)

## End of Script ##

