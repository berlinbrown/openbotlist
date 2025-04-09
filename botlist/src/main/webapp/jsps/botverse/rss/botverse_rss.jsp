<?xml version="1.0" ?>
<%@ page contentType="text/xml" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<rss version="2.0" xmlns:dc="http://purl.org/dc/elements/1.1/">
<channel>
        <title>Botlist: (botverse) new stuff and ads online</title>
        <link>http://www.botspiritcompany.com/botlist/</link>
        <description>New links and interesting ad listings</description>	
        <c:forEach items="${command.listings}"  var="listing" varStatus="status">
        <item>
       		<c:set var="urlValue" value="${listing.mainUrl}" />			
            <title><c:out value="${listing.urlTitle}" /></title>
            <link><c:out value='${botlistutil:encodeUrl(urlValue)}' /></link>      
           	<pubDate><fmt:formatDate pattern="EE, dd MMM yyyy hh:mm:00" value="${listing.createdOn.time}" /> GMT</pubDate>
          	<description>
          		<c:out value="${listing.urlTitle}" />
          		&lt;br /&gt;
          		&lt;a href="<c:out value="${listing.mainUrl}" escapeXml="true" />"&gt;[link]&lt;/a&gt;
          	</description>
        </item>        	
        </c:forEach>
</channel>
</rss>
