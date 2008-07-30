<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Results</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Search Results">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

<style type="text/css">
/** =============================================== */
/** == Link List Data, Only for the Link Section == */
/** == Bold and Dark Blue / Purple == */
/** =============================================== */
a.linklist_objlinks:link {
	font-family: "arial", verdana, helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	background-color: transparent;
	color: #3838E5;
}

/** Leave the visited link as darker than others */
a.linklist_objlinks:visited {
	font-family: "arial", verdana, helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #460C7B;
	background-color: transparent;
}

a.linklist_objlinks:hover {
	font-family: "arial", verdana, helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #3838E5;
	background-color: transparent;
}
a.linklist_objlinks:active {
	font-family: "arial", verdana, helvetica, sans-serif;
	font-size: 10pt;
	font-weight: bold;
	margin-bottom: 0px;
	margin-top: 0px;
	color: #3838E5;
	background-color: transparent;
}

/** =============================================== */
/** == End of Link List table == */
/** =============================================== */
</style>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botlist | Search Results</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					

<div style="margin: 20px;">

<div style="border: 1px solid #DDD; padding: 10px; margin-right: 100px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<!-- Build the table for entering the new department information -->
	<a href="<c:url value="/" />" >Home</a>
	| <a href="<c:url value="/spring/search/text/search_textview.html" />" >Text View</a>
	<p>	
	
	<table class="linklist_data">
		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
				<tr>					
					<td colspan="3">							
							<a class="linklist_objlinks" href="<c:url value="${listing.mainUrl}" />" >
									<c:out value="${listing.urlTitle}" />
							</a>
							<%-- Add custom tag here, find hostname --%>
							<span class="linklist_comments_host">
							&nbsp;(<botlistutil:hostname value="${listing.mainUrl}" />)
							</span>							
					</td>
				</tr>
				<tr>
				<td>
					<!-- Inner table for data/status information -->
					<table>
					<tr>
					<td>
						<div style="margin-left: 30px;" class="linklist_comments_txt">
							<c:out value="${status.count}" />)
						</div>
					</td>
					<td>
						<div class="linklist_comments_txt">
						 <span class="linklist_comments_date">							
							<botlistutil:timePast dateValue="${listing.createdOn.time}" />
							on <fmt:formatDate pattern="EE MMM, dd" value="${listing.createdOn.time}" />
						 </span>
						</div>
					</td>										
					</tr>
					
					<tr>
						<td colspan="3">
							<!-- == Keywords == -->
							<span class="linklist_keywords">
							 relevancy / <c:out value="${listing.searchScore}" />
							 <c:set var="keywordValue" value="${listing.keywords}" />
							 tags / <c:out value="${botlistutil:tagViewKeywords(listing.keywords, 'linklist_keywords_lnk', '/botlist/spring/botverse/botverse.html?filterset=defkeytag&defkeytagid=', ' ')}" escapeXml="false" />
							</span>
						</td>
					</tr>
					
					</table>
					<!-- End of inner table -->				
				</td>
				</tr>
		</c:forEach>
	</table>
	
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

</body>
</html>
