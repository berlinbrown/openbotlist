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
include_class 'org.spirit.spring.validate.BotListEntityLinksValidator' unless defined? BotListEntityLinksValidator
include_class 'org.spirit.spring.validate.BotListEntityLinksRoverValidator' unless defined? BotListEntityLinksRoverValidator

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

  # Generate the view
  def getModel(request)
    linkListing = BotListEntityLinksForm.new
    linkListing.viewName = nil    
    # Also set the ROVER validator
    @controller.setValidator(BotListEntityLinksRoverValidator.new)    
    return linkListing
  end
   
  # Transform the form data to the data object.
  def transformFormData(form)
    listing = BotListEntityLinks.new
    listing.rating = 0
    listing.urlTitle =  KeywordProcessor::filterNonAscii(form.urlTitle)
    listing.fullName = "botrover99"
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
  # Transform Group Data
  def transformGroupFormData(form)
    groupId = KeywordProcessor.filterAlphaNumeric(form.generatedId)
    groupLinkBean = @dao_groups.readLinkGroup(groupId)            
    groupName = groupLinkBean.groupName
    listing = BotListGroupLinks.new
    listing.urlTitle =  KeywordProcessor::filterNonAscii(form.urlTitle)
    listing.fullName = "botrover99"
    
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
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)       
    # Check the errors first.
    if (errors.getErrorCount() > 0) || (form.roverVerify != BotListEntityLinksRoverValidator::ROVER_VERIFY)
      # Revert back to the entry screen
      form.viewName = "botverse/rover/botverse_rover"
      return form
    end
    
    puts form.generatedId
    if form.generatedId.nil?
      link = transformFormData(form)
    else
      link = transformGroupFormData(form)
    end
    # Get the bean from the DB as opposed to off the session table
    begin
      sessionFactory = @daohelperlink.getSessionFactory()
      hbm_session = sessionFactory.openSession()
      tx = hbm_session.beginTransaction()
      hbm_session.save(link)
      tx.commit()
    rescue Exception => e
      @log.error(e)
      raise e.message
    ensure
      hbm_session.close()
    end
    # Navigate to the confirmation page
    form.viewName = "botverse/rover/rover_confirm"
    return form
  end
end

LinkListingController.new($controller)

## End of Script ##

