########################################
# Rspec tests
# Author: Berlin Brown
# Date: 3/10/2008
#
# Rspec simple single test.  Modify this test
# to only test one unit.
# 
# Single test - no help
########################################

# Have to manually find the library files; example:
# spring_config = File.expand_path("#{File.dirname(__FILE__)}/../../../../WEB-INF/botlistings-servlet.xml")

require File.join(File.dirname(__FILE__), '../../../../WEB-INF/lib/ruby/web/foaf', 'edit_foaf')

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar

include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.util.BotListUniqueId' unless defined? BotListUniqueId
include_class 'org.acegisecurity.providers.encoding.Md5PasswordEncoder' unless defined? Md5PasswordEncoder
include_class 'org.spirit.bean.impl.BotListProfileSettings' unless defined? BotListProfileSettings
include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager

include_class "org.spirit.contract.BotListCoreUsersContract"
include_class "org.spirit.bean.impl.BotListEntityLinks"
include_class "org.spirit.apps.foaf.BotListEntityTypeFoaf" unless defined? BotListEntityTypeFoaf

describe "Creating simple mock objects <single test>" do
  before(:each) do
    @cur_sess_id = rand(1000000)
  end

  it "Should return null or empty" do
    a = [ "1990-01-01", nil, "sdfsf" ]       
    d = BotListFoaf::parseDate(a[0])
    d = BotListFoaf::parseDate(a[1])
    d = BotListFoaf::parseDate(a[2])
    puts "#{d}"
  end

  it "Should return valid parsed date" do
    a = nil
    b = ""
    c = "#{123123}"
    d = c
    d = nil
    a2 = BotListFoaf::valueEmpty?(a)
    b2 = BotListFoaf::valueEmpty?(b)
    c2 = BotListFoaf::valueEmpty?(c)
    d2 = BotListFoaf::valueEmpty?(d)
    puts "#{d2} #{c2}"
  end

  # End of module
end

