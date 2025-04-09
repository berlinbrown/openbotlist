<%@ page contentType="text/plain" 
%><%@include file="/WEB-INF/jsps/general/default_includes.jsp" 
%><c:forEach items="${command.listings}" var="listing" varStatus="status">
<c:out value="${listing.mainUrl}" />	<c:out value="${listing.urlTitle}" />	<fmt:formatDate pattern="EE.MM.dd.yyyy.hh:mm" value="${listing.createdOn.time}" /></c:forEach>
