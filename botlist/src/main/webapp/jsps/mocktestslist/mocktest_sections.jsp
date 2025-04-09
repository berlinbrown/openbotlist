<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <body>
    <c:forEach items="${command.sections}"
			var="section" varStatus="status">
		<a href="<c:url value="/spring/mocktestslist/mocktest_listings.html?viewid=${section.generatedId}" />" >
			<c:out value="${section.sectionName}" /><br>
		</a>			
	</c:forEach>
 </body>
</html>