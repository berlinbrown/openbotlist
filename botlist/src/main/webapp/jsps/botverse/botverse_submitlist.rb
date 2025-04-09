##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##
 
include_class 'org.spirit.form.BotListEntityLinksForm' unless defined? BotListEntityLinksForm

include_class 'org.spirit.bean.impl.BotListEntityLinks' unless defined? BotListEntityLinks
include_class 'org.spirit.bean.impl.BotListCalculatorVerification' unless defined? BotListCalculatorVerification
include_class 'org.spirit.spring.validate.BotListEntityLinksValidator' unless defined? BotListEntityLinksValidator

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor
 
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class LinkListingController
		
  def initialize(controller)
    @controller = controller
    @daohelperlink = controller.entityLinksDao
    @log = LogFactory::getLog("org.jruby")
  end

  #
  # Generate the view
  def getModel(request)
    linkListing = BotListEntityLinksForm.new()
    linkListing.viewName = nil
    
    # Calculator Verification
    calc = generateCalcVerification()
    calc.solution = calc.firstInput + calc.secondInput
    linkListing.firstInput = calc.firstInput
    linkListing.secondInput = calc.secondInput
    linkListing.solution = calc.solution

    cur_session = request.getSession(false)
    if cur_session.nil?        
        @log.error("Invalid session object, botverse_submitlist")
        linkListing.viewName = 'errorInvalidView'
        return linkListing
    end
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if prev_calc != nil
      linkListing.prevSolution = prev_calc.solution
    end    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListEntityLinksValidator.new)
    return linkListing
  end
  
  #
  # Transform the form data to the
  # data object.
  def transformFormData(form)
    listing = BotListEntityLinks.new
    listing.urlTitle = form.urlTitle
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
      form.viewName = "botverse/botverse_submit"
      return form
    end    
    link = transformFormData(form)

    # Get the bean from the DB as opposed to off the session table
    sessionFactory = @daohelperlink.getSessionFactory()
    hbm_session = sessionFactory.openSession()
    tx = hbm_session.beginTransaction()
    hbm_session.save(link)
    tx.commit()
    hbm_session.close()

    # Navigate to the confirmation page
    form.viewName = "botverse/botverse_confirm"
    return form
  end
end

LinkListingController.new($controller)

## End of Script ##

