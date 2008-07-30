##
## Berlin Brown
## TestCreateChart
##

require 'java'

include_class 'org.jfree.chart.ChartFactory'
include_class 'org.jfree.data.category.DefaultCategoryDataset'
include_class 'org.jfree.chart.plot.PlotOrientation'
include_class 'javax.swing.WindowConstants'
include_class 'org.jfree.ui.ApplicationFrame'
include_class 'java.awt.Dimension'
include_class 'org.jfree.ui.RefineryUtilities'
include_class 'org.jfree.ui.RectangleInsets'
include_class 'org.jfree.chart.ChartPanel'

include_class 'java.awt.Color'

include_class 'org.spirit.test.chart.MainTestCreateChart'

class TestCreateChart < ApplicationFrame
  
  def initialize(title)
    super(title)
  end

  def test()
    puts  "Launching demo"

    dataset = DefaultCategoryDataset.new
    dataset.addValue 150, "no.1", "Jan"
    dataset.addValue 210, "no.1", "Feb"
    dataset.addValue 390, "no.1", "Mar"
    dataset.addValue 300, "no.2", "Jan"
    dataset.addValue 400, "no.2", "Feb"
    dataset.addValue 200, "no.2", "Mar"
    
    chart = ChartFactory::createLineChart(
            "Line Chart Demo 1",
            "Type",
            "Value",
            dataset,
            PlotOrientation::VERTICAL,
            true,
            true,
            false)
    
    chart.setBackgroundPaint(Color::white)
    
    plot = chart.getPlot()
    plot.setBackgroundPaint(Color::lightGray)
    plot.setDomainGridlinePaint(Color::white)
    plot.setRangeGridlinePaint(Color::white)

    plot.setAxisOffset( RectangleInsets.new(5.0, 5.0, 5.0, 5.0) )
    #plot.setDomainCrosshairVisible(true)
    #plot.setRangeCrosshairVisible(true)

    # Create the chart panel
    dimen = Dimension.new(500, 700)
    chartPanel = ChartPanel.new(chart)
    chartPanel.setPreferredSize(dimen)
    #chartPanel.setMouseZoomable(true, false)
    #self.setContentPane(chartPanel)
    #puts self
    #puts self.setContentPane
  end  

  def to_s()
    return "Create Chart"
  end

end

#t = TestCreateChart.new()
#t.test()
#t.pack()
#t.setVisible(true)
#t
#MainTestCreateChart::main(nil)

