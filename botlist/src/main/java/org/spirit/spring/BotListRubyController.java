/* 
 *** Notice Update: 8/14/2007
 *** Copyright 2007 Berlin Brown
 *** Copyright 2006-2007 Newspiritcompany.com
 *
 * -------------------------- COPYRIGHT_AND_LICENSE --
 * Botlist contains an open source suite of software applications for 
 * social bookmarking and collecting online news content for use on the web.  
 * Multiple web front-ends exist for Django, Rails, and J2EE.  
 * Users and remote agents are allowed to submit interesting articles.
 *
 * Copyright (c) 2007, Botnode.com (Berlin Brown)
 * http://www.opensource.org/licenses/bsd-license.php
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 *	    * Redistributions of source code must retain the above copyright notice, 
 *	    this list of conditions and the following disclaimer.
 *	    * Redistributions in binary form must reproduce the above copyright notice, 
 *	    this list of conditions and the following disclaimer in the documentation 
 *	    and/or other materials provided with the distribution.
 *	    * Neither the name of the Botnode.com (Berlin Brown) nor 
 *	    the names of its contributors may be used to endorse or promote 
 *	    products derived from this software without specific prior written permission.
 *	
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * -------------------------- END_COPYRIGHT_AND_LICENSE --
 */
package org.spirit.spring;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jruby.javasupport.bsf.JRubyEngine;
import org.spirit.cache.BotListCacheController;
import org.spirit.cache.BotListCacheEntity;
import org.spirit.dao.BotListPostListingDAO;
import org.spirit.dao.BotListUserLinkDAO;
import org.spirit.form.base.BotListBaseForm;
import org.spirit.util.BotListCookieManager;
import org.spirit.util.BotListFileUploadType;
import org.spirit.util.io.JRubyIOHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring Controller that interfaces between spring and jruby; reads the ruby script and invokes the
 * correct model and view.
 * 
 * @author Berlin Brown 
 */
public class BotListRubyController 
		extends BotListRubyDAOHandler {

	private Log log = LogFactory.getLog(getClass());
	private static final String _CLASS_IDENTIFIER = "BotListRubyController: ";
	private static final String PROCESSING_TIME = "processingtime";

	/**
	 * User Link Data Access Object.
	 */
	private BotListUserLinkDAO userLinkDAO = null;

	/**
	 * Post Listing DAO.
	 */
	private BotListPostListingDAO postListingDAO = null;

	/**
	 * Ruby and BSF Managers.
	 */
	private BSFManager mManager;
	private JRubyEngine mEngine;

	// configuration parametes and defaults
	private String mScriptEngineClass = "org.jruby.javasupport.bsf.JRubyEngine";

	private String mJspDir = "/WEB-INF/jsps";
	private String mScriptEngineName = "ruby";
	private String mScriptExtension = "rb";
	private String mInitScript = "/WEB-INF/jsps/INIT.rb";
	
	private String springServletContext = "spring";

	/** 
	 * Record keeping, when the script starts and ends.
	 */
	private long scriptStartTime = 0;
	private long scriptEndTime = 0;
	
	private BotListCacheController cacheController;
		
	public static final String DECLARE_BEAN_CONTEXT = "context";
	public static final String DECLARE_BEAN_REQUEST = "request";
	public static final String DECLARE_BEAN_RESPONSE = "response";
	public static final String DECLARE_BEAN_CONTROLLER = "controller";
	
	/**
	 * Last command object instance.
	 */
	private Object rubyCommand;
	
	/**
	 * File Upload Properties and Processor.
	 */
	private BotListFileUploadType fileUploadUtil; 

	/**
	 * Default Constructor.	 
	 */
	BotListRubyController() {
		this.setBindOnNewForm(true);
	}

	private static String CLASS_IDENTIFIER() {
		return _CLASS_IDENTIFIER;
	}
	
	/**
	 * This function is quite similar to the Jobster internal PathToViewController.
	 * Duplicated here so that the RAD module can be self contained.
	 */
	public String getViewNameFromServletPath(String servletPath, String uri, String contextPath) {

		String viewName = servletPath;
		int beg = 0, end = viewName.length();		
		if (end > 0 && viewName.charAt(0) == '/') {
			beg = 1;
		} else {
			// We are now assuming incoming servletPath is actually a full URL
			// beg = servletPath.lastIndexOf('/');
			if (getJspPathPos(uri, contextPath) != -1) {
				beg = getJspPathPos(uri, contextPath);
			}
		}				
		int dot = viewName.lastIndexOf('.');
		if (dot >= 0) {
			end = dot;
		}
		viewName = viewName.substring(beg, end);		
		return viewName;
	}

	public String getDefaultViewNameFromRequest(HttpServletRequest request) {
		//log.info(CLASS_IDENTIFIER() + "getDefaultViewNameFromRequest() - From Request: " + request.getRequestURL());
		// TODO: to be fixed
		return getViewNameFromServletPath(request.getRequestURI().substring(1),
				request.getRequestURI(), request.getContextPath());
	}

	protected ModelAndView getModelAndView(Object rubyResult, String defaultView) {
		String viewName = null;
		if (Map.class.isAssignableFrom(rubyResult.getClass())) {
			Map map = (Map) rubyResult;
			// look for an embedded view name in the model
			viewName = (String) map.get("viewName");
			
		} else if (BotListBaseForm.class.isAssignableFrom(rubyResult.getClass())) {
			
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
				
		// keep record of processing time
		scriptEndTime = System.currentTimeMillis();
		long diff = scriptEndTime - scriptStartTime;
		double diffS = diff / 1000.0d;
		result.put(PROCESSING_TIME, "" + diffS);
		return new ModelAndView(viewName, result);	
	}

	/**
	 * Print Request Information
	 */
	private void printRequestInfo(HttpServletRequest request) {					
		//log.info(CLASS_IDENTIFIER() + " showForm() uri=" + request.getRequestURI());		
	}

	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#showForm(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.validation.BindException)
	 */
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {		
		printRequestInfo(request);		
		Object command = null;
		if (this.getRubyCommand() != null) {
			command = this.getRubyCommand();
		} else {
			command = this.getCommand(request);
		}		
		return getModelAndView(command, getDefaultViewNameFromRequest(request));
	}
			
	/**
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#processFormSubmission(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {				
		// The command could be an instance of a map
		// or one of the Form beans
		if (command instanceof Map) {
			Map map = (java.util.Map) command;			
			for (Iterator iter = request.getParameterMap().entrySet().iterator(); iter.hasNext();) {
				Map.Entry e = (Map.Entry) iter.next();
				Object[] values = ((Object[]) e.getValue());
				map.put(e.getKey(), values[0]);				
			}
		}		
		try {
			Object result = invokeRubyControllerMethod(request, "onSubmit", 
						new Object[] { request, response, command, errors });		
			if (errors.getErrorCount() > 0) {
				return new ModelAndView(getDefaultViewNameFromRequest(request), errors.getModel());
			} else {						
				return getModelAndView(result, getDefaultViewNameFromRequest(request));
			}
		} catch(BSFException bfe) {
			bfe.printStackTrace();			
			log.error(bfe);
			Throwable targetException = bfe.getTargetException();			
			throw new BSFException(bfe.getReason(), targetException.getMessage(), targetException);
		}		
	}
	
	/**
	 * Get the botlist cache controller, get a new instance if not existing.
	 */
	private BotListCacheController getCacheController() {
		if (this.cacheController == null) {
			this.cacheController = new BotListCacheController();
			this.cacheController.setContext(this.getApplicationContext());
		}
		return this.cacheController;
	}
	
	/**
	 * Establish bean objects for Ruby environment.
	 * 
	 * @throws BSFException
	 */
	protected final void initializeManagerBeans() throws BSFException {		
		getCacheController();		
		// Define the hibernate database objects for later use
		mManager.declareBean(DECLARE_BEAN_CONTEXT, getApplicationContext(), ApplicationContext.class);
		if (this.getUserLinkDao() != null) {
			mManager.declareBean("daohelper", this.getUserLinkDao(), BotListUserLinkDAO.class);
		} else {
			log.error(CLASS_IDENTIFIER() + "-- ERR: Invalid Database Object Helper --");
		}		
		if (this.getPostListingDao() != null) {
			mManager.declareBean("daohelperlisting", getPostListingDao(), getPostListingDao().getClass());
		} else {
			log.error(CLASS_IDENTIFIER() + "-- ERR: Invalid Database Object Helper --");
		}
		
		// Set this object for DAO access
		mManager.declareBean(DECLARE_BEAN_CONTROLLER, this, BotListRubyDAOHandler.class);		
	}

	protected void initializeManager() throws BSFException {
		BSFManager.registerScriptingEngine(getScriptEngineName(), getScriptEngineClass(), new String[] { getScriptExtension()});
		mManager = new BSFManager();
		setEngine((JRubyEngine) (mManager.loadScriptingEngine(getScriptEngineName())));
		initializeManagerBeans();								
		onInitializeManager();		
	}

	public BSFManager getManager() throws BSFException {
		if (mManager == null) {
			initializeManager();
		}
		return mManager;
	}
	
	/**
	 * Read an entire ruby script in from a file. Isn't there a better way to do this?
	 * 
	 * @param filename
	 * @return
	 * @throws IOException	 
	 */
	private String readRubyScript(String filename) throws IOException {		
				
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		fis = new FileInputStream(filename);
		bis = new BufferedInputStream(fis);			
		String rubyCode = JRubyIOHelper.inputStreamToString(bis);
		
		JRubyIOHelper.close(bis);
		JRubyIOHelper.close(fis);		
		return rubyCode;
	}
	
	/**
	 * Get JSP Path Pos.
	 * i.e. extract 'mypath/file.html' from '/webapp/b/mypath/file.html'
	 */
	private int getJspPathPos(String uri, String contextPath) {
		
		// Example, /webapp/spring
		String target = contextPath + "/" + this.getSpringServletContext();
		int len = target.length();
		return uri.startsWith(target) ? len : -1;		
	}
	
	/**
	 * Execute the cache management for this particule ruby code.
	 * 1. If CACHE is empty, create new cache object.
	 * 2. If CACHE node is available, check node last modified vs file last modified 
	 *   2a. If reloaded needed, create new cache object.
	 *   2b. Otherwise, use existing CACHE node.  
	 */
	public String executeCache(String filename) throws IOException {
		String rubyCode = "";
		if (this.cacheController == null) {
			this.getCacheController();
		}
		BotListCacheEntity cacheObj = (BotListCacheEntity) this.cacheController.getManager().getCacheStore().get(filename);				
		if (cacheObj == null) {
			rubyCode = readRubyScript(filename);
			cacheObj = BotListCacheController.createCacheEntity(filename, rubyCode);
			this.cacheController.getManager().getCacheStore().put(filename, cacheObj);
			log.info(CLASS_IDENTIFIER() + "cache-manager: creating new cache entity=" + cacheObj);
		} else {
			// Determine if we need to reload the cache file, otherwise, use existing.
			File rFile = new File(filename);
			long lastModified = rFile.lastModified();
			long cacheLastModified = cacheObj.getLastModified().getTime();
			long cacheModCheck = ((lastModified - cacheLastModified) - (BotListCacheController.CACHE_FREE_TIME * 1000));
			boolean reloadFile = (cacheModCheck > 0);
			if (reloadFile) {
				rubyCode = readRubyScript(filename);
				cacheObj = BotListCacheController.createCacheEntity(filename, rubyCode);
				this.cacheController.getManager().getCacheStore().put(filename, cacheObj);
				log.info(CLASS_IDENTIFIER() + "cache-manager: reloading cache modifed-diff=" + cacheModCheck + " entity=" + cacheObj + "");
			} else {
				rubyCode = cacheObj.getRubyCodeData();				
			} 
		}
		return rubyCode;
	}
	
	/**
	 * Call system utilities before loading the ruby objects. 
	 */
	private void rubySystemInit(HttpServletRequest request) {
		// If user cooke is available, auto login.
		BotListCookieManager.systemGetUserCookieParams(request, this.getCoreUsersDao(), this.getProfileSettingsDao());		
	}
	
	/**
	 * Get Ruby Controller.		 
	 * @param request
	 * @return
	 * @throws BSFException
	 */
	public Object getRubyController(HttpServletRequest request) throws BSFException {
		BSFManager manager = getManager();
		manager.declareBean(DECLARE_BEAN_REQUEST, request, HttpServletRequest.class);
		final String CACHED_RUBY_CONTROLLER_ATTRIBUTE = "__RUBYCONTROLLER";
		Object controller = null;
		try {						
			// Currently, getServletPath() is returning an incorrect value
			// Use the full URL instead to get the correct servletPath
			String fullURI = request.getRequestURI().toString();
			int lastPos = getJspPathPos(request.getRequestURI(), request.getContextPath());
			if (lastPos == -1) {
				lastPos = 0;
			}
			String servletPath = "/" + fullURI.substring(lastPos + 1); 			
			String baseFilePath = getServletContext().getRealPath(getJspDir());						
			String filename = baseFilePath + servletPath.replaceAll("\\.[a-zA-Z]+$", "." + getScriptExtension());							
			String rubyCode = "";
			
			// System initialization.
			rubySystemInit(request);
			
			// Simple cache management.
			if (getCacheController().isEnableCaching()) {
				rubyCode = executeCache(filename);
			} else {
				rubyCode = readRubyScript(filename);
			}
			controller = manager.eval(getScriptEngineName(), "(java)", 1, 1, rubyCode);			
			request.setAttribute("__manager", manager);
			request.setAttribute(CACHED_RUBY_CONTROLLER_ATTRIBUTE, controller);
		} catch (FileNotFoundException fne) {
			log.error(fne);
			System.err.println("*** WARN: Script not found ********* [" + request.getRequestURI() + "]");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
			// we don't care if the controller file doesn't exist-- we don't require one.
			System.err.println("*** IO Error while reading script ********* [" + request.getRequestURI() + "]");
			System.err.println(e.getMessage());
		} catch (BSFException bfe) {			
			setManager(null);			
			bfe.printStackTrace();			
			log.error(bfe);
			Throwable targetException = bfe.getTargetException();			
			throw new BSFException(bfe.getReason(), targetException.getMessage(), targetException);
		}
		return controller;
	}
	
	/**
	 * Load the ruby script based from a servlet.
	 * The path will correspond with the actual ruby script, eg:
	 * 
	 * "/chart/daily_chart.rb"
	 * 
	 * @throws BSFException
	 */
	public Object getRubyServletController(String path, String jspdir, HttpServletRequest request, HttpServletResponse response) 
			throws BSFException {
		BSFManager manager = getManager();	
		manager.declareBean(DECLARE_BEAN_REQUEST, request, HttpServletRequest.class);
		manager.declareBean(DECLARE_BEAN_RESPONSE, response, HttpServletResponse.class);
		final String CACHED_RUBY_CONTROLLER_ATTRIBUTE = "__RUBYCONTROLLER";
		Object controller = null;
		try {						 
			String baseFilePath = getServletContext().getRealPath(jspdir);						
			String filename = baseFilePath + path;							
			String rubyCode = "";			
			if (getCacheController().isEnableCaching()) {
				rubyCode = executeCache(filename);
			} else {
				rubyCode = readRubyScript(filename);
			}
			controller = manager.eval(getScriptEngineName(), "(java)", 1, 1, rubyCode);			
			request.setAttribute("__manager", manager);
			request.setAttribute(CACHED_RUBY_CONTROLLER_ATTRIBUTE, controller);

		} catch (IOException e) {
			log.error(e);			
			System.err.println(e.getMessage());
		} catch (BSFException bfe) {			
			setManager(null);			
			bfe.printStackTrace();			
			log.error(bfe);
			Throwable targetException = bfe.getTargetException();			
			throw new BSFException(bfe.getReason(), targetException.getMessage(), targetException);
		}
		return controller;
	}

	/**
	 * Invoke Ruby Controller Method.
	 * 
	 * @param rubyController
	 * @param methodName
	 * @param args
	 * @return
	 * @throws BSFException
	 */
	protected Object invokeRubyControllerMethod(Object rubyController, String methodName, Object[] args) throws BSFException {
		if (rubyController == null) {
			return null;
		}
		return getEngine().call(rubyController, methodName, args);
	}

	/**
	 * Invoke the Ruby Controller Method.
	 * 
	 * @param request
	 * @param methodName
	 * @param args
	 * @return
	 * @throws BSFException
	 */
	protected Object invokeRubyControllerMethod(HttpServletRequest request, String methodName, Object[] args) throws BSFException {
		return invokeRubyControllerMethod(getRubyController(request), methodName, args);
	}

	/**
	 * Form Backing Object.
	 * You must create the command without commandClass being set - either 
	 * set commandClass or (in a form controller) override formBackingObject.
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
				
		this.scriptStartTime = System.currentTimeMillis();		
		Object model = null;
		Object mapOrController = getRubyController(request);
	
		if (mapOrController != null) {
			if (Map.class.isAssignableFrom(mapOrController.getClass())) {
				model = mapOrController;				
			} else {
				try {
					// Script errors and other BSF type errors are normally thrown
					// here.  Log the error and print stacktrace
					model = invokeRubyControllerMethod(mapOrController, "getModel", new Object[] { request });
				} catch(BSFException be) {
					be.printStackTrace();
					log.error("Error: executing formBackingObject() script 'getModel', url=" + request.getRequestURL());
					log.error(be);
					// Stop normal execution
					// TODO: redirect to error page?  have some kind of backout routine?
					throw be;
				} // End - try - catch bsfmanager exceptions
			}
		}		
		long curEndTime = System.currentTimeMillis();
		long diff = curEndTime - scriptStartTime;
		log.debug("formBackingObject(): processing=" + diff + " ms");
		
		if (model == null) {
			TreeMap locTreeMap = new TreeMap();
			this.setRubyCommand(locTreeMap);
			return locTreeMap;
		}		
		this.setRubyCommand(model);
		return model;
	}

	/**************************************************************************
	 *  Setter and Getter Utility Methods
	 **************************************************************************/
	protected void onInitializeManager() {
	}

	private void setEngine(JRubyEngine jRubyEngine) {
		mEngine = jRubyEngine;
	}

	public String getJspDir() {
		return mJspDir;
	}
	public void setJspDir(String jspDir) {
		mJspDir = jspDir;
	}

	public Log getLogger() {
		return log;
	}
	public void setLogger(Log logger) {
		log = logger;
	}

	public void setManager(BSFManager manager) {
		this.mManager = manager;
	}
	public JRubyEngine getEngine() {
		return mEngine;
	}

	public String getScriptEngineClass() {
		return mScriptEngineClass;
	}

	public void setScriptEngineClass(String scriptEngineClass) {
		mScriptEngineClass = scriptEngineClass;
	}

	public String getScriptEngineName() {
		return mScriptEngineName;
	}

	public void setScriptEngineName(String scriptEngineName) {
		mScriptEngineName = scriptEngineName;
	}

	public String getScriptExtension() {
		return mScriptExtension;
	}

	public void setScriptExtension(String scriptExtension) {
		mScriptExtension = scriptExtension;
	}

	public String getInitScript() {
		return mInitScript;
	}

	public void setInitScript(String initScript) {
		mInitScript = initScript;
	}

	/******************************************************
	 * 
	 *  Set the Data Access Object
	 *  
	 ******************************************************/
	public void setUserLinkDao(BotListUserLinkDAO dao) {
		this.userLinkDAO = dao;
	}

	public BotListUserLinkDAO getUserLinkDao() {
		return this.userLinkDAO;
	}
	
	/**
	 * @return the postListingDAO
	 */
	public BotListPostListingDAO getPostListingDao() {
		return postListingDAO;
	}

	/**
	 * @param postListingDAO the postListingDAO to set
	 */
	public void setPostListingDao(BotListPostListingDAO postListingDAO) {
		this.postListingDAO = postListingDAO;
	}

	/**
	 * @return the springServletContext
	 */
	public String getSpringServletContext() {
		return springServletContext;
	}

	/**
	 * @param springServletContext the springServletContext to set
	 */
	public void setSpringServletContext(String springServletContext) {
		this.springServletContext = springServletContext;
	}
	
	/******************************************************
	 * 
	 *  Set the File Upload Properties
	 *  
	 ******************************************************/

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
	
	public int parseFileUploadRequest(HttpServletRequest request) {
		
		int res = -1;		
		if (this.getFileUploadUtil() != null) {
			try {
				res = this.fileUploadUtil.uploadFiles(request);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			log.error("ERR: File Upload Bean is NULL, should be set properly in the spring configuration");
		}
		return res;
	}

	/**
	 * @return the rubyCommand
	 */
	public Object getRubyCommand() {
		return rubyCommand;
	}

	/**
	 * @param rubyCommand the rubyCommand to set
	 */
	public void setRubyCommand(Object rubyCommand) {
		this.rubyCommand = rubyCommand;
	}
	
}