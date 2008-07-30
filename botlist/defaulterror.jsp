<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title><spring:message code="system.html.title"/></title>
 		 	
  	<META NAME="DESCRIPTION" CONTENT="Error Pagee">
 	<META NAME="keywords" CONTENT="error page, botlist">
     	
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" >
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" > 
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" > 
  	<link href="<c:url value="/company/stylesheets/botlist_general2.css" />" media="screen" rel="Stylesheet" type="text/css" > 
  	  	
</head>
<body>
		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" alt="Botlist header logo">
 			</div>
			<h1 class="bot_titlelogo">
				[ Error ]
			</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>
			
			<div style="margin: 8px;">
				<h3 class="bot_headerinfo">				
				All your favorite links in one place.  Bookmark popular items for you and friends.  Check out what other people are interested in.								
				</h3>
			</div>						
			<div style="margin: 8px;">
				
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/default_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
				
				<div id="link_content_group">
					
					<div style="color: #A00; font-weight: bold; margin-left: 20px; margin-top: 20px; margin-bottom: 20px; font-size: 16px;">
						We are sorry, an error occurred when we tried to process your request.
					</div> <!-- End of Error Message Area -->
				</div> <!-- End of Inner Content Div -->
				
			</div>								
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div> 
				
</body>
</html>