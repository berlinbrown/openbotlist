<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - View Forums</title>
 
  	<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
 	<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
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
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" >
 			</div>
			<h1 class="bot_titlelogo">
				BotList - Forums
			</h1>							
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
									
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
							
				<div style="border: 1px solid #DDD; padding: 10px">					
					<a href="<c:url value="/spring/forums/forums.html" />" class="linklist_sample">Forums/Home</a> | 
					<a href="<c:url value="/spring/forums/forumaddtopic.html" />" class="linklist_sample">Add Topic</a>
					<div style="margin-top: 6px;">						
						<%-- Banner Section --%>
						<div>
						<c:choose>
							<c:when test="${command.headline != null}" >
							  <div style="width: 80%;">
								<div style="color: #444; font-size: 14pt; background-color: #e7f0f1; padding: 6px;">
								  <c:out value="${command.headline}" />
								</div>
							 </div>
							</c:when>
						</c:choose>
						</div>
						<%-- End of Banner Section --%>	
						<h2 class="bot_splashinfo">
							Forums on
							<jsp:useBean id="now" class="java.util.Date" />
							  <fmt:formatDate pattern="EE dd, MMM yyyy" value="${now}" />
						</h2>
						<%-- Section forum links, shifted for presentation --%>	
						<div style="margin-left: 14px; margin-top: 8px;">						
						<%-- (right column) Table Data Grid With the Forum Comments --%>
						<table class="linklist_data" cellspacing="0" cellpadding="0">
						<c:forEach items="${command.listings}"
									var="listing" varStatus="status">
							<tr>							
								<td>
									<a class="linklist_objlinks"
										href="<c:url value="/spring/forums/forumviewtopic.html?viewid=${listing.id}&commentsct=${fn:length(listing.childComments)}" />" ><c:out value="${listing.subject}" /></a>
									/ <span class="linklist_comments">
											<c:out value="${listing.fullName}" />
										</span>
										<span>
										 <%-- Place Number of Comments --%>
										 (<c:out value="${fn:length(listing.childComments)}" /> comments)
										</span>
										<div style="margin-left: 8px; margin-bottom:4px;">
										  <div class="linklist_comments_txt">
											 <span class="linklist_comments_date">							
												<botlistutil:timePast dateValue="${listing.createdOn.time}" />
												on <fmt:formatDate pattern="EE MMM, dd" value="${listing.createdOn.time}" />
											 </span>
										  </div>
										</div>
								</td>							
							</tr>
						</c:forEach>
						</table>								
						<%-- End of Table --%>
						
					   </div>
					   <%-- End of the shifted section (links) --%> 
					  
					</div>
					<%-- margin border --%>
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
