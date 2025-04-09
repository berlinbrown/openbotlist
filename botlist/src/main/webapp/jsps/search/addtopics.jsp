<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Add Hot Search Topic</title>

<META NAME="DESCRIPTION" CONTENT="Botlist - Add Hot Topic">
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
<h1 class="bot_titlelogo">Botlist - Add Hot Search Topic</h1>
	<%-- Navigation Header --%>
	<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>	
	<%-- End of Navigation Header --%>
	
<div style="margin: 10px;">
		
<div style="border: 1px solid #DDD; padding: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link">
	
<div>
 <a href="<c:url value="/spring/search/search.html?querymode=enabled&query=${command.searchTerm}" />" class="linklist_botnav">+ Return to search results</a>
</div>
<div style="margin-top: 8px; width: 500px">
	<span style="color: #888888;"><b>Add hot search topic</b>.</span>    
</div>
	
<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />
<table>
	<!-- Search Term -->
	<tr>
		<td valign="top"><b>Hot Topic Search Term:</b></td>
	</tr>	
	<tr>
		<td>
		<form:input path="searchTerm" size="45" />
		</td>
		<td>
			<form:errors path="searchTerm" cssClass="general_field_error" />
		</td>
	</tr>	
		
	<!-- Description -->
	<tr>
		<td valign="top">
			<b>Description:</b>
		</td>
	</tr>	
	<tr>
		<td>
		 <form:input path="description" size="45" />		 
		</td>
		<td>
			<form:errors path="description" cssClass="general_field_error" />
		</td>	
	</tr>
		
</table>

<%-- Button to confirm registration --%>
<div style="margin-top: 6px; width: 500px">
    <div style="margin-top: 6px;">
	  <input type="submit" value=" Add Topic " />
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
