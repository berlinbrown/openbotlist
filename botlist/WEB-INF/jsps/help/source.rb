##
## Berlin Brown
## 7/24/2007
##

class ViewSourceController
  
  def initialize(controller)
    @controller = controller
  end    
  
  # Generate the view
  def getModel(request)    
    # Audit the request
    @controller.auditLogPage(request, "source.html")
    return { }    
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

ViewSourceController.new($controller)

## End of Script ##

