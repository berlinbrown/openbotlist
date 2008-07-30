<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Register</title>

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
<h1 class="bot_titlelogo">Botlist - Register</h1>
	<%-- Navigation Header --%>
	<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>	
	<%-- End of Navigation Header --%>
	
<div style="margin: 20px;">

		<%-- Add Welcome user message and login content (above content border line) --%>
		<%@include file="/WEB-INF/jsps/general/forreg_profile_nav.jsp" %>
		<%-- End of Welcome Header --%>		

<div style="border: 1px solid #DDD; padding: 20px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link">
	
<div style="margin-top: 6px; width: 500px">
	<span style="color: #888888;"><b>Register</b> in order to gain access to the all of botlist.</span>    
</div>
	
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>

	<!-- UserName -->
	<tr>
		<td valign="top"><b>User Name:</b></td>
	</tr>	
	<tr>
		<td>
		<form:input path="userName" size="30" />
		</td>
		<td>
			<form:errors path="userName" cssClass="general_field_error" />
		</td>
	</tr>
	
	<!-- Email -->
	<tr>
		<td valign="top">
			<b>Email:</b> 
			<br>
			<span style="color: #888888;">
				(Allows other people to email you through our web site. Your address is never revealed)
			</span>
		</td>
	</tr>	
	<tr>
		<td>
		  <form:input path="userEmail" size="30" maxlength="30" />
		</td>
		<td>
			<form:errors path="userEmail" cssClass="general_field_error" />
		</td>
	</tr>
	
	<!-- User URL -->
	<tr>
		<td valign="top">
			<b>Website:</b> 
			<br>
			<span style="color: #888888;">
				(optional: allow users to find about more about you)
			</span>
		</td>
	</tr>	
	<tr>
		<td>
		  <form:input path="userUrl" size="30" maxlength="254" />
		</td>
		<td>
			<form:errors path="userUrl" cssClass="general_field_error" />
		</td>
	</tr>
		
	<!-- Password -->
	<tr>
		<td valign="top">
			<b>Password:</b>
			<br>
			<span style="color: #888888;">(Enter an alphanumeric password between 6 and 20 characters)</span>
		</td>
	</tr>	
	<tr>
		<td>
		 <form:password path="userPassword" size="30" />		 
		</td>
		<td>
			<form:errors path="userPassword" cssClass="general_field_error" />
		</td>	
	</tr>
		
	<!-- Verify Password -->
	<tr>
		<td valign="top"><b>Verify Password:</b></td>
	</tr>	
	<tr>
		<td>
		<form:password path="verifyPassword" size="30" />
		</td>
		<td>
			<form:errors path="verifyPassword" cssClass="general_field_error" />
		</td>
	</tr>	
			
</table>
<!-- End of the table -->
<div style="margin-top: 10px"> 
  <IFRAME SRC="<c:url value="/company/spirit_user_reg_agreement.htm" />" NAME="userAgreement" WIDTH="560" HEIGHT="320" >
	Sorry, your browser doesn't support iframes.
  </IFRAME> 
</div>
<%-- Button to confirm registration --%>
<div style="margin-top: 6px; width: 500px">
	<span style="color: #888888;">Welcome to Botlist registration, once you have completed the registration form, click the register
	button.  You will be able to update your profile at any time after registering.</span>
    <div style="margin-top: 6px;">
	 <input type="submit" value=" Register " />
	</div>
</div>
</form:form>

</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

</body>
</html>
