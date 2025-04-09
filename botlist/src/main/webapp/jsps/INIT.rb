class RadApp
  # File is deprecated
  def initializeApplication
    reinitApplication()
  end
  
  def reinitApplication()
    # This method is automatically run when the RAD controller is first initialized.
    # It's useful for setting up global variables and other global context.
    puts "INFO: ReinitApplication"
  end
  
  def getModel(request)
    puts "INFO: GetModel()"
    reinitApplication()			
  end
end

RadApp.new()
