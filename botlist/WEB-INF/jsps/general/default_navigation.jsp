<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="/spring" %>			
			<%-- Navigation Header --%>
			<div id="navigation">
			<ul style="margin-top: 0px;">
				<li><a href="#"></a></li>
				<li><a href="<c:url value="/" />">Home</a></li>				
				<li><a href="<c:url value="/spring/botverse/botverse.html?filterset=mostrecent" />">Botverse (popular links)</a></li>
				<li><a href="<c:url value="/spring/semantic/foaf/foaf.html" />">FOAF (SW)</a></li>							
				<li><a href="<c:url value="/spring/search/search.html?querymode=enabled&amp;query=" />">Search</a></li>
				<li><a href="<c:url value="/spring/forums/forums.html" />">Forums</a></li>
				<li><a href="<c:url value="/spring/forums/comments/topics.html" />">Comments</a></li>										
				<li><a href="<c:url value="/spring/help/help.html" />">Help</a></li>				
			</ul>
			</div>
			<%-- End of Navigation Header --%>
