<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="zone" value="${cmsSite.bvConfig.zone}" />
<jsp:useBean id="zone" class="java.lang.String" />

<c:if test="${cmsSite.bvConfig.environment eq 'STAGING'}">
	<c:set var="stagedEnvironment" value="-stg" />
</c:if>

<%
String normalizedZone =java.net.URLEncoder.encode(zone,"UTF-8").replace("+", "%20"); 
%>


<script type="text/javascript" src="//display${stagedEnvironment}.ugc.bazaarvoice.com/static/${cmsSite.bvConfig.clientName}/<%=normalizedZone%>/${cmsSite.bvLocale}/bvapi.js">
</script>
