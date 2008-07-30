//
// Author: Berlin Brown
// Remote Agents
// Date: 2/2/2008
/*
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
package org.spirit.lift.agents

import scala.Console.{println}
import java.util.Random

import net.liftweb.http._
import S._
import net.liftweb.http.S._
import net.liftweb.http.S
import scala.xml.{XML, NodeSeq, Text, Group}
import net.liftweb.util.Helpers._
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse, HttpSession}

import org.spirit.lift.agents._
import org.spirit.lift.agents.model._

import org.springframework.context.{ApplicationContext => AC}
import org.spirit.dao.impl.{BotListUserVisitLogDAOImpl => LogDAO}
import org.spirit.dao.impl.{BotListSessionRequestLogDAOImpl => SessDAO}
import org.spirit.bean.impl.{BotListUserVisitLog => Log}
import org.spirit.bean.impl.{BotListSessionRequestLog => Sess}

/**
 * Example request:
 * http://localhost:8080/botlist/lift/pipes/agents/remote_agent
 */
class RemoteAgents (val request: RequestState) {
  def httpRequest = request.request

  def remote_agent_req : XmlResponse = {
	// Cast to the user visit log bean (defined in the spring configuration)
	val log_obj = AgentUtil.getAC(httpRequest).getBean("userVisitLogDaoBean")
	val log_dao = log_obj.asInstanceOf[LogDAO]
	val sess_obj = AgentUtil.getAC(httpRequest).getBean("sessionRequestLogDaoBean")
	val sess_dao = sess_obj.asInstanceOf[SessDAO]
	val uniq_id = AgentUtil.buildRequestSession(sess_dao, httpRequest, "request_auth", "true")
	AgentUtil.auditLogPage(log_dao, httpRequest, "remote_agent")

    XmlResponse(
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" >
	<agentmsg>
 		<botid>serverbot</botid>
 		<message>Hello my name is serverbot.  Would you like some cake?</message>
 		<status>200</status>
 		<requestid>{ Text(uniq_id) }</requestid>
 		<majorvers>0</majorvers>
 		<minorvers>0</minorvers>
 	</agentmsg>
</rdf:RDF>)
  } // End of Method Request
  
  def remote_agent_send : XmlResponse = {
	try {
	  var payload = ""
	  S.param("types_payload").map { (u => payload = u) }
	  val xml_payload = XML.loadString(payload)
	  val client_agent_msg = MessageUtil.fromXML(xml_payload)
	  MessageUtil.processPayload(payload, xml_payload, httpRequest)
	} catch {
	  case e => {
		// On error return invalid XML message
		println("ERR: " + e)
		return AgentUtil.invalidXMLResponse
	  }
	} // End of try - catch
	if (S.post_?) XmlResponse { (
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" >
	  <message>Enjoy your cake</message>
</rdf:RDF>) }
    else AgentUtil.invalidXMLResponse
  } // End of Method Send

}
