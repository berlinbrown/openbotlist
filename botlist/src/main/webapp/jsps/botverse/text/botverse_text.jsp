<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Text View</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting, Text View">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising, text view">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />
</head>
<body>

<div style="margin-top: 40px; margin-left: 60px; border-top: 1px solid #ccc;">
	<a href="<c:url value="/spring/botverse/botverse.html" />" >Botverse.Home</a><br />
	Text View - Use the text view in order to view, highlight, or copy the most recent links<br />
		<c:forEach items="${command.listings}"
				var="listing" varStatus="status">
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
