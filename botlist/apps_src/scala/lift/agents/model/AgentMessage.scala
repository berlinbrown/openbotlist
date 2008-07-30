//
// Berlin Brown
// 2/2/2008
// Model - botlist 
// Would you like some cake?
//
/*
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

package org.spirit.lift.agents.model

import scala.Console.{println}
import scala.xml._

import org.spirit.lift.agents.util.{GlobalUtil}
import org.spirit.lift.agents.{AgentUtil}
import org.spirit.bean.impl.BotListEntityLinks
import org.spirit.dao.impl.{BotListEntityLinksDAOImpl => LinkDAO}
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse, HttpSession}

abstract class AgentMessage {
  val message: String
  val status: Int
  val agentName: String
  val messageReqId: String
  
  override def toString = message + "@" +  messageReqId

  def toXML = 
	<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
	   <agentmsg>
		 <botid>{agentName}</botid>
 		 <message>{message}</message>
 		 <status>{status}</status>
	   </agentmsg>
	</rdf:RDF>  
}
object MessageUtil {
  def fromXML(node: Node): AgentMessage  =
	new AgentMessage {
	  val message = (node \\ "message").text
	  val status = Integer.parseInt((node \\ "status").text)
	  val agentName = (node \\ "botid").text
	  val messageReqId = "none"
	}
  /**
   * Iterate through all of the nodes and commit
   * the data to the database.
   */
  def processPayload(payload_content: String, rootNode: Node, httpRequest: HttpServletRequest) = {
	val stats_payload_sz = payload_content.length
	val payload = (rootNode \\ "typespayload")
    val agent_msg = fromXML(rootNode)
	var chat_forum_msg = agent_msg.message
	var chat_forum_title = agent_msg.message
	var success_count = 0
	payload(0) match {
	  case <typespayload>{elems @ _*}</typespayload> =>   
		for (curtype @ <type>{_*}</type> <- elems) {
		  try {
			// Unmarshall the payload data
			val link_type = new BotListEntityLinks
			val model_title = GlobalUtil.formatTextAscii( (curtype \ "title").text )
			val model_url =  GlobalUtil.formatTextAscii( (curtype \ "url").text )
			val model_keywords = GlobalUtil.formatKeywords( (curtype \ "keywords").text )
			val model_descr =  GlobalUtil.formatDescription( (curtype \ "descr").text )
			link_type.setUrlTitle(model_title)
			link_type.setMainUrl(model_url)
			link_type.setKeywords(model_keywords)
			link_type.setUrlDescription(model_descr)
			link_type.setFullName(agent_msg.agentName)
			link_type.setRating(new java.lang.Long(0))
			
			val bean_obj = AgentUtil.getAC(httpRequest).getBean("entityLinksDaoBean")
			val link_dao = bean_obj.asInstanceOf[LinkDAO]
			link_dao.createLink(link_type)
			 success_count = success_count + 1
		  } catch {
			case e => {
			  println("ERR: " + e)
			  chat_forum_msg = "There was an issue with your payload<br />Msg: " + agent_msg.message
			  //chat_forum_title = "There was an issue with your payload"
			}
		  } // End of try - catch
		} // End of for
	}
	// Also create a message telling us that the bot message
	// was received.
	val additional_chat_msg = GlobalUtil.msgFormatStr(
"""<br />Server Agent: I had to do a lot of work, the payload was large with
 a size of {0} characters. I was able to handle {1} of those widgets.""",
 stats_payload_sz, success_count)
	AgentUtil.createBotForumMsg(httpRequest, agent_msg.agentName,
								(chat_forum_msg + additional_chat_msg), 
								(chat_forum_title take 40) + "...")
  } // End of Method

} // End of Object
