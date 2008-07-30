##
## Berlin Brown
## 11/4/2006
##
## create_listing.rb
##

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/actions', 'group_links')
require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/util', 'botlist_core')

include_class 'org.spirit.form.BotListGenericPagingForm' unless defined? BotListGenericPagingForm
include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager

include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.BotListConsts' unless defined? BotListConsts
include_class 'org.spirit.util.text.KeywordProcessor' unless defined? KeywordProcessor

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

class GroupLinksController
		
  def initialize(controller)
    @controller = controller
    @daoLinkGroups = @controller.linkGroupsDao
    @daoGroupLinks = @controller.groupLinksDao
  end

  def getPageOffset(request)
    page = request.getParameter("offsetpage")
    if !page.nil?
      nPage = page.to_i()
    else
      nPage = 0
    end
    nPage
  end
  
  def getModel(request)
    
    groupId = request.getParameter("groupgenid")
    nextPage = getPageOffset(request)

    # Audit the request
    @controller.auditLogPage(request, "grouplinks.html")
    
    groupLinkObj = GroupLinksUsersManager::GroupLinks.new
    groupLinkObj.dao_groups = @daoLinkGroups
    groupLinkObj.dao_links = @daoGroupLinks
    coreLinks = groupLinkObj.readGroupLinks(groupId, nextPage)
    linkGroup = groupLinkObj.linkGroup
    userInfo = BotListContractManager::getUserInfo(request)
    pagingForm = BotListCore.createPagingForm(@daoGroupLinks.getLinkCount(linkGroup.get_id), nextPage)
    return {
      'coreLinks' => coreLinks,
      'userInfo' => userInfo,
      'linkGroup' => linkGroup,
      'pagingform' => pagingForm
    }
  end
  
  #
  # Processed when the form is submitted, 
  # see the controller 'processFormSubmission()' method
  def onSubmit(request, response, form, errors)      
    return form
  end
end

GroupLinksController.new($controller)

## End of Script ##

