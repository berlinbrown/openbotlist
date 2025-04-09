########################################
## Berlin Brown
## 11/4/2006
##
## THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
## "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
## LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
## A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
## CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
## EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
## PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
## PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
## LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
## NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
## SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
########################################

require 'java'
include Java

BotListMapEntityLink org.spirit.form.ext.BotListMapEntityLink unless defined? BotListMapEntityLink

BotListSessionManager = org.spirit.util.BotListSessionManager unless defined? BotListSessionManager
KeywordProcessor = org.spirit.util.text.KeywordProcessor unless defined? KeywordProcessor
BotListGenericValidator = org.spirit.spring.validate.BotListGenericValidator unless defined? BotListGenericValidator

Log = org.apache.commons.logging.Log unless defined? Log
LogFactory = org.apache.commons.logging.LogFactory unless defined? LogFactory

class ViewCommentsController	
  def initialize(controller)
    @controller = controller
    @daohelper = @controller.userCommentsDao
    @daohelperlink = @controller.entityLinksDao    
    @log = LogFactory::getLog("org.jruby")
  end    
     
  # Get a view instance of the link the user clicked on
  def getViewLink(request)
    viewid = request.getParameter("viewid")    
    if !viewid.nil? 
      newviewid = KeywordProcessor.filterAlphaNumeric(viewid)
    end
    return nil if newviewid.nil?
    link = @daohelperlink.readLinkListing(newviewid.to_i())
    cur_session = request.getSession(false)
    cur_session = BotListSessionManager::safeCreateSession(request) if cur_session.nil?
    if !cur_session.nil?
      cur_session.setAttribute(BotListSessionManager::CURRENT_LINK_VIEW, link)
    end    
    return link
  end
  def updateLinkViews(link)
    # Get the bean from the DB as opposed to off the session table
    sessionFactory = @daohelperlink.getSessionFactory()
    hbm_session = sessionFactory.openSession()
    tx = hbm_session.beginTransaction()
    if !link.views.nil?
      link.views = link.views + 1
    else
	  link.views = 0
    end    
    hbm_session.saveOrUpdate(link)
    tx.commit
    hbm_session.close
  end
  def getModel(request)    
    link = getViewLink(request)
    linkId = link.get_id
    query = "from org.spirit.bean.impl.BotListUserComments as comments where comments.linkId = '#{linkId}' order by comments.id"
    comments = @daohelper.listComments(query)
    
    # Update the link views for later filtering
    # Removed 7/6/2007
    updateLinkViews(link)
    
    # Audit the request
    @controller.auditLogPage(request, "linkviewcomments.html?viewid=#{linkId}")    
    map = BotListMapEntityLink.new  
    map['listings'] = comments
    map['link'] = link
    return map
  end   
  def onSubmit(request, response, form, errors)
    @log.error("invalid request through submit form=linkviewcomments")
    @controller.setValidator(BotListGenericValidator.new)    
    return form
  end
end
ViewCommentsController.new($controller)

## End of Script ##

