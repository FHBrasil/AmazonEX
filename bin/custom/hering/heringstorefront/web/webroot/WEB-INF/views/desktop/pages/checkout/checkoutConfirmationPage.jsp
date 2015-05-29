<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<template:page pageTitle="${pageTitle}">
    <div class="container">
        <%-- <div class="orderConfirmationData">
				<input type="hidden" class="orderID" value="${orderCode}" /> <input
					type="hidden" class="orderTotalPrice"
					value="${orderData.totalPrice.value}" />
			</div> --%>
        <section class="home page with-sidebar pc">
            <ycommerce:testId code="orderConfirmation_yourOrderResults_text">
                <h2>O seu pedido foi realizado com sucesso!</h2>
                <p>
                    <spring:theme code="checkout.orderConfirmation.rememberUs" />
                </p>
                <%--  <div class="orderConfirmationData">
						<input type="hidden" class="orderID" value="${orderCode}" /> <input
							type="hidden" class="orderTotalPrice"
							value="${orderData.totalPrice.value}" />
					</div>			 --%>
                <div>
                    <small> <spring:theme
                            code="checkout.orderConfirmation.orderNumberInformation" />
                    </small> <big>${orderData.code}</big>
                    <c:if test="${not empty orderData.customPaymentInfo}">
                    	<c:url var="boletoDownloadLink" value="../../${boletoUrl}" />
                        <a class="rb" href="${boletoDownloadLink}" target="_blank"> <spring:theme
                                code="checkout.orderConfirmation.reprintBoleto" />
                        </a>
                    </c:if>
                    <span> <c:choose>
                            <c:when test="${not orderData.guestCustomer}">
                                <spring:theme code="checkout.orderConfirmation.seePurchases" />
                                <br />
                                <a href="${request.contextPath}/my-account/orders"> Meus pedidos
                                </a>
                            </c:when>
                            <c:otherwise>
                                <spring:theme code="checkout.confirmation.guest.email" />
                            </c:otherwise>
                        </c:choose>
                    </span>
                    <ul>
                        <li>
                            <%-- <a href="${request.contextPath}/my-account/order/${orderData.code}" target="_blank">Resumo do pedido</a>  --%>
                            <a class="fancybox" href="#modal-order-resume"> <spring:theme
                                    code="order.resume" />
                        </a>
                        </li>
                        <%-- <li><a href="#">Texto legal</a></li> --%>
                        <li><a target="_blank" href="/medias/contrato-${themeName}.pdf">
                                Contrato </a></li>
                    </ul>
                    <ul style="margin-top: 15px;">
                        <li><c:url var="urlTroca" value="../../troca-e-devolucao" /> <a
                            href="${urlTroca}"><spring:theme code="order.change" /></a></li>
                    </ul>
                </div>
                <a href="http://www.ebitempresa.com.br/bitrate/pesquisa1.asp?empresa=17545"
                    target="_blank"> <img border="0" name="E-bit"
                    src="${themeResourcePath}/assets/images/banner-ebit.jpg"
                    alt="O que você achou desta loja?" width="468" height="60">
                </a>
                <a href="${request.contextPath}" class="volte"> <spring:theme
                        code="checkout.orderConfirmation.comebackHomeNews" />
                </a>
            </ycommerce:testId>
        </section>
    </div>
</template:page>
<order:orderModalPanels order="${orderData}" />
