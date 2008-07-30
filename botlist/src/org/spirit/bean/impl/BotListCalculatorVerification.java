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
package org.spirit.bean.impl;

import java.io.Serializable;

/**
 * This is class is used by botverse.
 * @author Berlin Brown
 *
 */
public class BotListCalculatorVerification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5767760461182300259L;
	private Long firstInput;
	private Long secondInput;
	private Long solution;
	private Long userSolution;
	
	/**
	 * @return the userSolution
	 */
	public Long getUserSolution() {
		return userSolution;
	}
	/**
	 * @param userSolution the userSolution to set
	 */
	public void setUserSolution(Long userSolution) {
		this.userSolution = userSolution;
	}
	/**
	 * @return the firstInput
	 */
	public Long getFirstInput() {
		return firstInput;
	}
	/**
	 * @param firstInput the firstInput to set
	 */
	public void setFirstInput(Long firstInput) {
		this.firstInput = firstInput;
	}
	/**
	 * @return the secondInput
	 */
	public Long getSecondInput() {
		return secondInput;
	}
	/**
	 * @param secondInput the secondInput to set
	 */
	public void setSecondInput(Long secondInput) {
		this.secondInput = secondInput;
	}
	/**
	 * @return the solution
	 */
	public Long getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(Long solution) {
		this.solution = solution;
	}

}
