<%@ page contentType="text/xml" %><?xml version="1.0" encoding="UTF-8"?>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<bot_resp>
 <last_oper><c:out value="${command['ajx_last_oper']}" /></last_oper>
 <status><c:out value="${command['ajx_status']}" /></status>
 <code><c:out value="${command['ajx_code']}" /></code>
 <message><c:out value="${command['ajx_message']}" /></message>
</bot_resp>