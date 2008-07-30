##
## Berlin Brown
## 11/4/2006
##
## forumaddtopic.rb

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')

include_class 'org.spirit.form.BotListUserCommentsForm' unless defined? BotListUserCommentsForm

include_class 'org.spirit.bean.impl.BotListEntityLinks' unless defined? BotListEntityLinks
include_class 'org.spirit.bean.impl.BotListUserComments' unless defined? BotListUserComments
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification
include_class 'org.spirit.spring.validate.BotListTopicValidator' unless defined? BotListTopicValidator
include_class 'org.spirit.util.markdown.BotListMarkUtil' unless defined? BotListMarkUtil

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class ForumCommentsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.userCommentsDao
    @daohelperforum = @controller.forumGroupDao
    @log = LogFactory::getLog("org.jruby")
  end      
    
  # Get a view instance of the forum the user clicked on
  def getForum()
    cur_session = request.getSession(false)
    forum = cur_session.getAttribute(BotListSessionManager::CURRENT_FORUM)
    return forum
  end
  
  # Generate the view
  def getModel(request)            
    userComment = BotListUserCommentsForm.new()
    userComment.viewName = nil
    
    curaction = BotListWebCore.safeGetParameter(request, BotListWebCore::REQUEST_GET_CUR_ACTION)
    actionid = BotListWebCore.safeGetParameter(request, BotListWebCore::REQUEST_GET_ACTION_ID)
     
    # Fill in the current action, action id
    if !actionid.nil? and !curaction.nil?
      userComment.currentAction = curaction
      userComment.actionId = actionid.to_i

      if curaction == "replytopic"
        # Display reply topic banner
        replyCommentBean = @daohelper.readComment(userComment.actionId)
      end
    end
    
    # Set the reply bean if available
    if !replyCommentBean.nil?
      userComment.userComment = replyCommentBean
    end

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
        userComment.viewName = "errorInvalidView.jsp"
        return userComment
    end
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if !prev_calc.nil?
      userComment.prevSolution = prev_calc.solution
    end
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListTopicValidator.new)
    return userComment
  end
 
  # Generate the calculator verification, input and solution
  def generateCalcVerification()
    calc = BotListCalculatorVerification.new
    calc.firstInput = rand 30
    calc.secondInput = rand 10
    calc.solution = calc.firstInput + calc.secondInput
    return calc
  end
  
  # createComment
  def createComment(comment, forumId)
    sessionFactory = @daohelper.getSessionFactory()
    hbm_session = sessionFactory.openSession()
    tx = hbm_session.beginTransaction()

    forum = hbm_session.load("org.spirit.bean.impl.BotListForumGroup", forumId, nil)
    comment.forumId = forumId
    forum.topics.add(comment)
    tx.commit()
    hbm_session.close()
  end

  # Transform the form data to the
  # data object.
  def transformFormData(form)    
    comment = BotListUserComments.new
    
    # If reply topic available, set
    comment.commentId = form.actionId if !form.currentAction.nil? and !form.currentAction.empty?
    
    # Also invoke markdown util to transform output text
    markdown = BotListMarkUtil.new
    comment.message = markdown.convert(form.message)
    
    comment.fullName = form.fullName
    if form.subject
      comment.subject = form.subject
    else
      comment.subject = "no subject for forum topic"
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
  
  # Process when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)

    # Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateUsername(form, response)
    cookieManager.getCreateEmail(form, response)

    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "forums/forumaddtopic"
      return form
    end
    
    # Get the current forum id
    cur_session = request.getSession(false)
    forum = cur_session.getAttribute(BotListSessionManager::CURRENT_FORUM)
    forumId = forum.get_id
    
    # Transform form data into a hbm bean
    comment = transformFormData(form)
    createComment(comment, forumId)
    
    form.viewName = 'forums/forumaddtopic_confirm'
    return form
  end

end

ForumCommentsController.new($controller)

## End of Script ##

