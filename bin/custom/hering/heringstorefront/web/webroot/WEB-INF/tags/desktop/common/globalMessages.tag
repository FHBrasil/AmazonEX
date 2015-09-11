<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>

<%-- Information (confirmation) messages --%>
<c:if test="${not empty accConfMsgs}">
	<div class="container prehead141211">
		<div class="row">
			<div class="alert alert-infocu">
				<a href="#" class="close" data-dismiss="alert">×</a>
				<c:forEach items="${accConfMsgs}" var="msg">
					<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
				</c:forEach>
			</div>
		</div>
		<div class="row bluerow">
			<div class="col-sm-12 text-left hidden-xs">Einkaufen ohne Risiko</div>
			<div class="col-sm-4 col-xs-12 text-center">Kostenloser Versand ab 40 € (D)</div>
			<div class="col-sm-4 text-right hidden-xs">Kundenservice: 089 / 904&nbsp;750&nbsp;6200</div>
		</div>
	</div>	
<%-- 	<c:forEach items="${accConfMsgs}" var="msg"> --%>
<!-- 		<div class="alert positive"> -->
<%-- 			<spring:theme code="${msg.code}" arguments="${msg.attributes}"/> --%>
<!-- 		</div> -->
<%-- 	</c:forEach> --%>
</c:if>

<%-- Warning messages --%>
<c:if test="${not empty accInfoMsgs}">
	<div class="container prehead141211">
		<div class="row">
			<div class="alert alert-warning">
				<a href="#" class="close" data-dismiss="alert">×</a>
				<c:forEach items="${accInfoMsgs}" var="msg">
					<spring:theme code="${msg.code}" arguments="${msg.attributes}"/>
				</c:forEach>
			</div>
		</div>
		<div class="row bluerow">
			<div class="col-sm-12 text-left hidden-xs">Einkaufen ohne Risiko</div>
			<div class="col-sm-4 col-xs-12 text-center">Kostenloser Versand ab 40 € (D)</div>
			<div class="col-sm-4 text-right hidden-xs">Kundenservice: 089 / 904&nbsp;750&nbsp;6200</div>
		</div>
	</div>	
<%-- 	<c:forEach items="${accInfoMsgs}" var="msg"> --%>
<!-- 		<div class="alert neutral"> -->
<%-- 			<spring:theme code="${msg.code}" arguments="${msg.attributes}"/> --%>
<!-- 		</div> -->
<%-- 	</c:forEach> --%>
</c:if>

<%-- Error messages (includes spring validation messages)--%>
<c:if test="${not empty accErrorMsgs}">
	<div class="container prehead141211">
		<div class="row">
			<div class="alert alert-danger">
				<a href="#" class="close" data-dismiss="alert">×</a>
				<c:forEach items="${accErrorMsgs}" var="msg">
					<li><spring:theme code="${msg.code}" arguments="${msg.attributes}"/></li>
				</c:forEach>
			</div>
		</div>
		<div class="row bluerow">
			<div class="col-sm-12 text-left hidden-xs">Einkaufen ohne Risiko</div>
			<div class="col-sm-4 col-xs-12 text-center">Kostenloser Versand ab 40 € (D)</div>
			<div class="col-sm-4 text-right hidden-xs">Kundenservice: 089 / 904&nbsp;750&nbsp;6200</div>
		</div>
	</div>
<!-- 	<div class="alert negative"> -->
<!-- 		<ul> -->
<%-- 			<c:forEach items="${accErrorMsgs}" var="msg"> --%>
<%-- 				<li><spring:theme code="${msg.code}" arguments="${msg.attributes}"/></li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<!-- 	</div> -->
</c:if>