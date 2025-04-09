<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Contact Listing</title>

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
<h1 class="bot_titlelogo">BotList - Contact Ad Poster | Home</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>				
			

<div style="margin: 20px;">

<div style="border: 1px solid #DDD; padding: 10px; margin-right: 100px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<!-- Build the table for entering the new department information -->
	<a href="<c:url value="/spring/listings.html" />" >Listings</a>
	
	<p>
		
	<i>Contacting ad poster</i>
	<br>
	Please add the following values as verification
	to contact the ad poster.

<form:form method="post">
<form:errors path="*" cssClass="general_field_error" />

<table width="550" class="viewlisting">
	
	<!-- First Name -->
	<tr>
		<td>
			<span style="color: #777;">
			<i>Created - <fmt:formatDate pattern="EE dd, MMM yyyy hh:mm" 
					value="${command.createdOn.time}" /></i>								
			</span>
		</td>		
	</tr>
	
	<!-- First Name -->
	<tr>
		<td valign="top">
			<b>Title:</b>
		</td>
	</tr>
	<tr>
		<td>
			<c:out value="${command.title}" />
		</td>
	</tr>		
	
	<c:if test="${not empty command.keywords}">
		<tr><td><b>tags: </b></td></tr>
		<tr>
			<td>
				<c:out value="${command.keywords}" />
			</td>
		</tr>
	</c:if>
			
	<tr>
		<td><input type="submit" value=" Contact Poster " /></td>
	</tr>
	
	<tr>
		<td>
			<!--  Section for Addition Verification -->
			<div style="margin-left: 24px; margin-top: 10px;">
				<div class="info_spam_addition" id="info_spam_addition">
					<h2>Please add the following values in order to receive contact information</h2>
					<p>
						<!-- table for spam verify -->
						<table>
						<tr>
							<td>
								<c:out value="${command.firstInput}" />
							</td>
							<td>+</td>
							<td><c:out value="${command.secondInput}" /></td>
							<td>=</td>
							<td>
								<form:input size="6" path="userSolution" />
							</td>
						</tr>
						</table>
						<!-- End of Table for spam verify -->
				</div>
				<!-- End of Addition for Spam Check -->
			</div>
		</td>
	</tr>

		
</table>
<!-- End of the table -->

<form:hidden path="id" />
<form:hidden path="uniqueId" />

</form:form>

</div>

</div>

</div>

<%@include file="/WEB-INF/jsps/general/default_footer.jsp"%>
</div>

</body>
</html>
