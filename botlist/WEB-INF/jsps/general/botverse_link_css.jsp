<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
/** =============================================== */
/** == Link List Data, Only for the Link Section == */
/** == Bold and Dark Blue / Purple == */
/** =============================================== */
a.linklist_objlinks:link {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	background-color: transparent;
	text-decoration: none;
	/** New entity link foreground color */	
	<c:choose>
   	 <c:when test="${command.userInfo != null}" >														  
	  color: <c:out value='#${command.userInfo.linkColor};' />
	 </c:when>
	 <c:otherwise>
		color: #2327ae;
     </c:otherwise> 
	</c:choose>
}

/** Leave the visited link as darker than others */
a.linklist_objlinks:visited {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #460C7B;
	text-decoration: none;
	background-color: transparent;
}

a.linklist_objlinks:hover {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #447;
	text-decoration: underline;
	background-color: transparent;
}
a.linklist_objlinks:active {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 11pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #447;
	text-decoration: underline;
	background-color: transparent;
}

/** =============================================== */
/** == End of Link List table == */
/** =============================================== */