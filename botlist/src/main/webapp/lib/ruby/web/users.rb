##
## Berlin Brown
## 04/07/2007
##
## Ruby module associated with handling contract for the
## user objects.  Web manipulation is allowed.

include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.contract.BotListCoreUsersContract' unless defined? BotListCoreUsersContract
include_class 'org.spirit.util.BotListSessionManager' unless defined? BotListSessionManager
include_class 'org.spirit.util.BotListCookieManager' unless defined? BotListCookieManager

module CoreUsersWeb

  class CoreUsers
    
    attr_accessor :log, :user, :request, :response, :core_users
    def initialize()
      @log = nil
      @user = nil
      @request = nil
      @response = nil
      @core_users = nil      
    end
    
    # Create the user record, also store the session user value.
    # The user is officialy logged in.
    # Also, set the cookie parameters to signify that the user been logged in
    # (remember me functionality)
    def marshallData(dao_profile, user_bean)      
      if @request.nil?
        raise "ERR: CoreUsersWeb::CoreUsers(): Invalid Request Object"
      end
      
      user = @core_users.createUserContract(dao_profile, user_bean)
      # user is an instance of 'BotListCoreUsersContract'
      @user = user      
      
      cur_session = @request.getSession()
      # Commit the user contract object to session
      # Note: we may over write this value if it is already existing by auto login)
      cur_session.setAttribute(BotListSessionManager::USER_INFO_OBJECT, @user)
            
      BotListCookieManager.setRememberMeCookieParams(@response, @user)
      return @user
    end        
    
    # End of the class
  end
end
