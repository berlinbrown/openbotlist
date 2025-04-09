<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>Botlist - Admin Tools</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Pipes Api">
<META NAME="keywords" CONTENT="pipes, search, index, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botverse - Admin</h1>
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>

<div style="margin: 10px;">

<div style="border: 1px solid #DDD; padding: 10px; margin-right: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">			
	
	<%--Text Description of Pipe Api --%>
	<div style="color: #777; font-size: 12pt; width: 80%">
	Admin
	</div>
	
	<table class="sample">
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botverse Admin Tools
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px;">
				<a href="<c:url value="/spring/newadmin/botverse_editheadline.html" />" >edit botverse headline</a>
				<br />
				Set default header displayed on botverse home page.<br />
			</div>
		</td>
	</tr>	
	</table>
	<%-- End of Table, Pipe Link Content --%>
	
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>
  
</body>
</html>
