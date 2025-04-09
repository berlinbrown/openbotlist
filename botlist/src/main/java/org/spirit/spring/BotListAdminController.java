/* 
 *** Notice Update: 8/14/2007
 *** Copyright 2007 Berlin Brown
 *** Copyright 2006-2007 Newspiritcompany.com
 *** 
 *** This SOURCE FILE is licensed to NEWSPIRITCOMPANY.COM.  Unless
 *** otherwise stated, use or distribution of this program 
 *** for commercial purpose is prohibited.
 *** 
 *** See LICENSE.BOTLIST for more information.
 ***
 *** The SOFTWARE PRODUCT and CODE are protected by copyright and 
 *** other intellectual property laws and treaties. 
 ***  
 *** Unless required by applicable law or agreed to in writing, software
 *** distributed  under the  License is distributed on an "AS IS" BASIS,
 *** WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 *** implied.
 */
package org.spirit.spring;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.spirit.dao.BotListCityListingDAO;
import org.spirit.dao.BotListDocFileDAO;
import org.spirit.dao.BotListDocFileMetadataDAO;
import org.spirit.dao.BotListEntityLinksDAO;
import org.spirit.dao.BotListUserSearchDAO;
import org.spirit.form.base.BotListBaseForm;
import org.spirit.spring.handler.BotListAdminHandler;
import org.spirit.spring.util.BotListGenericHandler;
import org.spirit.util.BotListFileUploadType;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListAdminController extends SimpleFormController {
	
	private Log log = LogFactory.getLog(getClass());
	
	/**
	 * City Listing.
	 */
	private BotListCityListingDAO cityListingDao = null;
	
	private BotListEntityLinksDAO entityLinksDao = null;
	
	private BotListUserSearchDAO userSearchDao = null;
	
	/**
	 * Access the BotList Ruby DAO Handler.
	 */	
	private BotListRubyDAOHandler handler;
	
	private String springServletContext = "botlistadmin";
	
	private BotListGenericHandler genericPathHandler;
	
	private BotListDocFileMetadataDAO docFileMetadataDao = null;
	
	private BotListDocFileDAO docFileDao = null;
	
	/**
	 * File Upload Properties and Processor.
	 */
	private BotListFileUploadType fileUploadUtil;
	
	/**
	 * Ignore command action set.
	 */
	private final String [] IGNORE_SAVE_ACTION = { "generate" }; 
	
	/**
	 * Last command object instance.
	 */
	private Object lastCommand;
	
	/**
	 * @return the springServletContext
	 */
	public String getSpringServletContext() {
		return springServletContext;
	}
	
	/**
	 * @return the springServletContext
	 */
	public void setSpringServletContext(String springServletContext) {
		this.springServletContext = springServletContext;
		// Also instantiate the generic path handler
		genericPathHandler = new BotListGenericHandler(this.springServletContext);
	}
	
	protected Object invokeControllerMethod(HttpServletRequest request) {
		BotListAdminHandler handler = new BotListAdminHandler(this);		
		return handler.getModel(request);
	}
	
	/**
	 * Form Backing Object.
	 * You must create the command without commandClass being set - either 
	 * set commandClass or (in a form controller) override formBackingObject.
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
							
		Object model = null;
		model = invokeControllerMethod(request);
		this.setLastCommand(model);
		return model;
	}

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#showForm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.validation.BindException)
	 */
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {				
		Object command = null;			
		
		if (this.getLastCommand() != null) {
			command = this.getLastCommand();
		} else {
			command = this.getCommand(request);
		}
		
		String viewName = "/botlistadmin/" +  getGenericPathHandler().getDefaultViewNameFromRequest(request);		
		return getModelAndView(command, viewName);
	}
	
	
	protected ModelAndView getModelAndView(Object rubyResult, String defaultView) {

		String viewName = null;
		
		if (BotListBaseForm.class.isAssignableFrom(rubyResult.getClass())) {
			
			// Or use the Base Class Form to get the view name.
			BotListBaseForm form = (BotListBaseForm) rubyResult;
			if (form.getViewName() != null) {
				viewName = form.getViewName();
			}			
		}
		
		if (viewName == null) {
			viewName = defaultView;
		}
		
		TreeMap result = new TreeMap();
		result.put(getCommandName(), rubyResult);				
		return new ModelAndView(viewName, result);	
	}
	
	public String getDefaultViewNameFromRequest(HttpServletRequest request) {		
		return "/botlistadmin/" + getGenericPathHandler().getViewNameFromServletPath(request.getRequestURI().substring(1),
				request.getRequestURI(), request.getContextPath());
	}
	
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#processFormSubmission(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
				
		Object result = null;
		BotListAdminHandler handler = new BotListAdminHandler(this);
		result = handler.onSubmit(request, response, command, errors);
		boolean saveModeIgnore = false;
		
		for (int i = 0; i < IGNORE_SAVE_ACTION.length; i++) {
			if (request.getParameter(IGNORE_SAVE_ACTION[i]) != null) {
				// Save mode disabled
				saveModeIgnore = true;
			}
		}
		
		if ((errors.getErrorCount() > 0) && !saveModeIgnore) {			
			return new ModelAndView(getDefaultViewNameFromRequest(request), errors.getModel());
		} else {						
			return getModelAndView(result, getDefaultViewNameFromRequest(request));
		}
	}
	
	
	/****************************************************************
	 * 
	 *  Setters and Getter Utilities
	 *  
	 ****************************************************************/
	
	/**
	 * @return the handler
	 */
	public BotListRubyDAOHandler getHandler() {
		return handler;
	}


	/**
	 * @param handler the handler to set
	 */
	public void setHandler(BotListRubyDAOHandler handler) {
		this.handler = handler;
	}


	/**
	 * @return the cityListingDao
	 */
	public BotListCityListingDAO getCityListingDao() {
		return cityListingDao;
	}


	/**
	 * @param cityListingDao the cityListingDao to set
	 */
	public void setCityListingDao(BotListCityListingDAO cityListingDao) {
		this.cityListingDao = cityListingDao;
	}

	/**
	 * @return the entityLinksDao
	 */
	public BotListEntityLinksDAO getEntityLinksDao() {
		return entityLinksDao;
	}


	/**
	 * @param entityLinksDao the entityLinksDao to set
	 */
	public void setEntityLinksDao(BotListEntityLinksDAO entityLinksDao) {
		this.entityLinksDao = entityLinksDao;
	}


	/**
	 * @return the userSearchDao
	 */
	public BotListUserSearchDAO getUserSearchDao() {
		return userSearchDao;
	}


	/**
	 * @param userSearchDao the userSearchDao to set
	 */
	public void setUserSearchDao(BotListUserSearchDAO userSearchDao) {
		this.userSearchDao = userSearchDao;
	}

	/**
	 * @return the lastCommand
	 */
	public Object getLastCommand() {
		return lastCommand;
	}

	/**
	 * @param lastCommand the lastCommand to set
	 */
	public void setLastCommand(Object lastCommand) {
		this.lastCommand = lastCommand;
	}

	/**
	 * @return the genericPathHandler
	 */
	public BotListGenericHandler getGenericPathHandler() {
		if (genericPathHandler == null) {			
			genericPathHandler =  new BotListGenericHandler(this.getSpringServletContext()); 
		}
		return genericPathHandler;
	}

	/**
	 * @param genericPathHandler the genericPathHandler to set
	 */
	public void setGenericPathHandler(BotListGenericHandler genericPathHandler) {
		this.genericPathHandler = genericPathHandler;
	}

	/**
	 * @return the fileUploadUtil
	 */
	public BotListFileUploadType getFileUploadUtil() {
		return fileUploadUtil;
	}

	/**
	 * @param fileUploadUtil the fileUploadUtil to set
	 */
	public void setFileUploadUtil(BotListFileUploadType fileUploadUtil) {
		this.fileUploadUtil = fileUploadUtil;
	}

	/**
	 * @return the docFileDao
	 */
	public BotListDocFileDAO getDocFileDao() {
		return docFileDao;
	}

	/**
	 * @param docFileDao the docFileDao to set
	 */
	public void setDocFileDao(BotListDocFileDAO docFileDao) {
		this.docFileDao = docFileDao;
	}

	/**
	 * @return the docFileMetadataDao
	 */
	public BotListDocFileMetadataDAO getDocFileMetadataDao() {
		return docFileMetadataDao;
	}

	/**
	 * @param docFileMetadataDao the docFileMetadataDao to set
	 */
	public void setDocFileMetadataDao(BotListDocFileMetadataDAO docFileMetadataDao) {
		this.docFileMetadataDao = docFileMetadataDao;
	}

	/**
	 * @return the log
	 */
	public Log getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Log log) {
		this.log = log;
	}

}
