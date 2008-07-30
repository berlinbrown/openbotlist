<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${command.searchTerm}" /> (Botverse - Search Results)</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Search Results">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/botverse/rss/botverse_rss.html" />">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botverse - Interesting things online</h1>
			
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
			
	<%-- Table Section including banner and search  --%>	
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
	<td valign="top">
		<%-- Banner Section --%>
		<c:choose>
			<c:when test="${command.headline != null}" >
				<div style="color: #444; font-size: 14pt; background-color: #e7f0f1; padding: 6px;">
				  <c:out value="${command.headline}" />
				</div>
			</c:when>
		</c:choose>
		<%-- End of Banner Section --%>
	</td>
	<td align="right" valign="top">
		<!-- Begin Search Form -->
		<div align="right">
			<form method="get" action="<c:url value="/spring/search/search.html" />" name="newsearch">
			<table>
				<tr>
					<td> 
						<input name="query" size="26" />
					</td>
					<td>
						<input type="submit" value=" Search " />
					</td>
				</tr>	
				<tr>
					<%-- Row with 'addtopic' button --%>
					<td>
					  <span style="background-color: #F2F2F2; padding-left: 4px; padding-right: 4px;">
						<c:choose>
   	 						<c:when test="${command.userInfo != null}" >
								<a href="/botlist/spring/search/addtopics.html?query=${command.query}" class="linklist_sample">add+</a> hot topic
	 						</c:when>
	 						<c:otherwise>
								<a href="/botlist/spring/profile/login.html" class="linklist_sample">login</a> in to add hot topic
     						</c:otherwise> 
						</c:choose>
					   </span>
					</td>
					<%-- End of row, add topic --%>
				</tr>		
			</table>
			<input type="hidden" name="querymode" value="enabled" />
			</form>
		</div>
		<!-- End of Form -->		
	</td>
	</tr>
	</table>
	<%-- End of Table search/banner --%>
			
	<form:form method="post">
	<form:errors path="*" cssClass="general_field_error" />	
	<p>		
	<table class="linklist_data" cellspacing="2" cellpadding="0">
		<c:forEach items="${command.listings}"  var="listing" varStatus="status">
				<tr>					
					<td colspan="3">
							<span class="rating_area">
							 <c:out value="${listing.rating}" /> pts
							</span>
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
					<td>
					  <span class="linklist_comments">
					  <a class="linklist_comments" href="<c:url value="/spring/botverse/linkviewcomments.html?viewid=${listing.id}" />">details / comments (<c:out value="${listing.commentsCount}" />)</a> 					  
					  </span>						
					  | <span class="linklist_comments"><a class="linklist_comments" href="<c:url value="/spring/botverse/linkaddcomment.html?viewid=${listing.id}" />">add comment</a></span>						
					</td>
					</tr>
					<tr>
						<td colspan="3">
							<!-- == Keywords == -->														
							<span class="linklist_keywords">								
											
							 <c:set var="keywordValue" value="${listing.keywords}" />
							 <b>relevancy</b>=<c:out value="${listing.searchScore}" />
							</span>
							<%-- Print the UserName --%>
							<span style="margin-left: 0px; font-size: 10px">
								<span class="linklist_comments_date"><strong>by
									<c:out value="${listing.fullName}" /></strong>
								</span>
							</span>
						</td>
					</tr>
					</table>
					<!-- End of inner table (loop) -->				
				</td>
				</tr>
		</c:forEach>
	</table>
	
	<form:hidden path="operation" />
	<form:hidden path="ratingId" />
	
	</form:form>
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
  
  
<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
</script>
<script type="text/javascript">
_uacct = "UA-286501-2";
urchinTracker();
</script>  
  
</body>
</html>
