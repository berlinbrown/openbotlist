##
## Berlin Brown
## TestCreateChart
##

require 'java'

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/util', 'chart_graphics')

include_class "junit.framework.Test"
include_class "junit.framework.TestCase"
include_class "junit.framework.TestSuite"
include_class 'junit.textui.TestRunner'

include_class "org.spirit.test.JRubyTestCase"

class TestCreateLineChart < JRubyTestCase

  def test()
    d = [ 5, 6, 7 ]
    lineChart = ChartGraphics::LineChart.new(500, 400, d, "")
    chart = lineChart.renderChart    
  end
end

#---------------------------------------
t = TestCreateLineChart.new($context)
begin
  t.test()
rescue Exception => e
  puts e.message
  raise e.message
end
t

