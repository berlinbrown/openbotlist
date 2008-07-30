<%@include file="/WEB-INF/jsps/general/default_doc_type.jsp" %>
<%@ page contentType="text/html"%>
<%@include file="/WEB-INF/jsps/general/default_includes.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Botlist - Create Listing</title>

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
<h1 class="bot_titlelogo">BotList - Create Listing | Home</h1>
			<%-- Navigation Header --%>
			<%@include file="/WEB-INF/jsps/general/default_navigation.jsp" %>			
			<%-- End of Navigation Header --%>					


	<br>
	<div style="color: #777; font-size: 12pt;">
				Area: <c:out value="${command.cityListing.cityName}" />
				| <c:out value="${command.postSection.sectionName}" />
	</div> 


<div style="margin: 20px;">			

<div style="border: 1px solid #DDD; padding: 10px;">
<!-- Display the error message -->
<div class="bot_profile_sect_add_link ">

	<!-- Build the table for entering the new department information -->
	<a href="<c:url value="/spring/citylist.html" />" class="linklist_botnav">/ Home</a>
	
<form:form method="post" enctype="multipart/form-data">
<form:errors path="*" cssClass="general_field_error" />
<table>

	<%-- First Name --%>
	<tr>
		<td valign="top"><b>Title:</b></td>
	</tr>
	<tr>
		<td>
		<form:input path="title" size="40" />
		</td>
		<td>
			<form:errors path="title" cssClass="general_field_error" />
		</td>
	</tr>

	<!-- First Name -->
	<tr>
		<td valign="top"><b>Topic Listing:</b></td>
	</tr>
	<tr>
		<td valign="top">
		<form:textarea rows="12" cols="60" path="message"  />
		</td>
		<td valign="top">
			<form:errors path="message" cssClass="general_field_error" />
		</td>
	</tr>

	<%-- Email --%>
	<tr>
		<td valign="top"><b>Email (will not be displayed):</b></td>
	</tr>
	<tr>
		<td>
		<form:input path="email" size="40" />
		</td>
		<td valign="top">
			<form:errors path="email" cssClass="general_field_error" />
		</td>
	</tr>
	
	<tr><td valign="top"><b>Listing URL:</b></td></tr>
	<tr>
		<td>
		<form:input path="mainUrl" size="40" />
		</td>		
	</tr>
	
	<tr><td valign="top"><b>Keywords (space separated):</b></td></tr>
	<tr>
		<td>
		<form:input path="keywords" size="40" />
		</td>		
	</tr>
	
	
	<tr>
		<td>
			<input type="submit" id="save" value=" Save Topic " />
			<input type="submit" id="preview" value=" Preview " />
		</td>
	</tr>
	
	<tr>
	<td valign="top">
		<!-- Additional Section for Spam Prevition Simple Addition -->
		<div style="margin-left: 24px; margin-top: 10px;">
				<div class="info_spam_addition" id="info_spam_addition">
					<h2>Please add the following values in order to submit</h2>
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
		<td valign="top">
			<div style="margin-top: 10px;">
				<form:errors path="userSolution" cssClass="general_field_error" />
			</div>
		</td>
	</tr>	
	<%-- Next Row for adding images --%>
	<tr>
		<td>
			<div style="margin-left: 24px;">
			 <fieldset>
				<legend align="left">&nbsp;<b>Select image(s) for upload (optional, jpeg only)</b>&nbsp;</legend>
					<%-- Div to add padding and background --%>
					<div style="padding: 4px;">
					<table cellpadding="4" cellspacing="4" border="0">
					<tr>
						<td>
							<nobr>Image 1:</nobr>
						</td>
						<td>
							<spring:bind path="command.uploadFilenameFirst">
								<input type="file" name="<c:out value="${status.expression}" />" size="35">	
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td>
							<nobr>Image 2:</nobr>
						</td>
						<td>
							<spring:bind path="command.uploadFilenameSecond">
								<input type="file" name="<c:out value="${status.expression}" />" size="35">	
							</spring:bind>
						</td>
					</tr>
					</table>
					</div>				
			</fieldset>
		   </div>
		   <!-- End of Div around field set -->
		</td>
	</tr>
	<!-- End of Row -->
	
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
