<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>

<h3>Your Google Profile</h3>

<p>Hello, <c:out value="${profile.firstName}"/>!</p>
<dl>
    <dt>Google ID:</dt>
    <dd><c:out value="${profile.id}"/></dd>
    <dt>Name:</dt>
    <dd><c:out value="${profile.name}"/></dd>
    <dt>Email:</dt>
    <dd><c:out value="${email}"/></dd>
</dl>

<form id="disconnect" action="${pageContext.request.contextPath}/connect/google" method="post">
    <button type="submit">Disconnect from Google</button>
    <input type="hidden" name="_method" value="delete"/>
</form>
