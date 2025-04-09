<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Interesting things online</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/botverse/rss/botverse_rss.html" />">

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
	color: <c:out value='#${command.userInfo.linkColor};' />
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
<h1 class="bot_titlelogo">Botverse - Info Articles | Information Resource</h1>
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>

<div style="margin: 10px;">
	
	<%-- Add Welcome user message and login content (above content border line) --%>
	<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
	<%-- End of Welcome Header --%>


<div style="border: 1px solid #DDD; padding: 10px; margin-right: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<%-- Build the table for entering the new department information --%>
	<%@include file="/WEB-INF/jsps/general/botverse_navigation.jsp" %>	
	<%-- End of Table for Botverse Navigation Links --%>	
	<div style="color: #888;">
		Group Links / 
		<a href="<c:url value="/spring/botverse/group/grouplinks_submit.html?groupgenid=${command.linkGroup.generatedId}" />" >Submit</a>
		<p>
		 NSFW - stands for "Not Safe For Work"; this area is used
		 for various links that might fall outside of typical political article or picture of a cute kitten.
		 Please be advised that submissions that fall outside of the editor's criteria of NSFW will be deleted.
		</p>
		
	</div>		
	<table class="linklist_data" cellspacing="0" cellpadding="0">
		<c:forEach items="${command.coreLinks}"  var="listing" varStatus="status">
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
						<div style="margin-left: 10px;" class="linklist_comments_txt">
							<c:set var="curStatusCount" value="${status.count}" />
							<c:set var="curPageOffset" value="${command.pagingform.pageOffset}" />
							<c:out value="${curStatusCount + curPageOffset}" />)
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
							 <c:set var="keywordValue" value="${listing.keywords}" />
							 tags / <c:out value="${botlistutil:tagViewKeywords(listing.keywords, 'linklist_keywords_lnk', '/botlist/spring/botverse/botverse.html?filterset=defkeytag&defkeytagid=', ' ')}" escapeXml="false" />
							</span>
						</td>
					</tr>
					</table>
					<!-- End of inner table (loop) -->				
				</td>
				</tr>
		</c:forEach>
	</table>
	
	<%-- ==== Section for paging ==== --%>
	<div id="linklist_paging"> 
		(view more) /
		<a href="<c:url value="/spring/botverse/group/grouplinks.html?groupgenid=${command.linkGroup.generatedId}&offsetpage=0" />">first</a>
		<c:forEach begin="${command.pagingform.begin}" 
					end="${command.pagingform.end}" var="page" varStatus="status">
			&nbsp;/ [<span class="linklist_paging_pg">
				<a href="<c:url value="/spring/botverse/group/grouplinks.html?groupgenid=${command.linkGroup.generatedId}&offsetpage=${page}" />">pg <c:out value="${page+1}" /></a> 
			</span>]
		</c:forEach>
	</div>
	<%-- ==== End of Paging ==== --%>
	
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
 
</body>
</html>
