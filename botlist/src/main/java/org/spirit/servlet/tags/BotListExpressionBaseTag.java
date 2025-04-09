/**
 * Berlin Brown
 * Dec 26, 2006
 */
package org.spirit.servlet.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.common.core.NullAttributeException;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public abstract class BotListExpressionBaseTag extends BodyTagSupport {

	private Object value;	
	private boolean needBody;

	public BotListExpressionBaseTag() {
		super();
		init();
	}

	/**
	 * Resets local state.
	 */
	private void init() {
		value = null;
		needBody = false;
	}

	/**
	 * Releases any resources we may have (or inherit)
	 */
	public void release() {
		super.release();
		init();
	}

	protected abstract String getActionResult(String input);
	
	/**
	 * Method called at start of tag, always returns EVAL_BODY_TAG
	 *
	 * @return EVAL_BODY_TAG
	 */
	public final int doStartTag() throws JspException {
		
	    // evaluate any expressions we were passed, once per invocation
        evaluateExpressions();
		
		super.bodyContent = null;
		try {
			if (value != null) {
				JspWriter w = pageContext.getOut();
				w.write(getActionResult(value.toString()));
				return SKIP_BODY;
			} else {
				needBody = true;
				return EVAL_BODY_BUFFERED;

			}
		} catch (IOException ex) {
			throw new JspException(ex.getMessage(), ex);
		}	
	}

	/**
	 * Evaluates expressions as necessary
	 */
	private void evaluateExpressions() throws JspException {
		try {
			value = evalNotNull("out", "value", value.toString(), Object.class, this, pageContext);
		} catch (NullAttributeException ex) {
			// explicitly allow 'null' for value
			value = null;
		}
	}

	/** 
	 * Evaluates an expression if present, but does not allow the expression
	 *  to evaluate to 'null', throwing a NullAttributeException if it
	 *  does.  The function <b>can</b> return null, however, if the
	 *  expression itself is null.
	 */
	public static Object evalNotNull(String tagName, String attributeName, String expression, Class expectedType, Tag tag, PageContext pageContext)
			throws JspException {
		if (expression != null) {
			Object r = ExpressionEvaluatorManager.evaluate(attributeName, expression, expectedType, tag, pageContext);
			if (r == null)
				throw new NullAttributeException(tagName, attributeName);
			return r;
		} else
			return null;
	}
	
	/**
	 * Method called at end of Tag
	 *
	 * @return EVAL_PAGE
	 */
	public final int doEndTag() throws JspException {
		try {
			if (!needBody) {
				return EVAL_PAGE;
			}
			if (bodyContent != null && bodyContent.getString() != null) {
				JspWriter w = pageContext.getOut();
				w.write(getActionResult(value.toString()));
			}
			return EVAL_PAGE;
		} catch (IOException ex) {
			throw new JspException(ex.getMessage(), ex);
		}
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
