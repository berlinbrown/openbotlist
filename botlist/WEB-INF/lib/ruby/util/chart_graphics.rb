##
## Berlin Brown
## 04/07/2007
##
## JRuby module for creating line charts, based on:
## kfahlgren.com/code/sparkline.jrb
##

require 'java'

include_class 'org.jfree.chart.ChartUtilities' unless defined? ChartUtilities
include_class 'org.jfree.chart.JFreeChart' unless defined? JFreeChart
include_class 'org.jfree.chart.axis.NumberAxis' unless defined? NumberAxis
include_class 'org.jfree.chart.plot.XYPlot' unless defined? XYPlot
include_class 'org.jfree.chart.renderer.xy.StandardXYItemRenderer' unless defined? StandardXYItemRenderer
include_class 'org.jfree.data.xy.XYSeries' unless defined? XYSeries
include_class 'org.jfree.data.xy.XYSeriesCollection' unless defined? XYSeriesCollection
include_class 'org.jfree.chart.plot.PlotOrientation' unless defined? PlotOrientation

module ChartGraphics
  
  class LineChart
   
    def initialize(width=500, height=400, data=[], title="")
      @log = nil      
      @width = width
      @height = height
      @data = data
      @title = title
    end

    def renderChart()
      series = XYSeries.new(@title)
      # Add the data to the series
      data = @data
      data = [] if data.nil?
      data.each_index {|i|
        series.add(i, data[i])
      }
      dataset = XYSeriesCollection.new
      dataset.addSeries(series)      
      return self.createChart(dataset)
    end

    def createChart(dataset)
      
      x = NumberAxis.new
      x.setTickLabelsVisible(true)
      x.setTickMarksVisible(true)
      x.setAxisLineVisible(true)
      x.setNegativeArrowVisible(false)
      x.setPositiveArrowVisible(false)
      x.setVisible(true)
      
      y = NumberAxis.new
      y.setTickLabelsVisible(true)
      y.setTickMarksVisible(true)
      y.setAxisLineVisible(true)
      y.setNegativeArrowVisible(false)
      y.setPositiveArrowVisible(false)
      y.setVisible(true)
      
      plot = XYPlot.new
      plot.setDataset(dataset)
      plot.setDomainAxis(x)
      plot.setDomainGridlinesVisible(true)
      plot.setDomainCrosshairVisible(true)
      plot.setRangeGridlinesVisible(true)
      plot.setRangeCrosshairVisible(false)
      plot.setRangeAxis(y)
      plot.setOutlinePaint(nil)
      plot.setRenderer(StandardXYItemRenderer.new(StandardXYItemRenderer::LINES))
      
      chart = JFreeChart.new(nil, JFreeChart::DEFAULT_TITLE_FONT, plot, false)
      chart.setBorderVisible(false)
      return chart      
    end
   
    # End of Class
  end

  # End of Module
end

