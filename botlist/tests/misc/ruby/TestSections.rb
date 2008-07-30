##
## Berlin Brown
## TestSections.rb
##
## Also see:
## http://www.ruby-doc.org/stdlib/libdoc/test/unit/rdoc/

# deprecated

require 'java'

include_class "junit.framework.Test"
include_class "junit.framework.TestCase"
include_class "junit.framework.TestSuite"
include_class 'junit.textui.TestRunner'

include_class "org.spirit.test.JRubyTestCase"

class TestSections < JRubyTestCase
  attr :test_name
  def initialize(test_name)
    @test_name = test_name
  end
  def test()
    puts @test_name
  end

  def to_s
    "Current Test"
  end
end

t = TestSections.new("Test Sections")
t.test()
t

## End of Script
