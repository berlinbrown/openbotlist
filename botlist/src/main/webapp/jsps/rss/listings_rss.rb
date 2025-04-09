##
## Berlin Brown
## 11/4/2006
##

require 'java'
include Java
import org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

class ListingsController
		
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.postListingDao
    @daohelpersects = @controller.postSectionsDao
  end    
  
  def getModel(request)
   
    query = "from org.spirit.bean.impl.BotListPostListing as posts order by posts.id desc"
    postListings = @daohelper.listPostListings(query)
    
    # Audit the request
    @controller.auditLogPage(request, "listings.html")
    map = BotListMapEntityLink.new
    map['listings'] = postListings
    return map
  end   
  def onSubmit(request, response, form, errors)
    return form
  end
end

ListingsController.new($controller)

## End of Script ##

