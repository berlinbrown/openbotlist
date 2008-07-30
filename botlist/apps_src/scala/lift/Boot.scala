/**
 * Boot.scala
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
 *	    * Neither the name of the Newspiritcompany.com (Berlin Brown) nor 
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
package bootstrap.liftweb

import net.liftweb.util.{Helpers, Can, Full, Empty, Failure, Log}
import net.liftweb.http._
import Helpers._
import javax.servlet.http.{HttpServlet, HttpServletRequest , HttpServletResponse, HttpSession}

import org.spirit.lift.agents._
 
/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {
	LiftRules.addToPackages("org.spirit.lift.agents")
	val dispatcher: LiftRules.DispatchPf = {         
	  // In our pseudo REST architecture, 'types' is associated with
	  // the payload dumps and their types.  
	  // In layman terms, a type is a article link and attributes.
	  case RequestMatcher(r, ParsePath("lift" :: "pipes" :: "types" :: c :: _, _,_),_, _) => 
		invokeAgents(r, c)
    }
	LiftRules.addDispatchBefore(dispatcher)
  }
  private def invokeAgents(request: RequestState, methodName: String)(req: RequestState): Can[ResponseIt] =
	createInvoker(methodName, new RemoteAgents(request)).flatMap(_() match {
	  case Full(ret: ResponseIt) => Full(ret)
      case _ => Empty
	})

} // End of Class 

