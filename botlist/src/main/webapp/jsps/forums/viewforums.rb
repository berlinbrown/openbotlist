##
## Berlin Brown
## 11/4/2006
##

require 'java'
include Java

KeywordProcessor = org.spirit.util.text.KeywordProcessor unless defined? KeywordProcessor
BotListSessionManager = org.spirit.util.BotListSessionManager unless defined? BotListSessionManager

class ViewForumsController
  
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.forumGroupDao
    @daohelpercomments = @controller.userCommentsDao
    @dao_banner = @controller.adminMainBannerDao
  end    
  
  # Get a view instance of the link the user clicked on
  def getViewLink(request)
    viewid = request.getParameter("viewid")
    if !viewid.nil? 
      newviewid = KeywordProcessor.filterAlphaNumeric(viewid)
    end
    return nil if newviewid.nil?
    newviewid.to_i()
  end
  
  # Get a view instance of the link the user clicked on
  def getForum(request)

    viewid = getViewLink(request)
    return nil if viewid.nil?
    forum = @daohelper.readForum(viewid.to_i())
    cur_session = request.getSession(false)
    cur_session = BotListSessionManager::safeCreateSession(request) if cur_session.nil?
    if !cur_session.nil?
      cur_session.setAttribute(BotListSessionManager::CURRENT_FORUM, forum)    
    end    
    return forum

  end

  def getModel(request)

    forum = getForum(request)
    forumId = forum.get_id
    
    query = "from org.spirit.bean.impl.BotListUserComments as comments where comments.forumId = '#{forumId}' and comments.commentId is null order by comments.id desc"
    comments = @daohelpercomments.listComments(query)    
    
    # Extract the banner headline to display
    banner = @dao_banner.readBanner('botverse')
    headline = banner.headline if !banner.nil?
    
    # View forums audit
    @controller.auditLogPage(request, "viewforums.html")
    map = BotListMapEntityLink.new
    map['listings'] = comments
    map['headline'] = headline
    return map
  end
  
  def onSubmit(request, response, form, errors)
    return form
  end
end

ViewForumsController.new($controller)

## End of Script ##
