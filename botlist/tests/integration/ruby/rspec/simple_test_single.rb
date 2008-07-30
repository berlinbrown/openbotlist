########################################
# Rspec tests
# Author: Berlin Brown
# Date: 3/10/2008
#
# Rspec simple single test.  Modify this test
# to only test one unit.
########################################

# Have to manually find the library files; example:
# spring_config = File.expand_path("#{File.dirname(__FILE__)}/../../../../WEB-INF/botlistings-servlet.xml")

require File.join(File.dirname(__FILE__), '../../../../WEB-INF/lib/ruby/web/foaf', 'edit_foaf')

include_class 'java.text.SimpleDateFormat' unless defined? SimpleDateFormat
include_class "java.util.Calendar" unless defined? Calendar
include_class 'org.apache.commons.logging.Log' unless defined? Log
include_class 'org.apache.commons.logging.LogFactory' unless defined? LogFactory

include_class 'org.spirit.bean.impl.BotListCoreUsers' unless defined? BotListCoreUsers
include_class 'org.spirit.util.BotListUniqueId' unless defined? BotListUniqueId
include_class 'org.acegisecurity.providers.encoding.Md5PasswordEncoder' unless defined? Md5PasswordEncoder
include_class 'org.spirit.bean.impl.BotListProfileSettings' unless defined? BotListProfileSettings
include_class 'org.spirit.contract.BotListContractManager' unless defined? BotListContractManager

include_class "org.spirit.contract.BotListCoreUsersContract"
include_class "org.spirit.bean.impl.BotListEntityLinks"
include_class "org.spirit.bean.impl.BotListUserLink"
include_class "org.spirit.bean.impl.BotListPostListing"
include_class "org.spirit.bean.impl.BotListCityListing"
include_class "org.spirit.bean.impl.BotListUserVisitLog"
include_class "org.spirit.bean.impl.BotListUserComments"
include_class "org.spirit.bean.impl.BotListForumGroup"
include_class "org.spirit.bean.impl.BotListCoreUsers"
include_class "org.spirit.bean.impl.BotListCatGroupTerms"
include_class "org.spirit.bean.impl.BotListCatLinkGroups"
include_class "org.spirit.bean.impl.BotListProfileSettings"
include_class "org.spirit.bean.impl.BotListMediaFeeds"
include_class "org.spirit.bean.impl.BotListActiveMediaFeeds"
include_class "org.spirit.bean.impl.BotListUserLinks"
include_class "org.spirit.apps.foaf.BotListEntityTypeFoaf" unless defined? BotListEntityTypeFoaf

describe "Creating simple mock objects <single test>" do
  before(:each) do
    @ac = $context
    @rad_controller = @ac.getBean("radController")
    @cur_sess_id = rand(1000000)
    @log = LogFactory::getLog("org.jruby")
  end

  it "Should return null or empty" do
    d = BotListFoaf.parseDate("1990-01-01")
    puts d
  end

  it "Should return valid parsed date" do
    a = nil
    b = ""
    c = 123123
    d = c
    d = nil
    a2 = BotListFoaf.valueEmpty?(a)
    b2 = BotListFoaf.valueEmpty?(b)
    c2 = BotListFoaf.valueEmpty?(c)
    d2 = BotListFoaf.valueEmpty?(d)
    puts d2
    puts b
  end

  it "Should test simple unit" do
    dao = @rad_controller.entityTypeFoafDao
    foaf = BotListFoaf::FoafHandler.new(dao, @log, "http://danbri.org/foaf.rdf")
    res = foaf.createFoaf
    sleep(4)
  end

  # End of module
end

