<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="dt" uri="http://jakarta.apache.org/taglibs/datetime-1.0" %>
<%@ taglib prefix="req" uri="http://jakarta.apache.org/taglibs/request-1.0" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<table class="linklist_data">
	<c:forEach items="${command.listings}" var="listing" varStatus="status">
		<tr>
			<td><c:out value="${status.count}" />)</td>
			<td>
			<a href="<c:url value="/spring/viewlisting.html?viewid${listing.id}" />"> 
				<c:out value="${listing.title}" />
			</a>
			</td>
			<td><fmt:formatDate pattern="EE dd, MMM yyyy hh:mm" value="${listing.createdOn.time}" /></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
