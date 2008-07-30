##
## Berlin Brown
## 4/20/2007
## 

require 'java'
include_class 'org.jfree.chart.ChartUtilities' unless defined? ChartUtilities
include_class('java.util.Calendar') { 'JCalendar' } unless defined? JCalendar

require File.join(File.dirname(__FILE__), '../../WEB-INF/lib/ruby/util', 'chart_graphics')

class DailyChartController

  def initialize(controller, request, response)
    @controller = controller
    @request = request
    @response = response
    @dao_visitlog = @controller.userVisitLogDao
  end
  
  def process()
    
    d = []
    (-6..0).each{|i|
      curCal = JCalendar::getInstance()
      curCal.add(JCalendar::DATE, i)
      curStat = @dao_visitlog.getVisitAuditOnDate(curCal)
      d << curStat
    }         
    lineChart = ChartGraphics::LineChart.new(500, 400, d, "")
    chart = lineChart.renderChart    
    buf = chart.createBufferedImage(380, 240)
    out = @response.getOutputStream()
    ChartUtilities.writeBufferedImageAsPNG(out, buf)
    out.close()
  end

end

DailyChartController.new($controller, $request, $response)
