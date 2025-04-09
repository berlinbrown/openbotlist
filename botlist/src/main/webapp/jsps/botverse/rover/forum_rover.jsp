<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>Forum Rover</title>
</head>
<body>
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>
	
	<!-- Subject -->
	<tr>
		<td valign="top">Subject:</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="subject" size="80" />
		</td>
		<td valign="top">
			<form:errors path="subject" cssClass="general_field_error" />
		</td>
	</tr>
	
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
		
	<!-- First Name -->
	<tr>
		<td valign="top">Full Name:</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="fullName" size="80" />
		</td>
		<td valign="top">
			<form:errors path="fullName" cssClass="general_field_error" />
		</td>
	</tr>
		
	<tr><td valign="top">Keywords (space separated, optional)</td></tr>
	<tr>
		<td>
		<form:input path="keywords" size="40" />
		</td>		
	</tr>
	
	<tr><td valign="top">Verify</td></tr>
	<tr>
		<td>
		<form:input path="roverVerify" size="40" />
		</td>		
	</tr>
		
	<tr>
		<td><input type="submit" value=" Submit Link " /></td>
	</tr>		
</table>
<!-- End of the table -->
</form:form>

</body>
</html>
