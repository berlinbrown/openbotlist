<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html" %>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 	<title>Botlist - Post Listings</title>
 
  	<META NAME="DESCRIPTION" CONTENT="Botlist - Promote yourself or something else interesting.  City Listings">
 	<META NAME="keywords" CONTENT="botverse, listing, bot, botlist, botlisting, bot's list, list, ads, advertising">
  
  	<link type="application/rss+xml" rel="alternate" title="Botverse - Link Listings" href="<c:url value="/spring/rss/listings_rss.html" />">
	<link href="<c:url value="/company/stylesheets/scaffold.css" />" media="screen" rel="Stylesheet" type="text/css" />
  	<link href="<c:url value="/company/stylesheets/newspirit.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	<link href="<c:url value="/company/stylesheets/botlist.css" />" media="screen" rel="Stylesheet" type="text/css" /> 
  	
</head>
<body>
		<div id="body_content_center">
			<div style="border-bottom: 1px solid #816943;">
				<img src="<c:url value="/company/images/building_orange_roof.jpg" />" >
 			</div>
			<h1 class="bot_titlelogo">
				Botlist - Ad Listings | Home
			</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>
			
			<div style="margin: 8px;">
				<h3 class="bot_headerinfo">
				Botlist allows you to promote a marketplace listing, personal profile or
				something of interest.  Posting personals, new and exciting websites, or
				upcoming events.
				</h3>
			</div>			
			<!-- Section with City Listing -->
			<div style="margin: 8px;">
				
				<%-- Add Welcome user message and login content (above content border line) --%>
				<%@include file="/WEB-INF/jsps/general/default_profile_nav.jsp" %>
				<%-- End of Welcome Header --%>
				
				<div style="border: 1px solid #DDD;">
				
				<!-- Table of Data Grid and Image (rowspan on the left = City Listings ) -->
				<table width="90%" >
				<tr>
				<td valign="top" rowspan="2">
					<!-- Table Data Grid -->
					<div style="padding: 4px;">
					<table class="sample">					
					<tr>
						<th>
							Listings in Various Cities
						</th>						
					</tr>
					<c:forEach items="${command.citylistings}"  
							var="citylisting" varStatus="status">
						<tr>
							<td>
								<a href="<c:url value="/spring/sections.html" />?city=<c:out value="${citylisting.id}" />">
									 / <c:out value="${citylisting.cityName}" />
									 (<c:out value="${fn:length(citylisting.listings)}" />)
								</a>
							</td>
						</tr>
					</c:forEach>					
					</table>
					</div>
					<%-- ==== End of City Listings ==== --%>
				</td>
				<td valign="top">
					<%-- ==== ************************* ==== --%>
					<%-- ==== Start Minor City Listings ==== --%>
					<%-- ==== ************************* ==== --%>
					<!-- Table Data Grid -->
					<div style="padding: 4px;">
					<table class="sample">					
					<tr>
						<th>
							Listings in Other Cities
						</th>						
					</tr>
					<c:forEach items="${command.cityListingsMinor}"  
							var="citylisting" varStatus="status">
						<tr>
							<td>
								<a href="<c:url value="/spring/sections.html" />?city=<c:out value="${citylisting.id}" />">
									 / <c:out value="${citylisting.cityName}" />
									 (<c:out value="${fn:length(citylisting.listings)}" />)
								</a>
							</td>
						</tr>
					</c:forEach>					
					</table>
					</div>
					<%-- ==== End of Minor Listings ==== --%>
				</td>
				<td valign="top" align="right">
					<!-- table just for search -->
					<table class="sample">
					<tr>
						<th>
							&nbsp;Search Listings
						</th>						
					</tr>
					<tr>
					<td>
					<!-- Begin Search Form -->
							<form method="get" action="<c:url value="/spring/search/search.html" />">
								<table>
								<tr>
								<td> 
									<input name="query" size="26" />
								</td>
								<td>
									<input type="submit" value=" Search " />
								</td>
								</tr>
								</table>
								<input type="hidden" name="querymode" value="enabled" />
							</form>
							<!-- End of Form -->
							<p style="text-align: right">
							<a href="<c:url value="/spring/rss/listings_rss.html" />" >
								<img border="0" src="<c:url value="/company/images/rss.gif" />" />
							</a>
							</p>
							<%-- ==== ************************* ==== --%>
							<%-- ==== Start Minor City Listings ==== --%>
							<%-- ==== ************************* ==== --%>
							<!-- Table Data Grid -->
							<div style="padding: 4px;">
							<table class="sample">					
							<tr>
								<th>
									Listings in Other Cities (cont)
								</th>						
							</tr>
							<c:forEach items="${command.cityListingsMinorTwo}"  
									var="citylisting" varStatus="status">
								<tr>
									<td>
										<a href="<c:url value="/spring/sections.html" />?city=<c:out value="${citylisting.id}" />">
											 / <c:out value="${citylisting.cityName}" />
											 (<c:out value="${fn:length(citylisting.listings)}" />)
										</a>
									</td>
								</tr>
							</c:forEach>					
							</table>
							</div>
							<%-- ==== End of Minor Listings (two) ==== --%>														
					</td>
					</tr>
					</table>
					<!-- End of Table -->
				</td>
				</tr>
				
				<tr>
					<td colspan="2" align="right">						
						<div style="text-align: right">
							<h2 class="bot_splashinfo">
								Fresh. User Controlled Content.
							</h2>
						</div>
					</td>
				</tr>
				
				</table>
				<!-- End Data/Image Table -->
					
				</div>
			</div>
			<%@include file="/WEB-INF/jsps/general/default_footer.jsp" %>
		</div> 
		
<div>
	<script src="http://www.google-analytics.com/urchin.js" type="text/javascript">
	</script>
	<script type="text/javascript">
	_uacct = "UA-286501-2";
	urchinTracker();
	</script>		
</div>
		
</body>
</html>