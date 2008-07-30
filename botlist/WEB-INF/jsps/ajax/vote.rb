##
## Berlin Brown
## 8/25/2007
##
## Ajax operations for voting

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager

class BotverseAjaxController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.entityLinksDao    
  end    
  
  # Generate the view
  def getModel(request)
  end
  
  # Create AjxVote Form
  def createAjxVoteForm(form_treemap, msgStatus, msg)
	form_treemap['ajx_last_oper'] = "vote"
	form_treemap['ajx_status'] = msgStatus
	form_treemap['ajx_code'] = "1001"
	form_treemap['ajx_message'] = msg
	return form_treemap
  end
  
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form_treemap, errors)
	  
	# First, check if user is able to access up-modding; is user logged in
	if !BotListSessionManager.isUserLoggedIn(request)
		# Set invalid response, vote failed.	
		createAjxVoteForm(form_treemap, "fail", "you will need to login in order to vote for links. <a href='/botlist/spring/profile/login.html' class='linklist_sample'>+login</a>")
		return form_treemap
	end
	  
    ratingId = form_treemap['ratingId']
    ratingId = ratingId.to_i
    operation = form_treemap['operation']
    link = @daohelper.readLinkListing(ratingId)
    
    if operation == 'upvote'
        link.rating = link.rating + 1
    elsif operation == 'downvote'
        link.rating = link.rating - 1
    end
    
    # Set response
    createAjxVoteForm(form_treemap, "pass", "the link you were interested in was voted up")	
    
    # Update link
    @daohelper.createLink(link)
    return form_treemap
  end
end

BotverseAjaxController.new($controller)

## End of Script ##

