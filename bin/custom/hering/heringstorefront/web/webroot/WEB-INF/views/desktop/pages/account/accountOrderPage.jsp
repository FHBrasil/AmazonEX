<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<template:page pageTitle="${pageTitle}">

<!--     <div id="main-wrapper"> -->
<!--         <div> -->
<!--             <header id="page-header"> -->
<!--                 <h1> -->
<%--                     <spring:theme code="text.account.yourAccount" text="Your Account" /> --%>
<!--                 </h1> -->
<!--                 <div class="breadcrumb"> -->
<%--                     <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" /> --%>
<!--                 </div> -->
<!--             </header> -->
<!--             <section class="order-details page with-sidebar"> -->
<%--                 <nav:accountNav /> --%>
<!--                 <div class="right"> -->
<!--                     <section id="general-info" class="section-block"> -->
<!--                         <header> -->
<%--                             <h2><spring:theme code="text.fliegercommerce.texto109"/></h2> --%>
<!--                         </header> -->
<!--                         <div class="container"> -->
<!--                             <section class="review left"> -->
<!--                                 <div class="left"> -->
<!--                                     <p> -->
<%--                                         <spring:theme code="text.account.order.orderNumber" --%>
<%--                                             arguments="${orderData.code}" /> --%>
<!--                                     </p> -->
<!--                                     <p> -->
<%--                                         <spring:theme code="text.account.order.orderPlaced" --%>
<%--                                             arguments="${orderData.created}" /> --%>
<!--                                     </p> -->
<%--                                     <c:if test="${not empty orderData.statusDisplay}"> --%>
<!--                                         <p> -->
<%--                                             <spring:theme --%>
<%--                                                 code="text.account.order.status.display.${orderData.statusDisplay}" --%>
<%--                                                 var="orderStatus" /> --%>
<%--                                             <spring:theme code="text.account.order.orderStatus" --%>
<%--                                                 arguments="${orderStatus}" /> --%>
<!--                                             <br /> -->
<!--                                         </p> -->
<%--                                     </c:if> --%>
<!--                                 </div> -->
<%--                                 <c:if test="${not empty orderHistoryPreview}"> --%>
<%--                                     <c:forEach items="${orderHistoryPreview}" var="orderHistory"> --%>
<%--                                         <c:if test="${orderHistory.code == orderData.code}"> --%>
<%--                                             <order:paymentDetailsItem order="${orderData}" --%>
<%--                                                 orderHistory="${orderHistory}" /> --%>
<%--                                         </c:if> --%>
<%--                                     </c:forEach> --%>
<%--                                 </c:if> --%>
<!--                             </section> -->
<%--                             <c:if test="${not empty orderHistoryPreview}"> --%>
<%--                                 <c:forEach items="${orderHistoryPreview}" var="orderHistory"> --%>
<%--                                     <c:if test="${orderHistory.code == orderData.code}"> --%>
<%--                                         <order:orderTotalsItem order="${orderData}" --%>
<%--                                             orderHistory="${orderHistory}" /> --%>
<%--                                     </c:if> --%>
<%--                                 </c:forEach> --%>
<%--                             </c:if> --%>
<!--                         </div> -->
<!--                     </section> -->
<%--                     <order:deliveryAddressItem order="${orderData}" /> --%>
<%--                     <order:deliveryMethodItem order="${orderData}" /> --%>
<%--                     <order:billingAddressItem order="${orderData}" /> --%>
<%--                     <c:if test="${not empty orderData.unconsignedEntries}"> --%>
<%--                         <order:orderUnconsignedEntries order="${orderData}" page='orderPage' /> --%>
<%--                     </c:if> --%>
<!--                 </div> -->
<!--             </section> -->
<!--         </div> -->
<!--     </div> -->

<div class="container">
	<small>
	    <ul class="breadcrumb">
	        <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	    </ul>
	</small>
</div>
<div class="container">
	<div class="col-sm-12 col-md-8 order150312">
		<div class="row">
			<div class="col-sm-3">
				<div class=" panel panel-default">
					<div class="panel-body text-center">
						<div class="title150312 text-left">Status</div>
						<c:if test="${orderData.status == 'COMPLETED'}">
							<span class="glyphicon glyphicon-ok" style="font-size: 100px;"></span>
						</c:if>
						<c:if test="${orderData.status != 'COMPLETED'}">
							<span class="glyphicon glyphicon-time" style="font-size: 100px;"></span>
						</c:if>
						<div class="value150312">
							<spring:theme code="text.account.order.status.display.${orderData.statusDisplay}" var="orderStatus" />
							${orderStatus}
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-5">
				<div class=" panel panel-default">
					<div class="panel-body">
						<div class="title150312">Bestellnummer</div>
						 <span class="value2150312">
						 	${orderData.code}
					 	</span>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class=" panel panel-default">
					<div class="panel-body">
						<div class="title150312">Datum</div>
						<span class="value2150312">
							<fmt:formatDate value="${orderData.created}" pattern="dd.MM.yy"/>
						</span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class=" panel panel-default">
					<div class="panel-body">
						<div class="title150312">Gesamt</div>
						<span class="value3150312"><b>
							<format:price priceData="${orderData.totalPrice}" displayFreeForZero="true" />
						</b></span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class=" panel panel-default">
					<div class="panel-body">
						<div class="title150312">Zahlart</div>
						<span class="value3150312"><b>
							${orderHistory.paymentMode}
						</b></span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class=" panel panel-default">
					<div class="panel-body">
						<div class="title150312">Bonuspunkte</div>
						<span class="value3150312"><b>+666</b></span>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default positions150327">
			<c:if test="${not empty orderData.statusDisplay}">
				<div class="panel-heading">
					<div class="col-sm-12 ship150327"><div class="title150312">Sendung 1</div><b><span class="value3150312"><span class="glyphicon glyphicon-time"></span> Wartet auf Geldeinang</span></b></div>
				</div>
				<c:forEach items="${orderHistoryPreview}" var="orderHistory">
					<c:if test="${orderHistory.code == orderData.code}">
						<order:orderTotalsItem order="${orderData}" orderHistory="${orderHistory}" />
                	</c:if>
				</c:forEach>
				<div class="panel-footer clearfix statusbar150327">
					<div class="col-sm-3 shipout150327">
						<div class="title150312">Geplanter Versand</div>
						<b>
							<span class="value3150312">
								<span class="glyphicon glyphicon-road"></span> 
								[FIXED]Bei Zahlung
							</span>
						</b>
					</div>
					<div class="col-sm-6 tracking150327">
						<div class="title150312">Sendung verfolgen</div>
						<b>
							<span class="value3150312">
								<span class="glyphicon glyphicon-screenshot"></span>
								[FIXED]
							</span>
						</b>
					</div>
					<div class="col-sm-3 receive150327">
						<div class="title150312">Geplante Lieferung</div>
						<b>
							<span class="value3150312">
								<span class="glyphicon glyphicon-map-marker"></span> 
								[FIXED]Zahlung +3 Tage
							</span>
						</b>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div class="col-sm-12 col-md-4">
		<div class="row">
			<div class="col-xs-12">
				<div class=" panel panel-default">
					<order:billingAddressItem order="${orderData}" />
				</div>
			</div>
			
			<div class="col-xs-12">
				<div class=" panel panel-default">
					<order:deliveryAddressItem order="${orderData}" />
				</div>
			</div>
			<div class="col-xs-12">
				<div class=" panel panel-default">
					<div class="panel-heading"><span class="fox24png150217"></span> Bonuspunkte[FIXED]
					</div>
					<div class="panel-body">
						Bonuspunkte eingelöst: 100<br>
						Bonuspunkte vorgemerkt: 300<br>
						Bonuspunkte erhalten: 0
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!--     <div id="main-wrapper"> -->
<!--         <div class="container"> -->
<!--             <header id="page-header"> -->
<!--                 <h1> -->
<%--                     <spring:theme code="text.account.yourAccount" text="Your Account" /> --%>
<!--                 </h1> -->
<!--                 <div class="breadcrumb"> -->
<%--                     <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" /> --%>
<!--                 </div> -->
<!--             </header> -->
<!--             <section class="order-details page with-sidebar"> -->
<%--                 <nav:accountNav /> --%>
<!--                 <div class="right"> -->
<!--                     <section id="general-info" class="section-block"> -->
<!--                         <header> -->
<%--                             <h2><spring:theme code="text.fliegercommerce.texto109"/></h2> --%>
<!--                         </header> -->
<!--                         <div class="container"> -->
<!--                             <section class="review left"> -->
<!--                                 <div class="left"> -->
<!--                                     <p> -->
<%--                                         <spring:theme code="text.account.order.orderNumber" --%>
<%--                                             arguments="${orderData.code}" /> --%>
<!--                                     </p> -->
<!--                                     <p> -->
<%--                                         <spring:theme code="text.account.order.orderPlaced" --%>
<%--                                             arguments="${orderData.created}" /> --%>
<!--                                     </p> -->
<%--                                     <c:if test="${not empty orderData.statusDisplay}"> --%>
<!--                                         <p> -->
<%--                                             <spring:theme --%>
<%--                                                 code="text.account.order.status.display.${orderData.statusDisplay}" --%>
<%--                                                 var="orderStatus" /> --%>
<%--                                             <spring:theme code="text.account.order.orderStatus" --%>
<%--                                                 arguments="${orderStatus}" /> --%>
<!--                                             <br /> -->
<!--                                         </p> -->
<%--                                     </c:if> --%>
<!--                                 </div> -->
<%--                                 <c:if test="${not empty orderHistoryPreview}"> --%>
<%--                                     <c:forEach items="${orderHistoryPreview}" var="orderHistory"> --%>
<%--                                         <c:if test="${orderHistory.code == orderData.code}"> --%>
<%--                                             <order:paymentDetailsItem order="${orderData}" --%>
<%--                                                 orderHistory="${orderHistory}" /> --%>
<%--                                         </c:if> --%>
<%--                                     </c:forEach> --%>
<%--                                 </c:if> --%>
<!--                             </section> -->
<%--                             <c:if test="${not empty orderHistoryPreview}"> --%>
<%--                                 <c:forEach items="${orderHistoryPreview}" var="orderHistory"> --%>
<%--                                     <c:if test="${orderHistory.code == orderData.code}"> --%>
<%--                                         <order:orderTotalsItem order="${orderData}" --%>
<%--                                             orderHistory="${orderHistory}" /> --%>
<%--                                     </c:if> --%>
<%--                                 </c:forEach> --%>
<%--                             </c:if> --%>
<!--                         </div> -->
<!--                     </section> -->
<%--                     <order:deliveryAddressItem order="${orderData}" /> --%>
<%--                     <order:deliveryMethodItem order="${orderData}" /> --%>
<%--                     <order:billingAddressItem order="${orderData}" /> --%>
<%--                     <c:if test="${not empty orderData.unconsignedEntries}"> --%>
<%--                         <order:orderUnconsignedEntries order="${orderData}" page='orderPage' /> --%>
<%--                     </c:if> --%>
<!--                 </div> -->
<!--             </section> -->
<!--         </div> -->
<!--     </div> -->

</template:page>
