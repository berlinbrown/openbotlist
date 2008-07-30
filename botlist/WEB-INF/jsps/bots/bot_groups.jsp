<%@ page contentType="text/plain" 
%><%@include file="/WEB-INF/jsps/general/default_includes.jsp" 
%><c:forEach items="${command.terms}" var="listing" varStatus="status">
<c:out value="${listing.categoryTerm}" /></c:forEach>
