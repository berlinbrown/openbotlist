<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botverse - Admin Edit Headline</title>

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
<h1 class="bot_titlelogo">BotList - Admin Edit Headline</h1>
	<%-- Navigation Header --%>
	<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
	<%-- End of Navigation Header --%>
	
<div style="margin: 20px;">

<div style="border: 1px solid #DDD; padding: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link">
			
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>

	<!-- Link -->
	<tr>
		<td valign="top"><b>Headline:</b></td>
	</tr>
	<tr>
		<td valign="top">
			<form:input path="headline" size="40" />
		</td>
		<td valign="top">
			<form:errors path="headline" cssClass="general_field_error" />
		</td>
	</tr>
	<tr>
		<td><input type="submit" value=" Submit Headline " /></td>
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
