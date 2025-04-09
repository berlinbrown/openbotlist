<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>Pipes API</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Pipes Api">
<META NAME="keywords" CONTENT="pipes, search, index, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

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
<h1 class="bot_titlelogo">Botverse - Interesting things online | Post Popular Links, it is ok!</h1>
			
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
	
	<%--Text Description of Pipe Api --%>
	<div style="color: #777; font-size: 12pt; width: 80%">
	Botlist data is provided through a RDF dump that is published regularly.
	</div>
	
	<table class="sample" width="70%">
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botverse RDF Download
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px;">
				<a href="<c:url value="http://botspiritcompany.com/botdata/botlist_rdfcontent.tar.gz" />" >botlist_rdfcontent.tar.gz</a>
				<br />				
				(right-click and "save-as" on the link above to download the rdf content)
				<p>
				Use the rdf data dump with nutch in order to build a search tool based on the
				internet articles.  For more information click <a href="<c:url value="/spring/pipes/rdf_nutch.html" />">here (botlist rdf and nutch)</a>
				</p>
			</div>
		</td>
	</tr>	
		
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Data Licensing
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
		 <img src="<c:url value="/company/images/creative_logo.gif" />" />
		 <div>
		  Data released under:<br />
		  <a href="http://creativecommons.org/licenses/by/3.0/">Creative Commons 3.0 Attribution License</a><br />
	 	  Attribution Condition: You do not have to attribute the work to the author.  Basically, you can<br />
	 	  do what you like with the data.
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

 <%-- Debug, how long to process page --%>
 <div style="font-size: 10px; color: #888;text-align: right">
 <i>(process in <c:out value="${processingtime}" />s)</i>
 </div>
  
</body>
</html>
