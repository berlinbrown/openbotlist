##
## Berlin Brown
## Ruby TestCase

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/actions', 'core_users')

require 'java'

include_class "junit.framework.Test"
include_class "junit.framework.TestCase"
include_class "junit.framework.TestSuite"
include_class 'junit.textui.TestRunner'
include_class "org.spirit.test.JRubyTestCase"

class TestXXX < JRubyTestCase
  attr :ac
  def initialize(context)
    @ac = context
  end
  def test()
    
  end
  def to_s
    "Current Test"
  end
end

#---------------------------------------
t = TestXXX.new($context)
begin
  t.test()
rescue Exception => e
  puts e.message
  raise e.message
end
t

## End of Script
