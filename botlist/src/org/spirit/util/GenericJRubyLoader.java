/*
 * Berlin Brown
 * Created on Nov 2, 2006
 * 
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
package org.spirit.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jruby.javasupport.bsf.JRubyEngine;
import org.spirit.util.io.JRubyIOHelper;
import org.springframework.context.ApplicationContext;

/**
 * Spring Controller that interfaces between spring and jruby; reads the ruby script and invokes the
 * correct model and view.
 * 
 * @author Berlin Brown 
 */
public class GenericJRubyLoader  {

	private Log log = LogFactory.getLog(getClass());

	private static int classInvokedId = 0;
	private static final String _CLASS_IDENTIFIER = "BotListRubyController: ";

	/**
	 * Ruby and BSF Managers.
	 */
	private BSFManager mManager;
	private JRubyEngine mEngine;
	
	private ApplicationContext applicationContext;

	// configuration parametes and defaults
	private String mScriptEngineClass = "org.jruby.javasupport.bsf.JRubyEngine";
	
	private String mScriptEngineName = "ruby";
	private String mScriptExtension = "rb";

	/** 
	 * Record keeping, when the script starts and ends.
	 */
	private long scriptStartTime = 0;
	private long scriptEndTime = 0;

	/**
	 * Default Constructor.	 
	 */
	public GenericJRubyLoader() {		
	}

	public static int getClassInvoked() {
		classInvokedId++;
		return classInvokedId; 
	}

	private static String CLASS_IDENTIFIER() {
		return getClassInvoked() + "| " + _CLASS_IDENTIFIER;
	}
	
	/**
	 * Establish bean objects for Ruby environment.
	 * 
	 * @throws BSFException
	 */
	protected final void initializeManagerBeans() throws BSFException {

		// Define the hibernate database objects for later use
		if (getApplicationContext() != null)
			mManager.declareBean("context", getApplicationContext(), ApplicationContext.class);
					
	}

	protected void initializeManager() throws BSFException {

		BSFManager.registerScriptingEngine(getScriptEngineName(), 
						getScriptEngineClass(), new String[] { getScriptExtension()});
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
	 * Read a Ruby Script into a String.
	 */
	public static String readRubyScript(String filename) {
		String rubyCode = "";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);			
			rubyCode = JRubyIOHelper.inputStreamToString(bis);
		} catch (IOException e) { 
			System.err.println("********* Error Loading Ruby Script *********");
			System.err.println(e.getMessage());
			e.printStackTrace();		
		} finally {
			JRubyIOHelper.close(bis);
			JRubyIOHelper.close(fis);
		}
		return rubyCode;
	}
	
	/**
	 * Run Ruby Script.
	 * 		 
	 * @param request
	 * @return
	 * @throws BSFException
	 */
	public Object runRubyScript(String filename) throws BSFException {
		BSFManager manager = this.getManager();
		Object controller = null;				
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			this.scriptStartTime = System.currentTimeMillis();
			log.info(CLASS_IDENTIFIER() + "Loading ruby script=" + filename);			
			fis = new FileInputStream(filename);
			bis = new BufferedInputStream(fis);			
			String rubyCode = JRubyIOHelper.inputStreamToString(bis);			
			controller = manager.eval(getScriptEngineName(), "(java)", 1, 1, rubyCode);				
		} catch (IOException e) { 
			System.err.println("********* Controller File Does Not Exist *********");
			System.err.println(e.getMessage());
			e.printStackTrace();		
		} catch (BSFException e) {
			e.printStackTrace();
			// rethrow the exception with a detailed error message.
			Throwable targetException = e.getTargetException();
			// BSFManager likes to get in a bad state when errors happen
			setManager(null);
			throw new BSFException(e.getReason(), targetException.getMessage(), targetException);
		} finally {			
			JRubyIOHelper.close(bis);
			JRubyIOHelper.close(fis);			
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
	
	/**************************************************************************
	 *  Setter and Getter Utility Methods
	 **************************************************************************/
	protected void onInitializeManager() {
	}

	private void setEngine(JRubyEngine jRubyEngine) {
		mEngine = jRubyEngine;
	}

	public Log getLogger() {
		return log;
	}
	public void setLogger(Log logger) {
		log = logger;
	}

	public void setManager(BSFManager manager) {
		mManager = manager;
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

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}	

}