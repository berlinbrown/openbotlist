##
## Berlin Brown
## Ruby TestCase

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/actions', 'core_users')

require 'java'

include_class 'org.spirit.bean.impl.BotListCoreUsers'
include_class "junit.framework.Test"
include_class "junit.framework.TestCase"
include_class "junit.framework.TestSuite"
include_class 'junit.textui.TestRunner'

include_class "org.spirit.test.JRubyTestCase"

include_class 'java.text.SimpleDateFormat'
include_class "java.util.Calendar"

class TestGroupLinks < JRubyTestCase
  attr :ac
  def initialize(context)
    @ac = context
  end
  def test()
    # Load the Controller for the Test
    c = @ac.getBean("radController")
    dao = c.coreUsersDao
    core_users = CoreUsersManager::CoreUsers.new()
    
    user = BotListCoreUsers.new
    user.userName = "billbobtest"
    user.userPassword = "apr2010"
    user.userEmail = "bob@bob.com"
    dob = "01/01/1981"
    
    # Use java's Date format to format the input date
    df = SimpleDateFormat.new("mm/dd/yyyy")
    cal = Calendar.getInstance()
    cal.time = df.parse(dob)
    user.dateOfBirth = cal
   
    core_users.user = user
    core_users.createUser(dao)
  end

  def to_s
    "Current Test #{@ac}"
  end
end

#---------------------------------------
t = TestGroupLinks.new($context)
begin
  t.test()
rescue Exception => e
  puts e.message
  raise e.message
end
t

## End of Script
