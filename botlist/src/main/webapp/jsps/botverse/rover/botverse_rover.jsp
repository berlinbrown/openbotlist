<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Bot Rover</title>
</head>
<body>
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>
	<!-- Link -->
	<tr>
		<td valign="top">Link</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="mainUrl" size="80" />
		</td>
		<td valign="top">
			<form:errors path="mainUrl" cssClass="general_field_error" />
		</td>
	</tr>
	
	<!-- Subject -->
	<tr>
		<td valign="top">Title:</td>
	</tr>
	<tr>
		<td>
		<form:input path="urlTitle" size="80" />
		</td>
		<td>
			<form:errors path="urlTitle" cssClass="general_field_error" />
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
