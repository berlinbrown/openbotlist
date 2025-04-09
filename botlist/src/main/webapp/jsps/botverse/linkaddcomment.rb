##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')

include_class 'org.spirit.form.BotListUserCommentsForm' unless defined? BotListUserCommentsForm
include_class 'org.spirit.bean.impl.BotListEntityLinks' unless defined? BotListEntityLinks
include_class 'org.spirit.bean.impl.BotListUserComments' unless defined? BotListUserComments
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification
include_class 'org.spirit.spring.validate.BotListUserCommentsValidator' unless defined? BotListUserCommentsValidator
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory
include_class 'org.spirit.util.markdown.BotListMarkUtil' unless defined? BotListMarkUtil

class UserCommentsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.userCommentsDao
    @daohelperlink = @controller.entityLinksDao
    @log = LogFactory::getLog("org.jruby")
  end      
    
  # Get a view instance of the link the user clicked on
  def getViewLink(request)
    viewid = BotListWebCore.safeGetParameter(request, BotListWebCore::REQUEST_GET_VIEW_ID)
    link = @daohelperlink.readLinkListing(viewid.to_i())
    cur_session = request.getSession(false)
    if cur_session.nil?
        return link
    end
    cur_session.setAttribute(BotListSessionManager::CURRENT_LINK_VIEW, link)    
    return link
  end

  # Generate the view
  def getModel(request)

    userComment = BotListUserCommentsForm.new()
    userComment.viewName = nil
    
    # Get the current link view
    getViewLink(request)
    
    # Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateUsername(userComment, nil)
    cookieManager.getCreateEmail(userComment, nil)
    
    # Calculator Verification
    calc = generateCalcVerification()
    calc.solution = calc.firstInput + calc.secondInput
    userComment.firstInput = calc.firstInput
    userComment.secondInput = calc.secondInput
    userComment.solution = calc.solution
    
    cur_session = request.getSession(false)
    if cur_session.nil?        
        @log.error("Invalid session object, linkaddcomment")
        userComment.viewName = 'errorInvalidView'
        return userComment
    end
        
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if prev_calc != nil
      userComment.prevSolution = prev_calc.solution
    end    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListUserCommentsValidator.new)

    return userComment
  end
  
  #
  # Generate the calculator verification, input and solution
  def generateCalcVerification()
    calc = BotListCalculatorVerification.new
    calc.firstInput = rand 30
    calc.secondInput = rand 10
    calc.solution = calc.firstInput + calc.secondInput
    return calc
  end

  #
  # createComment
  def createComment(comment, linkId)
    sessionFactory = @daohelper.getSessionFactory()
    hbm_session = sessionFactory.openSession()
    tx = hbm_session.beginTransaction()
    link = hbm_session.load("org.spirit.bean.impl.BotListEntityLinks", linkId, nil)
    comment.linkId = linkId
    link.listings.add(comment)
    tx.commit()
    hbm_session.close()
  end

  # Transform the form data to the
  # data object.
  def transformFormData(form)
    
    comment = BotListUserComments.new
    
    # Also invoke markdown util to transform output text
    markdown = BotListMarkUtil.new
    comment.message = markdown.convert(form.message)
    
    comment.fullName = form.fullName

    if form.subject
      comment.subject = form.subject
    else
      comment.subject = "subjectid.#{form.get_id}"
    end
    
    # Optional Fields
    if form.email
      comment.email = form.email
    end
    
    if form.mainUrl
      comment.mainUrl = form.mainUrl
    end
    if form.keywords
      comment.keywords = form.keywords
    end

    return comment
  end

  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
    
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "botverse/linkaddcomment"
      return form
    end
    
    # Get the current link id
    cur_session = request.getSession(false)
    if cur_session.nil?
        @log.error("Invalid session object, linkaddcomment")
        form.viewName = 'errorInvalidView'
        return form
    end
    
    linkbean = cur_session.getAttribute(BotListSessionManager::CURRENT_LINK_VIEW)
    linkId = linkbean.get_id
    
    # Transform form data into a hbm bean
    form.id = linkId
    comment = transformFormData(form)
    createComment(comment, linkId)
    
    form.viewName = 'botverse/linkaddcomment_confirm'
    return form
  end

end

UserCommentsController.new($controller)

## End of Script ##

