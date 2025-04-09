##
## Berlin Brown
## 04/07/2007
##

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

include_class "java.util.Calendar" unless defined? Calendar

module GroupLinksUsersManager

  class GroupLinks
    
    attr_accessor :log, :user, :dao_groups, :dao_links, :linkGroup
    def initialize()
      @log = nil
      @groupLink = nil
      @linkGroup = nil
      @dao_groups = nil
      @dao_links = nil
    end
    
    def readGroupLinks(groupId, nextPage)
      groupId = KeywordProcessor.filterAlphaNumeric(groupId)
      # Get the link group bean based on the generated id
      groupLinkBean = @dao_groups.readLinkGroup(groupId)            
      gid = groupLinkBean.id     
      query = "from org.spirit.bean.impl.BotListGroupLinks as links where links.groupId = :groupId order by links.views desc, links.createdOn desc"
      links = @dao_links.pageGroupLinks(query, nextPage, BotListConsts::MAX_RESULTS_PAGE, gid)
      # Also preserve the link group
      @linkGroup = groupLinkBean
      return links
    end

    def createGroupLink(dao)
      begin
        sessionFactory = dao.getSessionFactory()
        hbm_session = sessionFactory.openSession()
        tx = hbm_session.beginTransaction()
        
        hbm_session.save(@groupLink)
        tx.commit()
      rescue Exception => e
        if @log
          @log.error(e)
        end
        raise e.message
      ensure
        hbm_session.close()
      end      
      # End of create user
    end
  end

  # End of Module
end
