<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Remote Sync</title>
</head>
<body>
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>	
	<tr>
		<td valign="top">Key</td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="developerKey" size="80" />
		</td>
		<td valign="top">
			<form:errors path="developerKey" cssClass="general_field_error" />
		</td>
	</tr>
	
	<tr>
		<td valign="top">Data</td>
	</tr>
	<tr>
		<td>
		<form:input path="remoteData" size="80" />
		</td>
		<td>
			<form:errors path="remoteData" cssClass="general_field_error" />
		</td>
	</tr>
				
	<tr>
		<td><input type="submit" value=" Submit Data " /></td>
	</tr>		
</table>
<form:hidden path="remoteSyncKey" />
<!-- End of the table -->
</form:form>

</body>
</html>
