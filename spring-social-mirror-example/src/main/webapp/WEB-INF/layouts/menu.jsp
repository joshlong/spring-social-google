<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/spring-social/social/tags" prefix="social" %>

<h4><a href="${pageContext.request.contextPath}/connections">connections</a></h4>


<h4><a href="${pageContext.request.contextPath}/google">google</a></h4>
<social:connected provider="linkedin">
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/google/">google </a></li>
        <li><a href="${pageContext.request.contextPath}/google/google">google connections</a></li>
    </ul>
</social:connected>
<%--

<h4><a href="${pageContext.request.contextPath}/twitter">twitter</a></h4>
<social:connected provider="twitter">
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/twitter/">twitter </a></li>
        <li><a href="${pageContext.request.contextPath}/twitter/timeline">twitter timeline</a></li>
        <li><a href="${pageContext.request.contextPath}/twitter/friends">twitter friends</a></li>
        <li><a href="${pageContext.request.contextPath}/twitter/followers">twitter followers</a></li>
        <li><a href="${pageContext.request.contextPath}/twitter/messages">twitter messages</a></li>
        <li><a href="${pageContext.request.contextPath}/twitter/trends">twitter trends</a></li>
    </ul>
</social:connected>

<h4><a href="${pageContext.request.contextPath}/facebook">facebook</a></h4>
<social:connected provider="facebook">
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/facebook/">facebook </a></li>
        <li><a href="${pageContext.request.contextPath}/facebook/feed">facebook feed</a></li>
        <li><a href="${pageContext.request.contextPath}/facebook/friends">facebook friends</a></li>
        <li><a href="${pageContext.request.contextPath}/facebook/albums">facebook albums</a></li>
    </ul>

</social:connected>

<h4><a href="${pageContext.request.contextPath}/linkedin">linkedin</a></h4>
<social:connected provider="linkedin">
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/linkedin/">linkedin </a></li>
        <li><a href="${pageContext.request.contextPath}/linkedin/connections">linkedin connections</a></li>
    </ul>
</social:connected>

--%>
