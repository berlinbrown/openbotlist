<%@ page contentType="text/html"%>

<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>

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
<h1 class="bot_titlelogo">Botverse - Interesting things online</h1>
			
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
	The Pipes API allows you to pipe into data generated from Botlist.  Basically, use the pipe
	access points to extract Botlist data through the simple, text or XML output.  Use this REST
	API with your browser or favorite programming language to retrieve data.
	</div>
	
	<table class="sample">
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botverse Text Pipe
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px;">
				<a href="<c:url value="/spring/pipes/botverse_pipes.html" />" >botverse pipe (most recent text view)</a>
				<br />
				Text view output tab-delimited.<br />
				Column One = URL<br />
				Column Two = Title<br />
				Column Three = Date and Time<p />
			</div>
			<div style="margin-left: 20px;">
			  <c:url value="/spring/pipes/botverse_pipes.html" />
			</div>
		</td>
	</tr>
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botverse Search Pipe (enter query term to return search results)
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px; color: #777; font-size: 12pt;">
				This link contains the example query term "bush"; <br />
				modify that term when accessing this particular pipe <br />
				to return different search results. <br />
				<a href="<c:url value="/spring/pipes/search_pipes.html?querymode=enabled&query=bush" />" >botverse search pipe (text view)</a>
			</div>
			<div style="margin-left: 20px;">
			  <c:url value="/spring/pipes/search_pipes.html?querymode=enabled&query=bush" />
			</div>
		</td>
	</tr>
	
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botverse Global Search Pipe
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px; color: #777; font-size: 12pt;">
				Global search returns search results from urls outside of the<br/>
				Botlist application.<br/>
				<a href="<c:url value="/spring/pipes/global_pipes.html?querymode=enabled&query=python" />" >botverse search pipe (text view)</a>				
			</div>			
			<div style="margin-left: 20px;">
			 <c:url value="/spring/pipes/global_pipes.html?querymode=enabled&query=python" />
			</div>
		</td>
	</tr>
	
	
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Examples
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px; color: #777; font-size: 12pt;">
				Python Developer Example.  Simple GET Requests <br />				
				<a href="<c:url value="/company/examples/pipes_sample01.py" />" >simple python example (01)</a>
			</div>
			<div>
			 (right-click on link to download script)
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
	
	
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botlist RDF Data Output
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<a href="<c:url value="/spring/pipes/rdf.html" />" >rdf data</a>
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
