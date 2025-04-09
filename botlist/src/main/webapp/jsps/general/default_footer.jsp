<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="/spring" %>
	<%-- Source listing, remove late --%>
	<div style="margin-left: 10px;">
		<table width="100%">
			<tr>
			<td>
				<%-- General Links (TODO: find proper location) --%>
				<a href="<c:url value="/spring/stats.html" />">stats</a>
				| <a href="<c:url value="/spring/search/global.html" />">global search</a>
				| <a href="<c:url value="/spring/pipes/pipes.html" />">for developers</a>
				| <a href="<c:url value="/spring/help/source.html" />">botlist source</a>
				| <a href="<c:url value="http://code.google.com/p/openbotlist/" />">at googlecode</a>			
				| <a href="<c:url value="/company/botlist_faq.htm" />">faq</a>
				| <a href="http://berlinbrowndev.blogspot.com/">blog</a>
			</td>
			<td align="right">
				<jsp:useBean id="bot_now" class="java.util.Date" />
				<fmt:formatDate pattern="EE dd, MMM yyyy hh:mm a" value="${bot_now}" />
			</td>
			</tr>
		</table>
	</div>
	<!-- (Footer) Footer Section -->
	<div id="bot_footer_group">
			<div style="text-align: center">
				<a href="<c:url value="/company/terms_of_use.htm" />" class="footer">Terms of Use</a> |
				<a href="<c:url value="/spring/help/about.html" />" class="footer">About</a> |
				<a href="<c:url value="/spring/help/contact.html" />" class="footer">Contact</a> |
				<a href="<c:url value="/spring/help/help.html" />" class="footer">Help</a>
				<br />
				Copyright &copy; Botnode.com 2006. All rights reserved.
				<br />				
				<!-- Print the Bot List Version -->
				<div style="font-size: 8pt;">
				OpenBotlist <spring:message code="versMajor" />.<spring:message code="versMinor" />.b<spring:message code="versBuild" />
				r<spring:message code="versRev" /> - <spring:message code="versMilestone" />
				</div>
				<!-- End of Version -->
			</div>
	</div>
	<!-- (Footer) End of Footer Section -->