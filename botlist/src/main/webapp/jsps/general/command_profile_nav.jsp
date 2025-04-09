<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<%-- Add Welcome user message and login content (above content border line) --%>
	<%-- Input includes userForm --%>
	<div style="color: #888; width: 100%;">
		<table width="100%">
		<tr>
			<c:choose>
				<c:when test="${command != null}" >
					<td>you are <b>logged</b> in as: <c:out value="${command.userName}" /></td>
				</c:when>
				<c:otherwise>
           			<td>you are currently <b>not logged</b> in (use login or register)</td>
        		</c:otherwise>
			</c:choose>
			<td align="right">
				<c:choose>
				<c:when test="${command != null}" >
					<a href="<c:url value="/spring/profile/login.html" />" class="linklist_sample">profile</a>
					| <a href="<c:url value="/spring/profile/settings.html" />" class="linklist_sample">settings</a>
					| <a href="<c:url value="/spring/profile/logout.html" />" class="linklist_sample">logout</a>
				</c:when>
				<c:otherwise>
						<a href="<c:url value="/spring/profile/login.html" />" class="linklist_sample">login</a>
						| <a href="<c:url value="/spring/profile/register.html" />" class="linklist_sample">register</a>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</table>
	</div>
	<%-- End of Welcome Header --%>