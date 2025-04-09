<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Promote yourself or something else interesting">
<META NAME="keywords" CONTENT="listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">BotList - Add Comment</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					


<div style="margin: 20px;">

<div style="border: 1px solid #DDD; padding: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<!-- Build the table for entering the new department information -->
	<a href="<c:url value="/spring/botverse/botverse.html" />" >Botverse Links</a>
	
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>

	<!-- First Name -->
	<tr>
		<td valign="top">Comment:</td>
	</tr>
	<tr>
		<td valign="top">
			<form:textarea rows="10" cols="60" path="message"  />
		</td>
		<td valign="top">
			<form:errors path="message" cssClass="general_field_error" />
		</td>
	</tr>
		
	<!-- Username -->
	<tr>
		<td valign="top">Username:</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="fullName" size="80" />
		</td>
		<td valign="top">
			<form:errors path="fullName" cssClass="general_field_error" />
		</td>
	</tr>
		
		
	<tr>
		<td valign="top">Email: (optional, will not be displayed)</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="email" size="80" />
		</td>
		<td valign="top">
			<form:errors path="email" cssClass="general_field_error" />
		</td>
	</tr>
		
	<tr><td valign="top">Keywords (space separated, optional)</td></tr>
	<tr>
		<td>
		<form:input path="keywords" size="40" />
		</td>		
	</tr>
	
	<tr>
		<td><input type="submit" value=" Submit Comment " /></td>
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
</table>
<!-- End of the table -->
</form:form>

</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

</body>
</html>
