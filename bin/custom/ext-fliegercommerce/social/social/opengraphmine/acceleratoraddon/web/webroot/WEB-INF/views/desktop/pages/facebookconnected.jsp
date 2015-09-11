<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>

<html>
<head>
<template:javaScriptVariables/>
<c:set value="${facebookUrl}"  var="url" />
<script type="text/javascript">
	var url = '${redirUrl}'.length > 0 ? '${redirUrl}' : ACC.config.contextPath;
	
	window.opener.location.href = url;
	window.close();
	window.opener.location.reload();
</script>
</head>
<body>
</body>
</html>
