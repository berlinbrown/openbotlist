##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##
# -------------------------- COPYRIGHT_AND_LICENSE --
# Botlist contains an open source suite of software applications for 
# social bookmarking and collecting online news content for use on the web.  
# Multiple web front-ends exist for Django, Rails, and J2EE.  
# Users and remote agents are allowed to submit interesting articles.
#
# Copyright (c) 2007, Botnode.com (Berlin Brown)
# http://www.opensource.org/licenses/bsd-license.php
#
# All rights reserved.
#
# Redistribution and use in source and binary forms, with or without modification, 
# are permitted provided that the following conditions are met:
#
#	    * Redistributions of source code must retain the above copyright notice, 
#	    this list of conditions and the following disclaimer.
#	    * Redistributions in binary form must reproduce the above copyright notice, 
#	    this list of conditions and the following disclaimer in the documentation 
#	    and/or other materials provided with the distribution.
#	    * Neither the name of the Botnode.com (Berlin Brown) nor 
#	    the names of its contributors may be used to endorse or promote 
#	    products derived from this software without specific prior written permission.
#	
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
# "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
# LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
# A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
# CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
# EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
# PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
# PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
# LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
# -------------------------- END_COPYRIGHT_AND_LICENSE --
#

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/web', 'web_core')

include_class 'org.spirit.form.BotListPostListingForm'
include_class 'org.spirit.bean.impl.BotListPostListing'
include_class 'org.spirit.bean.impl.BotListCityListing'
include_class 'org.spirit.bean.impl.BotListPostImageMetadata'
include_class 'org.spirit.bean.impl.BotListCalculatorVerification'

include_class 'org.spirit.util.BotListSessionManager'
include_class 'org.spirit.util.BotListUniqueId'

include_class 'org.spirit.spring.validate.BotListPostListingValidator'

include_class('java.io.File') { 'JFile' }
include_class('java.util.ArrayList') { 'JArrayList' }
include_class 'org.apache.commons.logging.Log'
include_class 'org.apache.commons.logging.LogFactory'

include_class 'org.spirit.util.markdown.BotListMarkUtil'

class CreateListingController
		
  def initialize(controller)
    @controller = controller
    @daohelpercity = controller.cityListingDao
    @daohelperpost = controller.postListingDao
    @log = LogFactory::getLog("org.jruby")
  end    

  def getCitySessionInfo(request)
    cur_session = request.getSession(false)
    citybean = cur_session.getAttribute(BotListSessionManager::CURRENT_CITY_SETTING)
    sectionbean = cur_session.getAttribute(BotListSessionManager::CURRENT_SECTION_SETTING)
    return [citybean, sectionbean]
  end
  
  # Generate the view
  def getModel(request)
    postListing = BotListPostListingForm.new()
    postListing.viewName = nil
    
	# Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateEmail(postListing, nil)

    # Get the previous session objects
    sessionTuple = getCitySessionInfo(request)
    if not sessionTuple[0] or not sessionTuple[1]
      return {
        'viewName' => 'errorInvalidView'
      }
    end

    postListing.cityListing = sessionTuple[0]
    postListing.postSection = sessionTuple[1]

    calc = generateCalcVerification()
    postListing.firstInput = calc.firstInput
    postListing.secondInput = calc.secondInput
    postListing.solution = calc.solution

    cur_session = request.getSession(false)
    # Check the previous session object for the previous value
    # used in conjunction with onSubmit()
    prev_calc = cur_session.getAttribute(BotListSessionManager::CALC_VERIFY_OBJECT)
    if prev_calc != nil
      postListing.prevSolution = prev_calc.solution
    end    
    cur_session.setAttribute(BotListSessionManager::CALC_VERIFY_OBJECT, calc)
    
    # Also set the validator
    @controller.setValidator(BotListPostListingValidator.new())   
    return postListing
  end
  
  
  #
  # Transform the form data to the
  # data object.
  def transformFormData(form)
    listing = BotListPostListing.new
    listing.email = form.email
    
    # Perform markdown html conversion on form message
    markdown = BotListMarkUtil.new
    listing.message = markdown.convert(form.message)
    listing.title = form.title
    listing.age = form.age
    listing.category = 'general'
    if form.mainUrl
      listing.mainUrl = form.mainUrl
    end
    if form.keywords
      listing.keywords = form.keywords
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
  # Perform the download to the server and create the
  # data bean object.
  def uploadCurFile(request, form, entity)
    uploadDir = @controller.fileUploadUtil.uploadDir
    curFilename = entity.originalFilename

    uid = BotListUniqueId::getUniqueId()
    newFilename = "img#{uid}.jpg"

    fileUpload = JFile.new("#{uploadDir}/#{newFilename}")
    entity.transferTo(fileUpload)

    # Create the image metadata bean
    metadata = BotListPostImageMetadata.new
    metadata.imageFilesize = entity.size
    metadata.imageFilename = newFilename
    metadata.imageOriginalname = curFilename
    return metadata
  end

  #
  # Create the image hibernate record
  def createListingImage(listing, imageTuple)
    if imageTuple
      
      for img in imageTuple
        if img
          if not listing.images
            listing.images = JArrayList.new
          end
          img.adlistId = listing.get_id
          listing.images.add(img)
        end
      end
      
      # End of if - image
    end
  end

  #
  # Upload an image file
  def uploadImageFile(request, form)
    
    imageTuple = []
    imageTuple[0] = nil
    imageTuple[1] = nil
    # Attempt to upload the first file
    if form.uploadFilenameFirst and not form.uploadFilenameFirst.isEmpty()
      entity = form.uploadFilenameFirst
      curFilename = entity.originalFilename
      if curFilename.downcase =~  /\.jpg$/
        metadata = uploadCurFile(request, form, entity)    
        imageTuple[0] = metadata
      else
        @log.info("User attempting to upload non-jpeg file #{curFilename}")
      end
    end

    if form.uploadFilenameSecond and not form.uploadFilenameSecond.isEmpty()
      entity = form.uploadFilenameSecond
      curFilename = entity.originalFilename
      if curFilename.downcase =~  /\.jpg$/
        metadata = uploadCurFile(request, form, entity)    
        imageTuple[1] = metadata
      else
        @log.info("User attempting to upload non-jpeg file #{curFilename}")
      end

      # Create the image bean
      imageTuple[1] = metadata
    end

    return imageTuple
  end
    
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)   
	
	# Also, check the username for cookie setting
    cookieManager = BotListWebCore::WebCookieManager.new(request)
    cookieManager.getCreateEmail(form, response)
	
    # Check the errors first.
    if errors.getErrorCount() > 0
      # Revert back to the entry screen
      form.viewName = "create_listing"
      return form
    end
    
    # Get the city object from the session
    cur_session = request.getSession(false)
    citybean = cur_session.getAttribute(BotListSessionManager::CURRENT_CITY_SETTING)
    cityId = citybean.get_id

    # Upload image file
    imageTuple = uploadImageFile(request, form)

    # Transfer data object bean
    listing = transformFormData(form)

    # Get the bean from the DB as opposed to off the session table
    sessionFactory = @daohelpercity.getSessionFactory()
    hbm_session = sessionFactory.openSession()
    tx = hbm_session.beginTransaction()
    city = hbm_session.load("org.spirit.bean.impl.BotListCityListing", cityId, nil)
    listing.cityId = cityId
    listing.sectionId = form.postSection.get_id
    city.listings.add(listing)
    tx.commit()

    tx = hbm_session.beginTransaction()
    createListingImage(listing, imageTuple)
    tx.commit()
    hbm_session.close()

    # Navigate to the confirmation page
    form.viewName = "createconfirm"
    return form
  end
end

CreateListingController.new($controller)

## End of Script ##

