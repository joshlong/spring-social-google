<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<form id="signin" action="${pageContext.request.contextPath}/signin/authenticate"  method="post">
	<div class="formInfo">
  		<c:if test="${param.error eq 'bad_credentials'}">
  		<div class="error">
  			Your sign in information was incorrect.
  			Please try again or <a href="${pageContext.request.contextPath}/signup" >sign up</a>.
  		</div>
 	 	</c:if>
  		<c:if test="${param.error eq 'multiple_users'}">
  		<div class="error">
  			Multiple local accounts are connected to the provider account.
  			Try again with a different provider or with your username and password.
  		</div>
 	 	</c:if>
	</div>
	<fieldset>
		<label for="login">Username</label>
		<input id="login" name="j_username" type="text" size="25" <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if> />
		<label for="password">Password</label>
		<input id="password" name="j_password" type="password" size="25" />	
	</fieldset>
	<button type="submit">Sign In</button>
	
	<p>Some test user/password pairs you may use are:</p>
	<ul>
		<li>habuma/freebirds</li>
		<li>kdonald/melbourne</li>
		<li>rclarkson/atlanta</li>
        <li>jlong/cowbell</li>
	</ul>
	
	<p>Or you can <a href="${pageContext.request.contextPath}/signup">signup</a> for a new account.</p>
</form>



	<!-- GOOGLE SIGNIN -->
Or
<form style="display: inline-block" id="google_signin" action="${pageContext.request.contextPath}/signin/google" method="POST">

    <input type="hidden" name="scope" value="https://www.googleapis.com/auth/blogger https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/glass.timeline https://www.googleapis.com/auth/glass.location https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/drive.apps.readonly https://www.googleapis.com/auth/drive.file https://www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/drive.readonly https://www.googleapis.com/auth/drive.scripts" />
    <button type="submit"><img src="${pageContext.request.contextPath}/resources/social/google/connect-with-google.png" /></button>
</form>

