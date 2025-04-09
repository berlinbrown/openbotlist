<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dt" uri="http://jakarta.apache.org/taglibs/datetime-1.0" %>
<%@ taglib prefix="req" uri="http://jakarta.apache.org/taglibs/request-1.0" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Ad Listings</title>
 
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
				Current Ad Listings
			</h1>							
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
						
			<br>
			<div style="color: #777; font-size: 12pt; margin-left: 10px;">
				Area: <c:out value="${command.city.cityName}" />
				| <c:out value="${command.section.sectionName}" />
			</div> 
			
			<!-- Section with City Listing -->
			<div style="margin: 20px;">
				<div style="border: 1px solid #DDD; padding: 10px;">
					
					<a href="<c:url value="/spring/citylist.html" />" class="linklist_botnav">/ Home</a>
					| <a href="<c:url value="/spring/citylist.html" />" class="linklist_botnav">City Listings</a>
					| <a href="<c:url value="/spring/create_listing.html" />" class="linklist_botnav">New Ad Listing</a>										
					<p>					
					
					<table class="linklist_data">
					<c:forEach items="${command.listings}"  var="listing" varStatus="status">
						<tr>
							<td>
								<a class="linklist_objlinks" href="<c:url value="/spring/viewlisting.html" />?viewid=<c:out value="${listing.id}" />" >
									<c:out value="${listing.title}" />
								</a>
								<div class="linklist_comments_txt">
								 	<span class="linklist_comments_date">		
										<fmt:formatDate pattern="EE dd, MMM yyyy hh:mm" value="${listing.createdOn.time}" />
									</span>
								</div>								
							</td>
							
						</tr>
					</c:forEach>
					</table>
		
					
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
