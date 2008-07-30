<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@ taglib prefix="dt" uri="http://jakarta.apache.org/taglibs/datetime-1.0" %>
<%@ taglib prefix="req" uri="http://jakarta.apache.org/taglibs/request-1.0" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title><c:out value="${command.link.urlTitle}" /> - (Botlist)</title>
 
  	<META NAME="DESCRIPTION" CONTENT="Botverse - View Comments">
 	<META NAME="keywords" CONTENT="comments, postings, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" >
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" > 
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" >
  	<link href="<c:url value="/company/stylesheets/botlist_general2.css" />" media="screen" rel="Stylesheet" type="text/css" > 
  	
<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

</head>
<body>
		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" >
 			</div>
			<h1 class="bot_titlelogo">
				Botverse - Details and Comments
			</h1>							
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>											
			<br>						
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
				<div style="border: 1px solid #DDD; padding: 10px;">
					<a href="<c:url value="/spring/botverse/botverse.html" />" class="linklist_botnav">Botverse.Home</a>
					| <a href="<c:url value="/spring/botverse/linkaddcomment.html?viewid=${command.link.id}" />" class="linklist_botnav">Add Comment</a>
					<p>												
					<div>
						<span class="rating_area">
							 <c:out value="${command.link.rating}" /> pts
						</span>
						<%-- Link Data --%>
						<a class="linklist_objlinks" href="<c:url value="${command.link.mainUrl}" />" >
							<c:out value="${command.link.urlTitle}" />
						</a>
						<%-- Add custom tag here, find hostname --%>
						<span class="linklist_comments_host">
							&nbsp;(<span class="home_source_label">source:</span> <botlistutil:hostname value="${command.link.mainUrl}" />)
						</span>
					</div>
					<div class="linklist_comments_txt">
						 <span class="linklist_comments_date">							
							<botlistutil:timePast dateValue="${command.link.createdOn.time}" />
							on <fmt:formatDate pattern="EE MMM, dd" value="${command.link.createdOn.time}" />
						 </span>
					</div>
					<div>
							<%-- == Keywords == --%>
							<span class="linklist_keywords">							 
							 <span style="background-color: white">
							  link keywords / <c:out value="${botlistutil:tagViewKeywords(command.link.keywords, 'linklist_keywords_lnk', '/botlist/spring/search/search.html?querymode=enabled&query=', ' ')}" escapeXml="false" />
							 </span>
							</span>
							
							<c:if test="${not empty command.link.urlDescription}">															
									<%-- ======================== --%>
									<%-- == Updated: 2/2/2008; Add URL description text --%>
									<%-- ======================== --%>
									<div class="single_link_descr">
										<span style="background-color: #ffcc66"><b>about:</b></span> <c:out value="${command.link.urlDescription}" />
									</div>								
							</c:if>
							
							<%-- *************************** --%>
							<%-- ==== Section with misc web analytical data (views, objectid) ==== --%>
							<%-- *************************** --%>
							<div style="margin-left: 20px; font-size: 8pt; color: #000; padding: 0px 0px 0px 0px;">
								 <div style="width: 60%;">
									 <span style="background-color: #000; color: #FFF; padding: 2px 8px 8px 2px; margin-right: 2px;">
								 	  <b>page metadata (dna):</b>
								 	 </span>
								 	 <div style="margin-top: 4px; border-top: 1px solid #000;">
								 	 linktype:(<c:out value="${command.link.linkType}" />)
									 views:<c:out value="${command.link.views}" />
									 links:<c:out value="${command.link.linksCt}" />
									 images:<c:out value="${command.link.imageCt}" />
									 para:<c:out value="${command.link.paraTagCt}" />
			                         chars:<c:out value="${command.link.documentSize}" />
			                         proctime:<c:out value="${command.link.requestTime}" />
			                         objid:<c:out value="${command.link.generatedUniqueId}" />
			                         </div>
		                         </div>
							</div>
					</div>					
					<p />
					<h2 class="bot_splashinfo">
						Comments
					</h2>
					<table class="linklist_data" width="70%">
						<%-- *************************** --%>
						<%-- Begin Section with Comments --%>
						<%-- *************************** --%>
						<c:choose>
							<c:when test="${fn:length(command.listings) > 0}" >
								<%-- ==== List with Comments ==== --%>						
								<c:forEach items="${command.listings}" var="childComments" varStatus="status"> 
									<tr>							
										<td style="border-top: 1px solid #DDD;">
											<div class="single_view_forum">					
												<c:out value="${childComments.message}" escapeXml="false" />
											</div>
										</td>
									</tr>
									<tr>					
										<td align="right">
										  <div style="margin-left: 20px;">
											 <strong>by <c:out value="${childComments.fullName}" /></strong><br />
												on <fmt:formatDate pattern="EE MMM dd, yyyy hh:mm a" value="${childComments.createdOn.time}" />
										 </div>
										</td>
									</tr>
								</c:forEach>
								<%-- ==== List with Comments ==== --%>
							</c:when>
							<c:otherwise>
								<%-- ==== No Comments ==== --%>
								<div style="background-color: #f6f6f6; padding: 6px; width: 50%;">
								 No responses posted.
								 <br />
								 <a href="<c:url value="/spring/botverse/linkaddcomment.html?viewid=${command.link.id}" />" class="linklist_botnav">add new comment</a>
								</div>
								<div style="background-color: #e2e5fd; padding: 6px; border-top: 1px solid #777; width: 50%;">&nbsp;</div>
							</c:otherwise>
						</c:choose>			
						<%-- *************************** --%>
						<%-- End Section with Comments --%>
						<%-- *************************** --%>
					</table>
							
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
