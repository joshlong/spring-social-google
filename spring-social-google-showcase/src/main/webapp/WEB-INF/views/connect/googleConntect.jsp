<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>

<%@ page session="false" %>

<h3>Connect to Google</h3>

<form action="${pageContext.request.contextPath}/connect/google" method="POST">
    <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access"/>

    <div class="formInfo"><p>You aren't connected to Google yet. Click the button to connect Spring Social Showcase with your Google+ account.</p></div>

    <p> <button type="submit"><img src="${pageContext.request.contextPath}/resources/social/facebook/connect_light_medium_short.gif"/></button> </p>

    <label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall"/> Tell your circle about Spring Social Showcase on your Google+ Page</label>
</form>
