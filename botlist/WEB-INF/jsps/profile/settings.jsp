<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Settings</title>
 
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
				BotList | Profile Settings
			</h1>							
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					
						
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
			
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/command_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
			
				<div style="border-top: 1px solid #DDD; padding: 4px;">															
					<div style="margin-top: 4px">
					
						<form:form method="post">
						<form:errors path="*" cssClass="general_field_error" />
															
						<h1 class="bot_settingsinfo">
							Account Settings - Update your profile settings
						</h1>
												
						<%-- User Info Table  --%>
						<table>							 
						 <tr>
						  <td>
						    <b>User Name:</b>
						  </td>
						  <td>
					 		<c:out value="${command.userName}" />					
					 	  </td>
					 	 </tr>					 	 
					 	 <tr>
						  <td>
						    <b>Account Number:</b>
						  </td>
						  <td>
					 		<c:out value="${command.accountNumber}" />					
					 	  </td>
					 	 </tr>					 	 
					 	 <tr>
						  <td>
						    <b>Account Created On:</b>
						  </td>
						  <td>
					 		<fmt:formatDate pattern="EE MMM, dd HH:mm" value="${command.createdOn.time}" />
					 	  </td>
					 	 </tr>					 	 
					 	</table>
					 	<%-- End of User Table  --%>
					 	
					 	<h1 class="bot_settingsinfo">
							General Options
						</h1>
						<%-- View Options for Link Settings --%>
						<table>							 
						 <tr>
						  <td>
						    <b>Link Color Settings:</b>
						  </td>
						 </tr>
						 <tr>
						   <td>
						   	<div style="border-bottom: 1px solid #333;">
						   		<span 
						   			style="font-family: arial, verdana, helvetica, sans-serif; font-weight: bold; font-size: 12pt; color: <c:out value='#${command.linkColor};' /> ">
						   			(Current Setting)
						   		</span>						   		
						   	</div>
						   	
						   	<!-- Table for Selection Present the selection (row color grey) -->
						   	<table>
						   	 <tr>
						   	 	<td colspan="2">
						   	 		<span 
						   			   style="font-family: arial, verdana, helvetica, sans-serif; font-weight: bold; font-size: 12pt; color: #444444; /> ">
						   			 The internet has over 1 million widgets.
						   			</span>						   			
						   		</td>
						   	</tr>
						   	<tr>
						   	<td>
						   			<table>
						   			<tr>
								   		<td>
								   			<div style="background-color: #444444; width: 60px; height: 24px;"></div>
								   	 	</td>
								   	 	<td>
								   	 		<form:radiobutton path="linkColor" value="444444" />
								   	 	</td>
								   	 </tr>
								   	 </table>
							 </td>
						   	 </tr>
						   	</table>
						   	<!-- End Table for Selection Present the selection -->
						   							   				
						   	<!-- Table for Selection Present the selection (row color light blue) -->
						   	<table>
						   	 <tr>
						   	 	<td colspan="2">
						   	 		<span 
						   			   style="font-family: arial, verdana, helvetica, sans-serif; font-weight: bold; font-size: 12pt; color: #12129D; /> ">
						   			 The internet has over 1 million widgets.
						   			</span>						   			
						   		</td>
						   	</tr>
						   	<tr>
						   	<td>
						   			<table>
						   			<tr>
								   		<td>
								   			<div style="background-color: #12129D; width: 60px; height: 24px;"></div>
								   	 	</td>
								   	 	<td>
								   	 		<form:radiobutton path="linkColor" value="12129D" />
								   	 	</td>
								   	 </tr>
								   	 </table>
							 </td>
						   	 </tr>
						   	</table>
						   	<!-- End Table for Selection Present the selection -->
						   	
						   	
						   	<!-- Table for Selection Present the selection (row color light blue) -->
						   	<table>
						   	 <tr>
						   	 	<td colspan="2">
						   	 		<span 
						   			   style="font-family: arial, verdana, helvetica, sans-serif; font-weight: bold; font-size: 12pt; color: #3838E5; /> ">
						   			 The internet has over 1 million widgets.
						   			</span>						   			
						   		</td>
						   	</tr>
						   	<tr>
						   	<td>
						   			<table>
						   			<tr>
								   		<td>
								   			<div style="background-color: #3838E5; width: 60px; height: 24px;"></div>
								   	 	</td>
								   	 	<td>
								   	 		<form:radiobutton path="linkColor" value="3838E5" />
								   	 	</td>
								   	 </tr>
								   	 </table>
							 </td>
						   	 </tr>
						   	</table>
						   	<!-- End Table for Selection Present the selection -->						   	
						   	
						   </td>
						 </tr>
					    </table>
					    <%-- End of View Options for Link Settings --%>
					    
					    <h1 class="bot_settingsinfo">
							Update
						</h1>
						<div>
							<%-- Submit the Content --%>
							<div style="margin-top: 6px;">
	  							<input type="submit" value=" Save Settings " />
							</div>
						</div>
						
						</form:form>
						<%-- End of Full Content --%>
					</div>			
										
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
