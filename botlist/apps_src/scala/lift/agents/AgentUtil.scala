//
// Author: Berlin Brown
// Remote Agents
// Date: 2/2/2008

package org.spirit.lift.agents

import java.util.Random
import org.springframework.context.{ApplicationContext => AC}
import org.spirit.dao.impl.{BotListUserVisitLogDAOImpl => LogDAO}
import org.spirit.dao.impl.{BotListSessionRequestLogDAOImpl => SessDAO}
import org.spirit.bean.impl.{BotListUserVisitLog => Log}
import org.spirit.bean.impl.{BotListSessionRequestLog => Sess}

import org.spirit.dao.impl.{BotListForumGroupDAOImpl => ForumDAO}
import org.spirit.bean.impl.{BotListForumGroup => ForumGroup}
import org.spirit.bean.impl.{BotListUserComments => ChatComment}

import net.liftweb.http._
import net.liftweb.http.S._
import net.liftweb.http.S
import scala.xml.{NodeSeq, Text, Group}
import net.liftweb.util.Helpers._
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse, HttpSession}

import scala.xml._
import org.spirit.lift.agents.model.{AgentMessage}

object AgentUtil {
  /**
   * Create a unique string id based on; random number and client ip.
   */
  def uniqueMsgId (clientip: String) : String = {
	val t = (System.currentTimeMillis) + 1
	val r = new Random()
	val rand_long = r.nextLong
	hexEncode(md5( (clientip + rand_long).getBytes ))
  }  
  def auditLogPage (dao: LogDAO, request: HttpServletRequest, curPage: String) = {
	val link = new Log()
	link.setRequestUri(request.getRequestURI)
	link.setRequestPage(curPage)
	link.setHost(request.getHeader("host"))
	link.setReferer(request.getHeader("referer"))
	link.setRemoteHost(request.getRemoteAddr())
	link.setUserAgent(request.getHeader("user-agent"))
	dao.createVisitLog(link)
  }
  def createBotForumMsg(httpRequest: HttpServletRequest, user: String, msg: String, title: String) {
	// TODO: do not hard code the forum id.
	val chatForumGroupId = 3
	val bean_obj = AgentUtil.getAC(httpRequest).getBean("forumGroupDaoBean")
	val forum_dao = bean_obj.asInstanceOf[ForumDAO]

	// Populate the comment data
	val comment = new ChatComment   
	comment.setFullName(user)
	comment.setSubject(title)
	comment.setMessage(msg)
	
	// Hibernate oriented approach for creating a comment topic
    val sessionFactory = forum_dao.getSessionFactory
    val hbm_session = sessionFactory.openSession
    val tx = hbm_session.beginTransaction

    val forum_obj = hbm_session.load("org.spirit.bean.impl.BotListForumGroup", 
									 new java.lang.Long(chatForumGroupId), null)
	val forum = forum_obj.asInstanceOf[ForumGroup]
    comment.setForumId(new java.lang.Long(chatForumGroupId))
    val topics = forum.getTopics()
	// TODO: with release 2.7, this code does not work.
	//topics.add(comment)
    tx.commit
    hbm_session.close
  }
  def buildRequestSession (dao: SessDAO, request: HttpServletRequest, key:String, value:String):String = {
	val sess = new Sess()
	val remote_host = request.getRemoteAddr
	val u_id = uniqueMsgId(remote_host)
	sess.setRemoteHost(remote_host)
	sess.setMsgKey(key)
	sess.setMsgValue(value)
	sess.setRequestId(u_id)
	dao.createSessionLog(sess)
	u_id
  }
  def getAC(request: HttpServletRequest) = {
	val sess = request.getSession
	val sc = sess.getServletContext
	
	// Cast to the application context
	val acobj = sc.getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.botlistings")
	acobj.asInstanceOf[AC]
  }   
  def invalidXMLResponse : XmlResponse = {
	 XmlResponse(
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
	   <agentmsg>
		 <botid>serverbot</botid>
 		 <message>Oops. Something went wrong.</message>
 		 <status>500</status>
	   </agentmsg>
</rdf:RDF>) }
  
  def validPostXMLResponse : XmlResponse = {
	 XmlResponse(
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
	   <agentmsg>
		 <botid>serverbot</botid>
 		 <message>Thanks, I hope you enjoy your cake.</message>
 		 <status>200</status>
	   </agentmsg>
</rdf:RDF>) }

} // End of Object
