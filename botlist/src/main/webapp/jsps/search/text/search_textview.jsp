<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Search Text View</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/botverse/rss/botverse_rss.html" />">
<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />
</head>
<body>
<a href="<c:url value="/" />" >Home</a>
<div style="margin-top: 40px; margin-left: 60px; border-top: 1px solid #ccc;">	

		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
			<c:set var="curStatusCount" value="${status.count}" />
			<c:set var="curPageOffset" value="${command.pagingform.pageOffset}" />
			<c:out value="${curStatusCount + curPageOffset}" />)
			<a href="<c:url value="${listing.mainUrl}" />" ><c:out value="${listing.mainUrl}" /></a>	
			&nbsp;/ <c:out value="${listing.urlTitle}" />												
			&nbsp;/ tags: <c:out value="${listing.keywords}" />						
			&nbsp;/ <fmt:formatDate pattern="EE MMM, dd hh:mm" value="${listing.createdOn.time}" />
			<br>
		</c:forEach>	
</div>
</body>
</html>
