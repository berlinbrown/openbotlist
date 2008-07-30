<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - User Overview</title>
  
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
				BotList | User Overview
			</h1>							
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
						
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
			
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
			
				<div style="border-top: 1px solid #DDD; padding: 4px;">															
					<div style="margin-top: 4px">
															
						<h1 class="bot_settingsinfo">
							User Public Profile
						</h1>
												
						<%-- User Info Table  --%>
						<table>							 
						 <tr>
						  <td>
						    <b>User Name:</b>
						  </td>
						  <td>
					 		<c:out value="${command.coreUser.userName}" />					
					 	  </td>
					 	 </tr>
					 	 <tr>
						  <td>
						    <b>User since:</b>
						  </td>
						  <td>
					 		<fmt:formatDate pattern="EE MMM, dd HH:mm" value="${command.coreUser.createdOn.time}" />
					 	  </td>
					 	 </tr>					 	 
					 	</table>
					 	<%-- End of User Table  --%>
					 	
					 	<%-- ==== Next section with user links --%>
					 	<h1 class="bot_settingsinfo">
							Submitted Links
						</h1>
						
						<table class="linklist_data" cellspacing="2" cellpadding="0">
							<c:forEach items="${command.linkListings}"  var="listing" varStatus="status">
									<%-- Begin row production for botverse links --%>
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
													<%-- &nbsp;(<botlistutil:hostname value="${listing.mainUrl}" />) --%>
													&nbsp;(<c:out value="${listing.hostnameDisplay}" /> <a href="<c:out value="${listing.hostnameDisplayUrl}" />" class="linklist_comments_host">+</a>)
												</span>
										</td>
									</tr>
							</c:forEach>
						</table>
						
						<%-- ==== End section with user links --%>
					 	
					 	<%-- End of Full Content --%>
					</div>			
										
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
