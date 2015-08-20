<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="showCloseOrderErrors" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showAuthorizeError" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showCaptureErrors" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showRefundErrors" required="false" type="java.lang.Boolean"%>

<c:set var="showCloseOrderErrors" value="${not empty showCloseOrderErrors ? showCloseOrderErrors : false}"/>
<c:set var="showAuthorizeError" value="${not empty showAuthorizeError ? showAuthorizeError : false}"/>
<c:set var="showCaptureErrors" value="${not empty showCaptureErrors ? showCaptureErrors : false}"/>
<c:set var="showRefundErrors" value="${not empty showRefundErrors ? showRefundErrors : false}"/>

<div class="col-sm-12">
<c:if test="${showCloseOrderErrors}">
	<div class="col-sm-3">
		<label>Simulate Close Order Error</label><br>
		<input type="radio" name="simulateCloseOrderError" value="0" checked>None<br>
		<input type="radio" name="simulateCloseOrderError" value="1">Amazon Closed<br>
	</div>
</c:if>
<c:if test="${showAuthorizeError}">
	<div class="col-sm-3">
		<label>Simulate Authorize Error</label><br>
		<input type="radio" name="simulateAuthorizeError" value="0" checked>None<br>
		<input type="radio" name="simulateAuthorizeError" value="1">Invalid Payment Method<br>
		<input type="radio" name="simulateAuthorizeError" value="2">Amazon Rejected<br>
		<input type="radio" name="simulateAuthorizeError" value="3">Transaction TimedOut<br>
		<input type="radio" name="simulateAuthorizeError" value="4">Expired Unused<br>
		<input type="radio" name="simulateAuthorizeError" value="5">Amazon Closed<br>
	</div>
</c:if>
<c:if test="${showCaptureErrors}">
	<div class="col-sm-3">
		<label>Simulate Capture Error</label><br>
		<input type="radio" name="simulateCaptureError" value="0" checked>None<br>
		<input type="radio" name="simulateCaptureError" value="1">Capture Pending<br>
		<input type="radio" name="simulateCaptureError" value="2">Amazon Rejected<br>
		<input type="radio" name="simulateCaptureError" value="3">Amazon Closed<br>
	</div>
</c:if>
<c:if test="${showRefundErrors}">
	<div class="col-sm-3">
		<label>Simulate Refund Error</label><br>
		<input type="radio" name="simulateRefundError" value="0" checked>None<br>
		<input type="radio" name="simulateRefundError" value="1">Amazon Rejected<br>
	</div>
</c:if>
</div>