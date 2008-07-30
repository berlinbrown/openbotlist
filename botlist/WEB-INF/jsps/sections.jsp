<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Ad Listings</title>
 
  	<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
 	<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
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
				Sections
			</h1>	
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
			
			
			<div style="margin: 10px 10px 10px 10px;">
				<h3 class="bot_headerinfo">
				BotList allows you to promote your personal profile or
				something of interest.  Post personal profile listings, new and exciting websites, or
				upcoming events.
				</h3>
				<br>
				<div style="color: #777; font-size: 12pt;">
					Area: <c:out value="${command.city.cityName}" />
				</div>
			</div>
			
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
								
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>				
				<div style="border: 1px solid #DDD;padding: 10px;">											
					<table width="100%">
					<tr>										
					<td valign="top">
					
					<a href="<c:url value="/spring/citylist.html" />" class="linklist_botnav">/ Home</a>
					
					<!-- Inner Data table -->
					<table class="sample">					
					<tr>
						<th>
							Sections
						</th>
					</tr>					
					<tr>
						<td>							
							 <c:forEach items="${command.sections}"
								var="section" varStatus="status">
								<a href="<c:url value="/spring/listings.html?viewid=${section.generatedId}" />" >
									+ <c:out value="${section.sectionName}" /> (<c:out value="${fn:length(section.listings)}" />)
									<br>
								</a>			
							</c:forEach>
														
						</td>
					</tr>
					</table>
					<!--  End of Table -->
					
					</td>
					<td valign="top" align="right" width="40%">
						<!-- New DIV with orange background -->
						<div>					
							<div>
								<img src="<c:url value="/company/images/BoxLogoVertical1.jpg" />" />
							</div>
						</div>												
					</td>
					</tr>
					</table>
					<!--  End of Outer Table -->
					
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
