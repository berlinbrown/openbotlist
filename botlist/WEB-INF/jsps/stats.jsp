<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Statistics</title>

<META NAME="DESCRIPTION" CONTENT="Botlist - (statistics) Promote yourself or something else interesting">
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
<h1 class="bot_titlelogo">Botlist - Statistics | Home</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					

<div style="margin: 2px;">
<div style="padding: 2px; margin-right: 4px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<!-- Build the table for entering the new department information -->
	<br />	
	<table width="100%">
	<tr>
	<td valign="top">
		
		<div style="border-top: 1px solid #CCC; padding: 6px; margin-top: 6px;">
			
			<%-- Present the Date by a particular Day --%>
			<div>
				Stats have been disabled.
			<div>
			<%-- End of Date Totals --%>
			
			<%-- Section to display stat image --%>			
			<div style="margin-top: 6px; margin-left: 10px;">
				<%-- **************************** --%>
				<%-- Build image chart based on freechart, on request --%>
				<%-- **************************** --%>
				<%-- 
				 (TODO: removed for more testing
				 <img src="<c:url value="/dayStatChart.png" />" border="0"/>
				 --%>
			
			</div>
			
		</div>				
	</td>
	<td valign="top" align="right" width="40%">
			<!-- New DIV with orange background -->
			<div>					
				<div>
					<img src="<c:url value="/company/images/BoxLogoVertical1.jpg" />" />
				</div>
			</div>												
	</td>
	</tr>
	</table>
	<!--  End of Outer Table -->
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

</body>
</html>
