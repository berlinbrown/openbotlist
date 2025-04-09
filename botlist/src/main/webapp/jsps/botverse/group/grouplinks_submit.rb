##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListEntityLinksForm' unless defined? BotListEntityLinksForm

include_class 'org.spirit.bean.impl.BotListEntityLinks' unless defined? BotListEntityLinks
include_class 'org.spirit.bean.impl.BotListGroupLinks' unless defined? BotListGroupLinks
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification
include_class 'org.spirit.spring.validate.BotListEntityLinksGroupValidator' unless defined? BotListEntityLinksGroupValidator
 
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class LinkListingController
		
  def initialize(controller)
    @controller = controller
    @daohelperlink = controller.entityLinksDao
    @dao_groups = controller.linkGroupsDao
    @log = LogFactory::getLog("org.jruby")
  end

  #
  # Generate the view
  def getModel(request)
    linkListing = BotListEntityLinksForm.new()
    linkListing.viewName = nil
    
    groupId = request.getParameter("groupgenid")
    linkListing.generatedId = groupId

    # Calculator Verification
    calc = generateCalcVerification()
    calc.solution = calc.firstInput + calc.secondInput
    linkListing.firstInput = calc.firstInput
    linkListing.secondInput = calc.secondInput
    linkListing.solution = calc.solution

    cur_session = request.getSession(false)
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if prev_calc != nil
      linkListing.prevSolution = prev_calc.solution
    end    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListEntityLinksGroupValidator.new)
    return linkListing
  end
    
  # Transform the form data to the
  # data object.
  def transformFormData(form)
    groupId = KeywordProcessor.filterAlphaNumeric(form.generatedId)
    # Get the link group bean based on the generated id
    groupLinkBean = @dao_groups.readLinkGroup(groupId)            
    groupName = groupLinkBean.groupName
    
    listing = BotListGroupLinks.new
    form.urlTitle = KeywordProcessor::filterNonAscii(form.urlTitle)
    listing.fullName = form.fullName
    listing.groupId = groupLinkBean.get_id
    listing.urlTitle = "[#{groupName}] #{form.urlTitle}" 
    if form.mainUrl
      listing.mainUrl = form.mainUrl
    end
    if form.keywords
      keywords = KeywordProcessor::createKeywords(form.keywords)
      listing.keywords = keywords
    end
    return listing
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
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "botverse/group/grouplinks_submit"
      return form
    end    
    link = transformFormData(form)

    begin
      sessionFactory = @daohelperlink.getSessionFactory()
      hbm_session = sessionFactory.openSession()
      tx = hbm_session.beginTransaction()
      hbm_session.save(link)
      tx.commit()    
    rescue Exception => e
      if @log
        @log.error(e)
      end
      raise e.message
    ensure
      hbm_session.close()
    end      
    
    # Navigate to the confirmation page
    form.viewName = "botverse/group/grouplinks_confirm"
    return form
  end
end

LinkListingController.new($controller)

## End of Script ##

