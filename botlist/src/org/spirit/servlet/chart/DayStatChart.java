/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Apr 25, 2007
 */
package org.spirit.servlet.chart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.bsf.BSFException;
import org.spirit.spring.BotListRubyController;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 */
public class DayStatChart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 478458550478406986L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	} // end of the function

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			 
		response.setHeader("Pragma", "no-cache");		
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png"); 
						
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		Object radDaoObj = context.getBean("radController");
		if (radDaoObj != null) {
			BotListRubyController radDao = (BotListRubyController) radDaoObj;			
			try {
				Object rubyController = radDao.getRubyServletController("/chart/daily_chart.rb", "WEB-INF/jsps", request, response);			
				if (rubyController == null) {
					return;
				}
				radDao.getEngine().call(rubyController, "process", new Object[] {});
			} catch (BSFException e) {			
				e.printStackTrace();
			}		
			//BufferedImage buf = chart.createBufferedImage(800, 450);	    
			//OutputStream out = response.getOutputStream();			     	  
			//ChartUtilities.writeBufferedImageAsPNG(out, buf);	     	
			//out.close();
		}
	      
	} // end of the method ...

}
