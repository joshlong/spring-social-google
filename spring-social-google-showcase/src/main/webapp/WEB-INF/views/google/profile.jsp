<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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

 <OL>
      <LI>
          <a href="${pageContext.request.contextPath}/timelines">
              Manipulate the timeline
          </a>
      </LI>
 </OL>
<form id="disconnect" action="${pageContext.request.contextPath}/connect/google" method="post">
    <button type="submit">Disconnect from Google</button>
    <input type="hidden" name="_method" value="delete"/>
</form>
