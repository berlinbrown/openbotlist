##
## Berlin Brown
## 04/07/2007
##

require 'java'

include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.util.BotListUniqueId' unless defined? BotListUniqueId
include_class 'org.acegisecurity.providers.encoding.Md5PasswordEncoder' unless defined? Md5PasswordEncoder
include_class 'org.spirit.bean.impl.BotListProfileSettings' unless defined? BotListProfileSettings
include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar


module CoreUsersManager

  DEFAULT_LINK_COLOR = '3838E5'
  
  class CoreUsers    
    attr_accessor :log, :user
    def initialize()
      @log = nil
      @user = nil
    end
    
    def convertDate(strDate)
      # Convert the date of birth from mm/dd/yyyy to database format
      df = SimpleDateFormat.new("mm/dd/yyyy")
      cal = Calendar.getInstance()
      cal.time = df.parse(strDate)
      return cal
    end
    
    def createUserContract(dao_profile, user_bean) 
      # Create the user contract and store in session
      # Use the java utility to get the user contract
      return BotListContractManager.createUserContract(dao_profile, user_bean)      
    end

    def unmarshallFormData(form)
      # Tranfer the form data into the data bean.
      user = BotListCoreUsers.new
      user.userName = form.userName.strip
      user.userPassword = form.userPassword.strip
      user.userEmail = form.userEmail.strip
      
      url = form.userUrl.strip
      if url
        user.userUrl = form.userUrl.strip
      end

      dob = "01/01/1776"
      user.dateOfBirth = convertDate(dob)
      @user = user
      return user
    end

    def authenticateUser(dao, userForm)
      begin
        userName = userForm.userName
        userPwd = userForm.userPassword
        
        # Attempt to find this user
        findUser = dao.readUser(userName)
        if not findUser 
          raise "login failed"
        end
        
        # Check password
        encode = Md5PasswordEncoder.new
        pwd = encode.encodePassword(userPwd, nil)
        dbPwd = findUser.userPassword
        if dbPwd != pwd
          raise "login failed"
        end

        # if found and password correct, set current user object
        @user = findUser
      rescue Exception => e
        err_msg = e.message
        raise "login failed"
      end
    end

    def createUser(dao)
      begin
        sessionFactory = dao.getSessionFactory()
        hbm_session = sessionFactory.openSession()
        tx = hbm_session.beginTransaction()
        
        uid = BotListUniqueId.getUniqueId()
        uname = @user.userName
        
        encode = Md5PasswordEncoder.new
        pwd = encode.encodePassword(@user.userPassword, nil)

        @user.userPassword = pwd
        @user.accountNumber = "#{uid}00#{uname}"
        @user.activeCode = 1

        # Set the login timestamp values
        @user.lastLoginSuccess = Calendar.getInstance()
        @user.lastLoginFailure = Calendar.getInstance()
        @user.createdOn = Calendar.getInstance()
        @user.updatedOn = Calendar.getInstance()
        @user.secretquesCode = 0
        hbm_session.save(@user)
        tx.commit()
        
        # Also Create the profile
        tx = hbm_session.beginTransaction()
        settings = BotListProfileSettings.new
        settings.userId = @user.get_id
        settings.linkColor = DEFAULT_LINK_COLOR
        hbm_session.save(settings)
        tx.commit()

      rescue Exception => e
        tx.rollback()
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
