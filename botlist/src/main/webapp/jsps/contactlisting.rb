##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

include_class 'org.spirit.form.BotListPostListingForm'
include_class 'org.spirit.bean.impl.BotListPostListing'
include_class 'org.spirit.bean.impl.BotListCityListing'
include_class 'org.spirit.util.BotListSessionManager'
include_class 'org.spirit.spring.validate.BotListPostContactValidator'
include_class 'org.spirit.bean.impl.BotListCalculatorVerification'
include_class 'org.spirit.util.BotListUniqueId'

include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

class ContactListingController
		
  def initialize(controller)
    @controller = controller
    @daohelperpost = controller.postListingDao
    @log = LogFactory::getLog("org.jruby")
  end    
  
  
  #
  # tranformFormBean, only transferring fields
  # that are needed (on view)
  def transformFormBean(modelbean)
    
    form = BotListPostListingForm.new
    form.title = modelbean.title
    form.id = modelbean.get_id
    form.createdOn = modelbean.createdOn

    form
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
  # Generate the view
  def getModel(request)
    
    # Generate the calculation verification
    calc = generateCalcVerification()
    calc.solution = calc.firstInput + calc.secondInput

    viewid = request.getParameter("viewid")    
    if viewid != nil
      
      viewid.strip!
      invalid_sql = false
      if viewid.include? "'" or
          viewid.include? "\"" or
          viewid.include? "=" or
          viewid.include? ";" or
          viewid.include? "%22"
        invalid_sql = true
      end
    end
    
    postbean = @daohelperpost.readPostListing(viewid.to_i())
    postListing = transformFormBean(postbean)
    postListing.firstInput = calc.firstInput
    postListing.secondInput = calc.secondInput
    postListing.solution = calc.solution

    cur_session = request.getSession(false)
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if prev_calc != nil
      postListing.prevSolution = prev_calc.solution
    end
    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)

    # Write the unique id to prevent users from view next page improperly
    uniqueId = BotListUniqueId::getUniqueId()
    cur_session.setAttribute(BotListSessionManager::POST_UNIQUE_ID, uniqueId)
    postListing.uniqueId = uniqueId

    # Also set the validator
    @controller.setValidator(BotListPostContactValidator.new())

    #
    # For contact listing transform the model bean to
    # a form bean    
    return postListing
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      

    # Navigate to the email listing page
    form.viewName = "contactemail"
    id = form.get_id
    
    if errors and errors.getErrorCount() == 0
      response.sendRedirect("contactemail.html?id=#{id}")
    end

    return form
  end
end

ContactListingController.new($controller)

## End of Script ##

