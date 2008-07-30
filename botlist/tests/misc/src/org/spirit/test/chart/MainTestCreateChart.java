/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 23, 2007
 */
package org.spirit.test.chart;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class MainTestCreateChart extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3340804323578160907L;

	/**
	 * @param arg0
	 */
	public MainTestCreateChart(String arg0) {
		super(arg0);
	}

	public static void main(String [] args) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    dataset.addValue(150, "no.1", "Jan");
	    dataset.addValue(210, "no.1", "Feb");
	    dataset.addValue(390, "no.1", "Mar");
	    dataset.addValue(300, "no.2", "Jan");
	    dataset.addValue(400, "no.2", "Feb");
	    dataset.addValue(200, "no.2", "Mar");
	   
	    final JFreeChart chart = 
	    	ChartFactory.createLineChart(
	            "Line Chart Demo 1",
	            "Type",
	            "Value",
	            dataset,
	            PlotOrientation.VERTICAL,
	            true,
	            true,
	            false);
	    
	    chart.setBackgroundPaint(Color.white);
	    
	    final CategoryPlot plot = (CategoryPlot) chart.getPlot();
	    plot.setBackgroundPaint(Color.lightGray);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);

	    plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0) );
	    //plot.setDomainCrosshairVisible(true);
	    plot.setRangeCrosshairVisible(true);
	    	    
	    final LineAndShapeRenderer r = (LineAndShapeRenderer) plot.getRenderer();
        if (r instanceof LineAndShapeRenderer) {
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }
	    
	    Dimension dimen = new Dimension(500, 700);
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(dimen);	    	    
	    chartPanel.setMouseZoomable(true, false);
	    
	    MainTestCreateChart testChart = new MainTestCreateChart("Test"); 
	    
	    //testChart.pack();
        RefineryUtilities.centerFrameOnScreen(testChart);
        testChart.setVisible(true);
        testChart.setContentPane(chartPanel);
        while(testChart.isVisible());       
	}
}
