<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Ad Listings</title>
 
  	<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
 	<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	<link href="<c:url value="/company/stylesheets/docwriteadmin.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	
</head>
<body>

		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" >
 			</div>
			<h1 class="bot_titlelogo">
				Admin Home
			</h1>	
			
			<div style="margin: 10px;">
				<h3 class="bot_headerinfo">
				BotList Administration | docwrite
				</h3>
			</div>
			
			<!-- Section with City Listing -->
			<div style="margin: 10px;">
				<div style="border: 1px solid #DDD; padding: 10px;">
				
					<a href="<c:url value="/botlistadmin/secure/index.html" />" >
						Admin Login
					</a>
					| <a href="<c:url value="/botlistadmin/j_acegi_logout" />">Logout</a>
													
					<%-- Content --%>
					<div style="margin-left: 20px; margin-top: 10px;">					
												
<form:form method="post" enctype="multipart/form-data">
<form:errors path="*" cssClass="general_field_error" />

<%-- Table with only submission form --%>
<b>Recent File History</b><br />
<table class="recent_files" cellspacing="0" cellpadding="0">
  <tr>
  	<th>
  		title
	</th>
	<th>
  		date
	</th>
  </tr>
  <c:forEach items="${command.files}"  
		var="file" varStatus="status">
	<tr>
		<td>				
			<c:out value="${file.title}" />			
		</td>
		<td>
			<botlistutil:timePast dateValue="${file.createdOn.time}" />
			on <fmt:formatDate pattern="EE MMM, dd" value="${file.createdOn.time}" />
		</td>
	</tr>
 </c:forEach>					
</table>

<%-- Table with only submission form --%>
<table>
	<%-- Title --%>
	<tr>
		<td valign="top">
			<b>Title:</b>
		</td>
	</tr>
	<tr>
		<td>
		<form:input path="title" size="40" />
		</td>
		<td>
			<form:errors path="title" cssClass="general_field_error" />
		</td>
	</tr>
	
	<tr>
		<td>
			<input type="submit" name="save" id="save" value=" Save Document " />
			<input type="submit" name="generate" id="generate" value=" Generate " />
		</td>
	</tr>
	
	<tr>
	<td valign="top">
		<!-- Additional Section for Spam Prevition Simple Addition -->
		<div style="margin-left: 24px; margin-top: 10px;">
				<div class="info_spam_addition" id="info_spam_addition">
					<h2>Please add the following values in order to submit</h2>
					<p>
						<!-- table for spam verify -->
						<table>
						<tr>
							<td>
								<c:out value="${command.firstInput}" />
							</td>
							<td>+</td>
							<td><c:out value="${command.secondInput}" /></td>
							<td>=</td>
							<td>
								<form:input size="6" path="userSolution" />
							</td>
						</tr>
						</table>
						<!-- End of Table for spam verify -->
				</div>
				<!-- End of Addition for Spam Check -->
		</div>
		</td>
		<td valign="top">
			<div style="margin-top: 10px;">
				<form:errors path="userSolution" cssClass="general_field_error" />
			</div>
		</td>
	</tr>	
	<%-- Next Row for adding images --%>
	<tr>
		<td>
			<div style="margin-left: 24px;">
			 <fieldset>
				<legend align="left">&nbsp;<b>Select file for upload</b>&nbsp;</legend>
					<%-- Div to add padding and background --%>
					<div style="padding: 4px;">
					<table cellpadding="4" cellspacing="4" border="0">
					<tr>
						<td>
							<nobr>File 1:</nobr>
						</td>
						<td>
							<spring:bind path="command.uploadFilenameFirst">
								<input type="file" name="<c:out value="${status.expression}" />" size="35">	
							</spring:bind>
						</td>
					</tr>					
					</table>
					</div>				
			</fieldset>
		   </div>
		   <!-- End of Div around field set -->
		</td>
	</tr>
	<!-- End of Row -->
	
</table>
<!-- End of the table -->
</form:form>
												
					</div>
					<%-- Content --%>
													
				</div>
			</div>
			
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div>	
  
</body>
</html>
