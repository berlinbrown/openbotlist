<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Logout</title>
 
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
				BotList
			</h1>							
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
						
			<!-- Section with City Listing -->
			<div style="margin: 40px;">
				<div style="border-top: 1px solid #DDD; padding: 10px;">
										
					<b>You have successfully logged out.</b>
					<div style="margin-top: 10px">					
						<%-- User Info Table  --%>
						<table>							 
						 <tr>
						  <td>
						    <b>logged out user:</b>
						  </td>
						  <td>
					 		<c:out value="${command.userName}" />					
					 	  </td>
					 	 </tr>
					 	 <tr>
						  <td>
						    <b>account number:</b>
						  </td>
						  <td>
					 		<c:out value="${command.accountNumber}" />					
					 	  </td>
					 	 </tr>					 	 
					 	</table>
					 	<%-- End of User Table  --%>
					</div>													
					<p>
 					 <a href="<c:url value="/" />" >Return to Botlist Home</a>
					</p>
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
