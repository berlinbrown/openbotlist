<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Light View</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting, Light View">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising, light view">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />
</head>
<body>
<div style="margin-top: 40px; margin-left: 60px; border-top: 1px solid #ccc;">
	<a href="<c:url value="/spring/botverse/botverse.html" />" >Botverse.Home</a><br />
	Light View - Use the light view in order to view, highlight, or copy the most recent links
	<table class="linklist_data">
		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
				<tr>					
					<td colspan="3">
							<span class="linklist_comments_date">_</span>
							<a href="<c:url value="${listing.mainUrl}" />" >
									<c:out value="${listing.urlTitle}" />
							</a>							
					</td>
				</tr>
				<tr>					
					<td colspan="3">							
							<a href="<c:url value="${listing.mainUrl}" />" >
									<c:out value="${listing.mainUrl}" />
							</a>							
					</td>
				</tr>
				<tr>					
					<td colspan="3">
					<div style="font-size: 10px;">
						keywords: <c:out value="${listing.keywords}" />
					</div>
					</td>
				</tr>
				<tr>
				<td>
					<!-- Inner table for data/status information -->
					<table>
					<tr>
					<td>
						<div style="margin-left: 30px;" class="linklist_comments_txt">
							<c:set var="curStatusCount" value="${status.count}" />
							<c:set var="curPageOffset" value="${command.pagingform.pageOffset}" />
							<c:out value="${curStatusCount + curPageOffset}" />)
						</div>
					</td>
					<td>
						<div class="linklist_comments_txt">
						 <span class="linklist_comments_date">							
							<botlistutil:timePast dateValue="${listing.createdOn.time}" />
							on <fmt:formatDate pattern="EE MMM, dd hh:mm" value="${listing.createdOn.time}" />
							by <c:out value="${listing.fullName}" />					
						 </span>
						</div>
					</td>					
					</tr>
					</table>
					<!-- End of inner table -->				
				</td>
				</tr>
		</c:forEach>
	</table>
</div>
	
</body>
</html>
