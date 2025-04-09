/**
 * Berlin Brown
 * Copyright (c) 2006 - 2007, Newspiritcompany.com
 *
 * Jan 12, 2007
 */
package org.spirit.form.base;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public interface BotListBaseCalcVerify {
	/**
	 * @return the prevSolution
	 */
	public Long getPrevSolution();

	/**
	 * @param prevSolution the prevSolution to set
	 */
	public void setPrevSolution(Long prevSolution);

	/**
	 * @return the firstInput
	 */
	public Long getFirstInput();

	/**
	 * @param firstInput the firstInput to set
	 */
	public void setFirstInput(Long firstInput);

	/**
	 * @return the secondInput
	 */
	public Long getSecondInput();

	/**
	 * @param secondInput the secondInput to set
	 */
	public void setSecondInput(Long secondInput);
	
	/**
	 * @return the solution
	 */
	public Long getSolution();
	

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(Long solution);

	/**
	 * @return the userSolution
	 */
	public Long getUserSolution();

	/**
	 * @param userSolution the userSolution to set
	 */
	public void setUserSolution(Long userSolution);
}
