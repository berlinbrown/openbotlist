/**
 * Berlin Brown
 * Dec 27, 2006
 */
package org.spirit.form;

import org.spirit.form.base.BotListBaseForm;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListGenericPagingForm extends BotListBaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6242789192971771987L;

	private int pageOffset = 0;
	private int curPage = 0;
	private int begin = 0;
	private int end = 0;
	
	/**
	 * @return the curPage
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * @param curPage the curPage to set
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * @return the pageOffset
	 */
	public int getPageOffset() {
		return pageOffset;
	}

	/**
	 * @param pageOffset the pageOffset to set
	 */
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public BotListGenericPagingForm() {
		super();
	}

	/**
	 * @return the begin
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * @param begin the begin to set
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	

	
}
