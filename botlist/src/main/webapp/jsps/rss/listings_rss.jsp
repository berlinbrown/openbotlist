<?xml version="1.0" ?>
<%@ page contentType="text/xml" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<rss version="2.0" xmlns:dc="http://purl.org/dc/elements/1.1/">
<channel>
        <title>Botlist: (listings) new stuff and ads online</title>
        <link>http://www.botspiritcompany.com/botlist/</link>
        <description>New links and interesting ad listings</description>	
        <c:forEach items="${command.listings}"  var="listing" varStatus="status">
        <item>
            <title><c:out value="${listing.title}" /></title>
            <link>http://www.botspiritcompany.com/botlist?id=3</link>      
           	<pubDate><fmt:formatDate pattern="EE, dd MMM yyyy hh:mm:00" value="${listing.createdOn.time}" /> GMT</pubDate>
          	<description>
          		&lt;strong&gt;<c:out value="${listing.title}" />&lt;/strong&gt;
          		&lt;br /&gt;
          		<c:out value="${listing.message}" />
          	</description>
        </item>        	
        </c:forEach>
</channel>
</rss>
