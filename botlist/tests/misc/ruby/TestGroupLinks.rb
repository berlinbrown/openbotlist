##
## Berlin Brown
## TestSections.rb
##
## Also see:
## http://www.ruby-doc.org/stdlib/libdoc/test/unit/rdoc/

require 'java'

include_class "junit.framework.Test"
include_class "junit.framework.TestCase"
include_class "junit.framework.TestSuite"
include_class 'junit.textui.TestRunner'

include_class "org.spirit.test.JRubyTestCase"

class TestGroupLinks < JRubyTestCase
  
  attr_accessor :ac
  def initialize(context)
    @ac = context
  end
  def test()
    c = @ac.getBean("radController")
    dao = c.linkGroupsDao
    n = "http://www.kjslfs22222.com"
    linkGroup = dao.readLinkGroup("aaf9dfb546f650d5fa614156000info")
    linkGroupId = linkGroup.id
    puts linkGroup.groupName
  end
  
  def to_s
    "Current Test"
  end
end

t = TestGroupLinks.new($context)
t.test()
t

## End of Script
