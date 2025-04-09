/**
 * Berlin Brown
 * 
 */
package org.spirit.servlet.tags;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.Util;

/**
 * @author Berlin Brown
 */
public class BotListTimePastTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887421688538819849L;
	
	private String value_;                      // stores EL-based property
	private Date value;                      	// 'value' attribute

	private String var;                       	// 'var' attribute
	private int scope;                         	// 'scope' attribute

	//*********************************************************************
	// Constructor and initialization
	//*********************************************************************

	public BotListTimePastTag() {
		super();
		init();
	}

	private void init() {
		value_ = null;
		value = null;		
		scope = PageContext.PAGE_SCOPE;
	}
	
	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}


	//*********************************************************************
	// Tag logic
	//*********************************************************************
	
	/**
	 * Evaluates expressions as necessary
	 */
    private void evaluateExpressions() throws JspException {
    	// 'value' attribute (mandatory)
    	if (value == null) {
    		value = (Date) ExpressionEvaluatorManager.evaluate(
    				"value", value_, Date.class, this, pageContext);
    	}
    }
	
    /**
     * Evaluates expression and chains to parent
     */
    public int doStartTag() throws JspException {

        // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();

        // chain to the parent implementation
        return super.doStartTag();
    }

    
    /**
     * Find time past since NOW().
     */
    public String findTimePast() {
    	long timeDiff = (new Date()).getTime() - value.getTime();
    	long hours = 0;
    	long days = 0;
    	long minutes = 0;
    	String formatted = "";
    	if (timeDiff > 0) {
    		days = timeDiff / (24 * 60 * 60 * 1000);
    		if (days > 0) {
    			return (days > 15) ? " after 15 days" : days + " days";    			
    		} // End of if days
    		hours = timeDiff / (60 * 60 * 1000);
    		if (hours > 0) {
    			return hours + " hours";
    		}    		
    		minutes = timeDiff / (60 * 1000);
    		formatted = minutes + " minutes";    	
    		
    	} else {
    		formatted = "0 minutes";
    	}    	
    	return formatted;
    }
    
	/**
	 * Formats the given date and time.
	 */
	public int doEndTag() throws JspException {

		String formatted = null;

		if (value == null) {
			if (var != null) {
				pageContext.removeAttribute(var, scope);
			}
			return EVAL_PAGE;
		}

		// no formatting locale available, use Date.toString()
		formatted = "posted " + findTimePast() + " ago";

		if (var != null) {
			pageContext.setAttribute(var, formatted, scope);	
		} else {
			try {
				pageContext.getOut().print(formatted);
			} catch (IOException ioe) {
				throw new JspTagException(ioe.toString(), ioe);
			}
		}

		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value_ = value;
	}
	
	public void setDateValue(Date value) {
		this.value = value;
	}

}
