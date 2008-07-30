<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Forums</title>
 
  	<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting, View Forums">
 	<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising, view forums">
  
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
				[ BotList - Forums ]
			</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
					
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
			
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>							
				<div id="forum_listing_sect" >					

					<!-- Table Data Grid -->
					<table class="sample">					
					<tr>
						<th>
							Discussion Forums
						</th>						
					</tr>
					<c:forEach items="${command.listings}"
								var="listing" varStatus="status">
						<tr>							
							<td>								
								<a href="<c:url value="/spring/forums/viewforums.html?viewid=${listing.id}" />" >
								 + <c:out value="${listing.forumName}" />
								</a>
								(<c:out value="${command.forumCountMap[listing.forumName]}" />)
							</td>							
						</tr>
					</c:forEach>
					</table>
		
					<%-- Add community forum listings --%>
					<div style="margin-top: 12px; padding-left: 5px; font-weight: bold;">
						<a href="<c:url value="http://ghostbots.com/botlist/spring/citylist.html" />" >+ Botlist Community Listings</a>						
					</div> <%-- End of Community Listings --%>
					
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
