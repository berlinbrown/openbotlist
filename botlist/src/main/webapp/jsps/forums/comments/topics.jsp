<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Recent Botverse Comments</title>
 
  	<META NAME="DESCRIPTION" CONTENT="BotList - Recent Botverse Comments">
 	<META NAME="keywords" CONTENT="botverse, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />   	
</head>
<body>
		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" >
 			</div>
			<h1 class="bot_titlelogo">
				Botverse | Recent Comments
			</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>																	
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
			
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
			
				<div style="border: 1px solid #DDD; padding: 10px;">

					<div style="width: 70%;">
						<%-- Table with comment replies --%>
						<table class="linklist_data" cellspacing="2" cellpadding="0" width="100%">
							<c:forEach items="${command.listings}" var="childComments" varStatus="status"> 
							   	<tr>					
									<td style="border-top: 1px solid #DDD;">
										parent title: 
										 <i><c:out value="${childComments.entityLink.urlTitle}" escapeXml="false" /></i>
										 / 
										 <a class="linklist_sample" href="<c:url value="/spring/botverse/linkviewcomments.html?viewid=${childComments.entityLink.id}" />" >
										  parent
										 </a>
									</td>
								</tr>
								<tr>							
									<td>
										<div class="single_view_forum" style="background-color: #F3F3F3;">
											<c:out value="${childComments.message}" escapeXml="false" />
										</div>
									</td>
								</tr>
								<tr>					
									<td align="right">	
									  <div style="margin-left: 20px;">
										by <c:out value="${childComments.fullName}" /><br />
											on <fmt:formatDate pattern="EE MMM dd, yyyy hh:mm a" value="${childComments.createdOn.time}" />
									 </div>
									</td>
								</tr>
							</c:forEach>
							<%-- End of table with comment replies --%>
						</table>					
						<%-- Begin new table/section with list of comments --%>
					</div>
					
					<%-- End of section with border --%>			
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
