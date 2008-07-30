<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist Contact</title>

<META NAME="DESCRIPTION" CONTENT="Botlist - Contact">
<META NAME="keywords" CONTENT="contact, berlin, search, index, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">


<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

</head>
<body>

<div id="body_content_center">

 <div style="border-bottom: 1px solid #816943;">
	<img src="<c:url value="/company/images/building_orange_roof.jpg" />">
 </div>
<h1 class="bot_titlelogo">Botverse - Contact Information</h1>
			
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>

<div style="margin: 10px;">

	<%-- Add Welcome user message and login content (above content border line) --%>
	<%@include file="/WEB-INF/jsps/general/botverse_profile_nav.jsp" %>
	<%-- End of Welcome Header --%>

<div style="border: 1px solid #DDD; padding: 10px; margin-right: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">			
	<p>		
	 <div style="color: #555;">
			Botlist is a product by 
			<a href="http://www.botnode.com">botnode.com</a>			
			
			<div style="margin-top: 20px;">
				<%-- *** Image Table *** --%>
				<table cellspacing="0" cellpadding="20">
					<tr>
						<td style="background-color: #e7f0f1;">
			 				<img src="/botlist/company/images/help/help_botlistmain.jpg">			 
			 			</td>
			 		</tr>
			 		<tr>
			 			<td style="background-color: #ddd; width: 400px;">			 			
						<b>Berlin Brown</b><p />
    					He is a software developer with a diverse background in a multitude of 
    					different environments. He has worked with the CDC/SAIC, 
    					Geographic Information Systems (GIS) and now works for a Financial Services firm. 
    					<p />
    					You can contact him at <a href="mailto:berlinDOTbrownATgmail.com">(berlin dot brown / gmail)</a>
			 			</td>
			 		</tr>
			 	</table>
			 	<%-- *** End Image Table *** --%>
			</div>
			<br />
			Contact the <a href="mailto:webadmin%20at%20botspiritcompany.com">Botspiritcompany administrator</a> for technical issues.
			
			<br />
			<div style="margin-top: 10px; font-size: 8pt; width: 60%">
			All information stored on or transmitted from the Application(s) 
			is the property of the Company and may be used by the Company for any purpose. 			
			</div>
	</div>
	<%-- End of content section (grey text) --%>
</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
  
</body>
</html>
