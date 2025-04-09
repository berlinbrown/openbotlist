<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>RDF Data Output with Nutch</title>

<META NAME="DESCRIPTION" CONTENT="BotList - Pipes Api">
<META NAME="keywords" CONTENT="pipes, search, index, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">

<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" />
<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" />

<style type="text/css">
 <%@include file="/WEB-INF/jsps/general/botverse_link_css.jsp" %>	
</style>

<script type="text/javascript">
	
</script>

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
	Nutch is an open-source web-search tool written purely in Java.  Combined with the RDF data dump
	from the Botlist application, you can crawl hundreds of thousands of popular internet articles.
	<br />
	<i>(The following document is based on the Nutch (document 0.8-9x) documentation)</i>
	</div>
	
	<table class="sample" width="70%">
	
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Requirements
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px;">			
				<ul>
				 <li> Java 1.4.x, either from Sun or IBM on Linux is preferred. Set NUTCH_JAVA_HOME to the root of your JVM installation.
				 <li> Nutch version 0.9 (Apr 2007 vers).  This document is based on Nutch vers 0.9 (7/10/2007) but may
				  work with earlier or later versions.
   				 <li> (optional) Apache's Tomcat 5.x.
   				</ul>
   				<br />
   				<a href="http://lucene.apache.org/nutch/index.html">Nutch Home and Download</a>
   				<p />
   				Also, download the Botlist rdf content if you haven't already done so:<br/>   				
				<a href="<c:url value="http://botspiritcompany.com/botdata/botlist_rdfcontent.tar.gz" />" >botlist_rdfcontent.tar.gz</a>
				<br />				
				(right-click and "save-as" on the link above to download the rdf content)
			</div>
		</td>
	</tr>
	
	
	<tr>
		<th>
			<%-- Header --%>
			&nbsp;
			Botlist RDF Output and crawling with Nutch
			&nbsp;&nbsp;&nbsp;&nbsp;
		</th>						
	</tr>
	<tr>
		<td>
			<div style="margin-left: 20px;">				
			After you downloaded and unpacked nutch.  You need to run the following commands.  You start
			by extracting the URLs from the Botlist RDF dump, then injecting the crawler database, fetching
			the URLs and then indexing the data.

 <p ><b>mkdir dmoz</b>
			
 <p ><b>bin/nutch</b> org.apache.nutch.tools.DmozParser botlist_rdfcontent.xml > dmoz/urls
 
 <p >
 <i>Initialize the crawl db with the selected urls.</i>
 <br />
 <p ><b>bin/nutch</b> inject crawl/crawldb dmoz
 
 <p>
 <i>Generate a fetchlist from the database:</i><br />
 <p ><b>bin/nutch</b> generate crawl/crawldb crawl/segments
 
 <p >s1=`ls -d crawl/segments/2* | tail -1`
 
 <p>
 Now run the fetcher on this segment with:
 <p>
 
 <p ><b>bin/nutch</b> fetch $s1
 
 <p ><b>bin/nutch</b> updatedb crawl/crawldb $s1
 
 <p ><b>bin/nutch</b> invertlinks crawl/linkdb crawl/segments/*
 
 <p ><b>bin/nutch index</b> crawl/indexes crawl/crawldb crawl/linkdb crawl/segments/*
 
 <div style="color: #777; font-size: 12pt; width: 80%">
		After the index has been created, the search tool has been built and you can use the
		nutch provided web-application (built for the Tomcat servlet engine), use Lucene, or use
		for example the simple search command below to perform searchs:
</div>
 
 <p ><b>bin/nutch</b> org.apache.nutch.searcher.NutchBean cats			
			
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
